package com.advisor.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.advisor.flight.dto.FlightDto;
import com.advisor.flight.exception.AirportNotExistException;
import com.advisor.flight.exception.CityNotExistException;
import com.advisor.flight.service.FlightService;
import com.advisor.flight.utils.ExceptionContainer;

@RestController
@RequestMapping(value = "/flight")
public class FlightController {

	private FlightService graphService;

	@Autowired
	public FlightController(FlightService graphService) {
		this.graphService = graphService;
	}

	@GetMapping(value = "/source/{cityId}/destination/{destinationId}")
	public FlightDto graphs(@PathVariable("cityId") Long citySourceId,
			@PathVariable("destinationId") Long destinationId) throws AirportNotExistException, CityNotExistException {
		return graphService.getFlight(citySourceId, destinationId);
	}

	@ExceptionHandler(AirportNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleAirportNotExistException() {
		return new ExceptionContainer(AirportNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(CityNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCitytNotExistException() {
		return new ExceptionContainer(CityNotExistException.getExceptionMessage());
	}

}
