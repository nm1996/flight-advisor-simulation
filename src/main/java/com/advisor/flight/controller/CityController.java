package com.advisor.flight.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.advisor.flight.dto.CityDto;
import com.advisor.flight.exception.CityExistException;
import com.advisor.flight.exception.CityNameNotExistException;
import com.advisor.flight.exception.CityNotExistException;
import com.advisor.flight.exception.CountryNotExistException;
import com.advisor.flight.service.CityService;
import com.advisor.flight.utils.ExceptionContainer;

@RestController
@RequestMapping(value = "/city")
public class CityController {

	private CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping(value = "/all")
	public List<CityDto> all() {
		return cityService.getAll();
	}

	@GetMapping(value = { "/comments", "/comments/{num}" })
	public List<CityDto> withComments(@PathVariable(required = false) Integer num) {
		if (num != null) {
			return cityService.getAllWithLimitedComments(num);
		} else {
			return cityService.getAll();
		}
	}

	@GetMapping(value = { "/name/{name}", "/name/{name}/comments/{num}" })
	public List<CityDto> findByName(@PathVariable String name, @PathVariable(required = false) Integer num)
			throws CityNameNotExistException {
		if (num != null) {
			return cityService.getByNameWithLimitedComments(name, num);
		} else {
			return cityService.getByName(name);
		}
	}

	@GetMapping(value = "/{id}")
	public CityDto find(@PathVariable Long id) throws CityNotExistException {
		return cityService.find(id);
	}

	@PostMapping(value = "/store")
	public CityDto store(@RequestBody @Valid CityDto cityDto)
			throws CountryNotExistException, CityExistException, CityNameNotExistException {
		return cityService.store(cityDto);
	}

	@PutMapping(value = "/update")
	public CityDto update(@RequestBody @Valid CityDto cityDto)
			throws CityNotExistException, CountryNotExistException, CityExistException {
		return cityService.update(cityDto);
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) throws CityNotExistException {
		cityService.delete(id);
	}

	@ExceptionHandler(CityNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCityNotExistException() {
		return new ExceptionContainer(CityNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(CityExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionContainer handleCityNameExistException() {
		return new ExceptionContainer(CityExistException.getExceptionMessage());
	}

	@ExceptionHandler(CountryNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCountryNotExistException() {
		return new ExceptionContainer(CountryNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(CityNameNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCityNameNotExistException() {
		return new ExceptionContainer(CityNameNotExistException.getExceptionMessage());
	}

}
