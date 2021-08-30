package com.advisor.flight.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.CountryDto;
import com.advisor.flight.entity.Country;
import com.advisor.flight.exception.CountryNameExistException;
import com.advisor.flight.exception.CountryNotExistException;
import com.advisor.flight.mapper.CountryMapper;
import com.advisor.flight.repository.CountryRepository;

@Service
public class CountryService {

	private CountryRepository countryRepository;
	private CountryMapper countryMapper;

	@Autowired
	public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
		this.countryRepository = countryRepository;
		this.countryMapper = countryMapper;
	}

	public List<CountryDto> getAll() {
		return countryRepository.findAll().stream().map(country -> countryMapper.toDto(country))
				.collect(Collectors.toList());
	}

	public CountryDto find(Long id) throws CountryNotExistException {
		Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotExistException());
		return countryMapper.toDto(country);
	}

	public CountryDto store(CountryDto countryDto) throws CountryNameExistException {
		Optional<Country> existingCountry = countryRepository.findByName(countryDto.getName());
		if (existingCountry.isPresent()) {
			throw new CountryNameExistException();
		}
		return countryMapper.toDto(countryRepository.save(countryMapper.toEntity(countryDto)));
	}

	public CountryDto update(CountryDto countryDto) throws CountryNameExistException, CountryNotExistException {
		Country existingCountry = countryRepository.findById(countryDto.getId())
				.orElseThrow(() -> new CountryNotExistException());
		Country sameCountry = countryRepository.findByName(countryDto.getName()).orElse(null);
		if (sameCountry != null) {
			if (!existingCountry.equals(sameCountry)) {
				throw new CountryNameExistException();
			}
		}
		return countryMapper.toDto(countryRepository.save(countryMapper.toEntity(countryDto)));

	}

	public void delete(Long id) throws CountryNotExistException {
		Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotExistException());
		countryRepository.delete(country);
	}

}
