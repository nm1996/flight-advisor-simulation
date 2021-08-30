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

import com.advisor.flight.dto.CountryDto;
import com.advisor.flight.exception.CountryNameExistException;
import com.advisor.flight.exception.CountryNotExistException;
import com.advisor.flight.service.CountryService;
import com.advisor.flight.utils.ExceptionContainer;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

	private CountryService countryService;

	@Autowired
	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping(value = "/all")
	public List<CountryDto> all() {
		return countryService.getAll();
	}

	@GetMapping(value = "/{id}")
	public CountryDto findById(@PathVariable Long id) throws CountryNotExistException {
		return countryService.find(id);
	}

	@PostMapping(value = "/store")
	public CountryDto store(@RequestBody @Valid CountryDto countryDto) throws CountryNameExistException {
		return countryService.store(countryDto);
	}

	@PutMapping(value = "/update")
	public CountryDto update(@RequestBody @Valid CountryDto countryDto)
			throws CountryNameExistException, CountryNotExistException {
		return countryService.update(countryDto);
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) throws CountryNotExistException {
		countryService.delete(id);
	}

	@ExceptionHandler(CountryNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCountryNotExistException() {
		return new ExceptionContainer(CountryNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(CountryNameExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCountryExistException() {
		return new ExceptionContainer(CountryNameExistException.getExceptionMessage());
	}
}
