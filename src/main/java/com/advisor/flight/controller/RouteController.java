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

import com.advisor.flight.dto.RouteDto;
import com.advisor.flight.exception.RouteExistException;
import com.advisor.flight.service.RouteService;
import com.advisor.flight.utils.ExceptionContainer;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

	private RouteService routeService;

	@Autowired
	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@GetMapping(value = "/all")
	public List<RouteDto> all() throws IOException {
		return routeService.getAll();
	}
	
	@PostMapping(value = "/store")
	public List<RouteDto> store() throws IOException, RouteExistException{
		return routeService.storeRoutes();
	}
	
	
	@ExceptionHandler(RouteExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionContainer handleRouteExistException() {
		return new ExceptionContainer(RouteExistException.getExceptionMessage());
	}
}
