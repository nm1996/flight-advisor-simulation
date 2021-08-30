package com.advisor.flight.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.CityDto;
import com.advisor.flight.entity.City;
import com.advisor.flight.entity.Comment;
import com.advisor.flight.entity.Country;
import com.advisor.flight.exception.CityExistException;
import com.advisor.flight.exception.CityNameNotExistException;
import com.advisor.flight.exception.CityNotExistException;
import com.advisor.flight.exception.CountryNotExistException;
import com.advisor.flight.mapper.CityMapper;
import com.advisor.flight.repository.CityRepository;
import com.advisor.flight.repository.CountryRepository;
import com.advisor.flight.utils.CommentComparator;

@Service
public class CityService {

	private CityRepository cityRepository;
	private CountryRepository countryRepository;
	private CityMapper cityMapper;

	@Autowired
	public CityService(CityRepository cityRepository, CountryRepository countryRepository, CityMapper cityMapper) {
		this.cityRepository = cityRepository;
		this.countryRepository = countryRepository;
		this.cityMapper = cityMapper;
	}

	public List<CityDto> getAll() {
		return cityRepository.findAll().stream().map(city -> cityMapper.toDto(city)).collect(Collectors.toList());
	}

	public List<CityDto> getAllWithLimitedComments(Integer numOfComments) {
		List<City> cities = cityRepository.findAll();
		for (City city : cities) {
			List<Comment> oldCommentsList = city.getComments();
			Collections.sort(oldCommentsList, new CommentComparator());
			List<Comment> newCommentsList = oldCommentsList.stream().limit(numOfComments).collect(Collectors.toList());
			city.setComments(newCommentsList);
		}
		return cities.stream().map(city -> cityMapper.toDto(city)).collect(Collectors.toList());
	}

	public List<CityDto> getByName(String name) throws CityNameNotExistException {
		List<City> cities = cityRepository.findByName(name).orElseThrow(() -> new CityNameNotExistException());
		return cities.stream().map(city -> cityMapper.toDto(city)).collect(Collectors.toList());
	}

	public List<CityDto> getByNameWithLimitedComments(String name, Integer numOfComments)
			throws CityNameNotExistException {
		List<City> cities = cityRepository.findByName(name).orElseThrow(() -> new CityNameNotExistException());
		for (City city : cities) {
			List<Comment> oldCommentsList = city.getComments();
			Collections.sort(oldCommentsList, new CommentComparator());
			List<Comment> newCommentsList = oldCommentsList.stream().limit(numOfComments).collect(Collectors.toList());
			city.setComments(newCommentsList);
		}

		return cities.stream().map(city -> cityMapper.toDto(city)).collect(Collectors.toList());
	}

	public CityDto find(Long id) throws CityNotExistException {
		City city = cityRepository.findById(id).orElseThrow(() -> new CityNotExistException());
		return cityMapper.toDto(city);
	}

	public CityDto store(CityDto cityDto) throws CountryNotExistException, CityExistException, CityNameNotExistException {
		Country country = countryRepository.findById(cityDto.getCountryDto().getId())
				.orElseThrow(() -> new CountryNotExistException());
		List<City> existingCities = cityRepository.findByName(cityDto.getName())
				.orElseThrow(() -> new CityNameNotExistException());
		City city = this.cityMapper.toEntity(cityDto);

		for (City c : existingCities) {
			if (city.equalsNameAndCountry(c)) {
				throw new CityExistException();
			}
		}

		city.setCountry(country);
		return cityMapper.toDto(this.cityRepository.save(city));
	}

	public CityDto update(CityDto cityDto) throws CityNotExistException, CountryNotExistException, CityExistException {
		cityRepository.findById(cityDto.getId()).orElseThrow(() -> new CityNotExistException());
		Country country = countryRepository.findById(cityDto.getCountryDto().getId())
				.orElseThrow(() -> new CountryNotExistException());
		List<City> sameCity = cityRepository.findByName(cityDto.getName()).orElse(null);
		City city = cityMapper.toEntity(cityDto);
		if (sameCity != null) {
			for(City c: sameCity){
				if(city.equals(c)) {
					throw new CityExistException();
				}
			}
		}
		city.setCountry(country);
		return cityMapper.toDto(cityRepository.save(city));

	}

	public void delete(Long id) throws CityNotExistException {
		City city = this.cityRepository.findById(id).orElseThrow(() -> new CityNotExistException());
		cityRepository.delete(city);
	}

}
