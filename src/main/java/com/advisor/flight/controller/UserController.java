package com.advisor.flight.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.advisor.flight.dto.UserDto;
import com.advisor.flight.exception.LoginException;
import com.advisor.flight.exception.RoleNotExistException;
import com.advisor.flight.exception.UserNotExistException;
import com.advisor.flight.exception.UserUsernameExistException;
import com.advisor.flight.service.UserService;
import com.advisor.flight.utils.ExceptionContainer;
import com.advisor.flight.utils.LoginData;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "all")
	public List<UserDto> all() {
		return userService.getAll();
	}

	@PostMapping(value = "store")
	public UserDto store(@RequestBody @Valid UserDto userDto)
			throws UserUsernameExistException, RoleNotExistException, NoSuchAlgorithmException {
		return userService.store(userDto);
	}

	@GetMapping(value = "/{id}")
	public UserDto findById(@PathVariable Long id) throws UserNotExistException {
		return userService.find(id);
	}

	@PostMapping(value = "/login")
	public UserDto login(@RequestBody LoginData loginData) throws NoSuchAlgorithmException, LoginException {
		return userService.login(loginData.getUsername(), loginData.getPassword());
	}

	@PutMapping(value = "/update")
	public UserDto update(@RequestBody @Valid UserDto userDto)
			throws RoleNotExistException, UserNotExistException, UserUsernameExistException {
		return userService.update(userDto);
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) throws UserNotExistException {
		userService.delete(id);
	}

	@ExceptionHandler(UserUsernameExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionContainer handleUsernameExistException() {
		return new ExceptionContainer(UserUsernameExistException.getExceptionMessage());
	}

	@ExceptionHandler(RoleNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleRoleNotExistException() {
		return new ExceptionContainer(RoleNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(UserNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleUserNotExistException() {
		return new ExceptionContainer(UserNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(LoginException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleLoginException() {
		return new ExceptionContainer(LoginException.getExceptionMessage());
	}

}
