package com.advisor.flight.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.AirportDto;
import com.advisor.flight.entity.Airport;
import com.advisor.flight.entity.City;
import com.advisor.flight.exception.AirportExistException;
import com.advisor.flight.mapper.AirportMapper;
import com.advisor.flight.mapper.CityMapper;
import com.advisor.flight.repository.AirportRepository;
import com.advisor.flight.repository.CityRepository;
import com.advisor.flight.utils.StringManipulator;

@Service
public class AirportService {

	private AirportRepository airportRepository;
	private AirportMapper airportMapper;

	private ResourceLoader resourceLoader;

	private CityRepository cityRepository;
	private CityMapper cityMapper;

	@Autowired
	public AirportService(AirportRepository airportRepository, AirportMapper airportMapper,
			CityRepository cityRepository, CityMapper cityMapper, ResourceLoader resourceLoader) {
		this.airportRepository = airportRepository;
		this.airportMapper = airportMapper;
		this.cityRepository = cityRepository;
		this.cityMapper = cityMapper;
		this.resourceLoader = resourceLoader;
	}

	public List<AirportDto> getAll() {
		return airportRepository.findAll().stream().map(airport -> airportMapper.toDto(airport))
				.collect(Collectors.toList());
	}

	public AirportDto store(AirportDto airportDto) throws AirportExistException {
		Optional<Airport> existingAirport = airportRepository.findById(airportDto.getId());
		if (existingAirport.isPresent()) {
			throw new AirportExistException();
		}
		return airportMapper.toDto(airportRepository.save(airportMapper.toEntity(airportDto)));
	}

	public List<AirportDto> storeAirports() throws IOException, AirportExistException {
		List<String[]> airports = getAirportsWithExistedCities(cityRepository.findAll());
		List<AirportDto> storedAirports = new ArrayList<>();
		for (String[] row : airports) {
			AirportDto airportDto = bindParams(row);
			storedAirports.add(store(airportDto));
		}
		return storedAirports;
	}

	private City getCityByNameAndCountry(String name, String country) {
		List<City> cities = cityRepository.findAll();
		City city = null;

		for (City c : cities) {
			if (name.equals(c.getName()) && country.equals(c.getCountry().getName())) {
				city = c;
			}
		}

		return city;
	}

	private AirportDto bindParams(String[] airport) {
		AirportDto airportDto = new AirportDto();

		airportDto.setId(StringManipulator.preventNForLong(airport[0]));
		airportDto.setName(StringManipulator.removeQuotes(airport[1]));

		airportDto.setCityDto(cityMapper.toDto(getCityByNameAndCountry(StringManipulator.removeQuotes(airport[2]),
				StringManipulator.removeQuotes(airport[3]))));

		airportDto.setIata(StringManipulator.removeQuotes(airport[4]));
		airportDto.setIcao(StringManipulator.removeQuotes(airport[5]));
		airportDto.setLatitude(StringManipulator.preventNForDouble(airport[6]));
		airportDto.setLongitude(StringManipulator.preventNForDouble(airport[7]));
		airportDto.setAltitude(StringManipulator.preventNForDouble(airport[8]));
		airportDto.setTimezone(StringManipulator.preventNForDouble(airport[9]));
		airportDto.setDst(StringManipulator.removeQuotes(airport[10]));
		airportDto.setTz(StringManipulator.removeQuotes(airport[11]));
		airportDto.setType(StringManipulator.removeQuotes(airport[12]));
		airportDto.setSource(StringManipulator.removeQuotes(airport[13]));

		return airportDto;
	}

	private List<String[]> getAirportsFromFile() throws IOException {
		File airportTxt = resourceLoader.getResource("classpath:static/airports.txt").getFile();
		Scanner reader = new Scanner(airportTxt);
		List<String[]> listData = new ArrayList<>();

		while (reader.hasNextLine()) {
			String[] tempArr = reader.nextLine().split(",");
			listData.add(tempArr);
		}
		reader.close();

		return listData;
	}

	private List<String[]> getAirportsWithExistedCities(List<City> cities) throws IOException {

		List<String[]> airports = getAirportsFromFile();

		Iterator<String[]> iterator = airports.iterator();

		while (iterator.hasNext()) {
			boolean isPresent = false;
			String[] row = iterator.next();
			for (City city : cities) {
				if (StringManipulator.removeQuotes(row[2]).equals(city.getName())) {
					if (StringManipulator.removeQuotes(row[3]).equals(city.getCountry().getName())) {
						isPresent = true;
					}
				}
			}
			if (isPresent == false) {
				iterator.remove();
			}
		}

		return airports;

	}

}
