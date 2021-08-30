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

import com.advisor.flight.dto.RoleDto;
import com.advisor.flight.exception.RoleNameExistException;
import com.advisor.flight.exception.RoleNotExistException;
import com.advisor.flight.service.RoleService;
import com.advisor.flight.utils.ExceptionContainer;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	private RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping(value = "/store")
	public RoleDto store(@RequestBody @Valid RoleDto roleDto) throws RoleNameExistException {
		return roleService.store(roleDto);
	}

	@GetMapping(value = "/all")
	public List<RoleDto> all() {
		return roleService.getAll();
	}

	@GetMapping(value = "/{id}")
	public RoleDto findById(@PathVariable Long id) throws RoleNotExistException {
		return roleService.find(id);
	}

	@PutMapping(value = "/update")
	public RoleDto update(@RequestBody @Valid RoleDto roleDto) throws RoleNotExistException, RoleNameExistException {
		return roleService.update(roleDto);
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) throws RoleNotExistException {
		roleService.delete(id);
	}
	

	@ExceptionHandler(RoleNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleRoleNotExistException() {
		return new ExceptionContainer(RoleNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(RoleNameExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionContainer handleRoleExistException() {
		return new ExceptionContainer(RoleNameExistException.getExceptionMessage());
	}

}
