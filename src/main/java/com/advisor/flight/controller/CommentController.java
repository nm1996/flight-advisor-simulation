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

import com.advisor.flight.dto.CommentDto;
import com.advisor.flight.exception.CityNotExistException;
import com.advisor.flight.exception.CommentNotExist;
import com.advisor.flight.exception.UserNotExistException;
import com.advisor.flight.exception.UserUsernameExistException;
import com.advisor.flight.service.CommentService;
import com.advisor.flight.utils.ExceptionContainer;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping(value = "/all")
	public List<CommentDto> all() {
		return commentService.getAll();
	}

	@GetMapping(value = "/{id}")
	public CommentDto find(@PathVariable Long id) throws CommentNotExist {
		return commentService.findById(id);
	}

	@PostMapping(value = "/store")
	public CommentDto store(@RequestBody @Valid CommentDto commentDto)
			throws UserNotExistException, CityNotExistException {
		return commentService.store(commentDto);
	}

	@PutMapping(value = "/update")
	public CommentDto update(@RequestBody @Valid CommentDto commentDto)
			throws CommentNotExist, UserNotExistException, CityNotExistException {
		return commentService.update(commentDto);
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) throws CommentNotExist {
		commentService.delete(id);
	}

	@ExceptionHandler(UserNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleUserNotExistException() {
		return new ExceptionContainer(UserUsernameExistException.getExceptionMessage());
	}

	@ExceptionHandler(CityNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCityNotExistException() {
		return new ExceptionContainer(CityNotExistException.getExceptionMessage());
	}

	@ExceptionHandler(CommentNotExist.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionContainer handleCommentNotExistException() {
		return new ExceptionContainer(CommentNotExist.getExceptionMessage());
	}

}
