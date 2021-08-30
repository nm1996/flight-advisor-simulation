package com.advisor.flight.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.advisor.flight.dto.AirportDto;
import com.advisor.flight.exception.AirportExistException;
import com.advisor.flight.service.AirportService;
import com.advisor.flight.utils.ExceptionContainer;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

	private AirportService airportService;

	@Autowired
	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}

	@GetMapping(value = "/all")
	public List<AirportDto> all() {
		return airportService.getAll();
	}

	@PostMapping(value = "/store")
	public List<AirportDto> collectListData() throws IOException, AirportExistException {
		return airportService.storeAirports();
	}

	@ExceptionHandler(AirportExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleAirportExistException() {
		return new ExceptionContainer(AirportExistException.getExceptionMessage());
	}
}
