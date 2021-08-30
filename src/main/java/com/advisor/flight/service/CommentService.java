package com.advisor.flight.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.CommentDto;
import com.advisor.flight.entity.City;
import com.advisor.flight.entity.Comment;
import com.advisor.flight.entity.User;
import com.advisor.flight.exception.CityNotExistException;
import com.advisor.flight.exception.CommentNotExist;
import com.advisor.flight.exception.UserNotExistException;
import com.advisor.flight.mapper.CommentMapper;
import com.advisor.flight.repository.CityRepository;
import com.advisor.flight.repository.CommentRepository;
import com.advisor.flight.repository.UserRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserRepository userRepository;
	private CityRepository cityRepository;
	private CommentMapper commentMapper;

	@Autowired
	public CommentService(CommentRepository commentRepository, UserRepository userRepository,
			CityRepository cityRepository, CommentMapper commentMapper) {
		this.userRepository = userRepository;
		this.cityRepository = cityRepository;
		this.commentRepository = commentRepository;
		this.commentMapper = commentMapper;
	}

	public List<CommentDto> getAll() {
		return commentRepository.findAll().stream().map(comment -> commentMapper.toDto(comment))
				.collect(Collectors.toList());
	}

	public CommentDto findById(Long id) throws CommentNotExist {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotExist());
		return commentMapper.toDto(comment);
	}

	public CommentDto store(CommentDto commentDto) throws UserNotExistException, CityNotExistException {
		User user = userRepository.findById(commentDto.getUserDto().getId())
				.orElseThrow(() -> new UserNotExistException());
		City city = cityRepository.findById(commentDto.getCityDto().getId())
				.orElseThrow(() -> new CityNotExistException());

		Comment comment = commentMapper.toEntity(commentDto);
		comment.setCity(city);
		comment.setUser(user);

		return commentMapper.toDto(commentRepository.save(comment));
	}

	public CommentDto update(CommentDto commentDto)
			throws CommentNotExist, UserNotExistException, CityNotExistException {
		commentRepository.findById(commentDto.getId()).orElseThrow(() -> new CommentNotExist());
		User user = userRepository.findById(commentDto.getUserDto().getId())
				.orElseThrow(() -> new UserNotExistException());
		City city = cityRepository.findById(commentDto.getCityDto().getId())
				.orElseThrow(() -> new CityNotExistException());

		Comment comment = commentMapper.toEntity(commentDto);
		comment.setCity(city);
		comment.setUser(user);

		return commentMapper.toDto(commentRepository.save(comment));
	}

	public void delete(Long id) throws CommentNotExist {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotExist());
		commentRepository.delete(comment);

	}

}
