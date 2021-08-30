package com.advisor.flight.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.RouteDto;
import com.advisor.flight.entity.Airport;
import com.advisor.flight.entity.Route;
import com.advisor.flight.exception.RouteExistException;
import com.advisor.flight.mapper.AirportMapper;
import com.advisor.flight.mapper.RouteMapper;
import com.advisor.flight.repository.AirportRepository;
import com.advisor.flight.repository.RouteRepository;
import com.advisor.flight.utils.StringManipulator;

@Service
public class RouteService {

	private RouteRepository routeRepository;
	private ResourceLoader resourceLoader;
	private RouteMapper routeMapper;
	private AirportRepository airportRepository;
	private AirportMapper airportMapper;

	@Autowired
	public RouteService(RouteRepository routeRepository, ResourceLoader resourceLoader, RouteMapper routeMapper,
			AirportRepository airportRepository, AirportMapper airportMapper) {
		this.routeRepository = routeRepository;
		this.resourceLoader = resourceLoader;
		this.routeMapper = routeMapper;
		this.airportRepository = airportRepository;
		this.airportMapper = airportMapper;
	}

	public List<RouteDto> getAll() throws IOException {
		return routeRepository.findAll().stream().map(route -> routeMapper.toDto(route)).collect(Collectors.toList());
	}

	public RouteDto store(RouteDto routeDto) throws RouteExistException {
		Route route = routeMapper.toEntity(routeDto);

		if (routeRepository.findAll().contains(route)) {
			throw new RouteExistException();
		}

		return routeMapper.toDto(routeRepository.save(route));
	}

	private List<String[]> getRoutesFromFile() throws IOException {
		File routeTxt = resourceLoader.getResource("classpath:static/routes.txt").getFile();
		Scanner reader = new Scanner(routeTxt);
		List<String[]> listData = new ArrayList<>();

		while (reader.hasNextLine()) {
			String[] tempArr = reader.nextLine().split(",");
			listData.add(tempArr);
		}
		reader.close();

		return listData;
	}

	private List<String[]> getRouteWithExistingAirport(List<Airport> airports) throws IOException {

		List<String[]> routes = getRoutesFromFile();

		Map<Long, String> aps = airports.stream().collect(Collectors.toMap(Airport::getId, Airport::getName));

		Iterator<String[]> iterator = routes.iterator();

		while (iterator.hasNext()) {
			boolean isPresent = false;
			String[] row = iterator.next();
			if (aps.containsKey(StringManipulator.preventNForLong(row[3]))
					&& aps.containsKey(StringManipulator.preventNForLong(row[5]))) {
				isPresent = true;
			}
			if (isPresent == false) {
				iterator.remove();
			}
		}

		return routes;

	}

	private RouteDto bindParams(String[] route) {
		RouteDto routeDto = new RouteDto();

		routeDto.setAirline(StringManipulator.removeQuotes(route[0]));
		routeDto.setAirlineId(StringManipulator.preventNForLong(route[1]));

		Long sourceAirportId = StringManipulator.preventNForLong(route[3]);
		Airport sourceAirport = airportRepository.findById(sourceAirportId).orElse(null);
		routeDto.setSourceAirportDto(airportMapper.toDto(sourceAirport));

		Long destinationAirportId = StringManipulator.preventNForLong(route[5]);
		Airport destinationAirport = airportRepository.findById(destinationAirportId).orElse(null);
		routeDto.setDestinationAirportDto(airportMapper.toDto(destinationAirport));

		routeDto.setCodeshare(StringManipulator.removeQuotes(route[6]));
		routeDto.setStops(StringManipulator.preventNForInteger(route[7]));
		routeDto.setEquipment(StringManipulator.removeQuotes(route[8]));
		routeDto.setPrice(StringManipulator.preventNForDouble(route[9]));

		return routeDto;
	}

	public List<RouteDto> storeRoutes() throws IOException, RouteExistException {
		List<RouteDto> storedRoutes = new ArrayList<>();
		List<String[]> routes = getRouteWithExistingAirport(airportRepository.findAll());

		for (String[] row : routes) {
			RouteDto routeDto = bindParams(row);
			storedRoutes.add(store(routeDto));
		}

		return storedRoutes;
	}

}
