package com.advisor.flight.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.UserDto;
import com.advisor.flight.entity.Role;
import com.advisor.flight.entity.User;
import com.advisor.flight.exception.LoginException;
import com.advisor.flight.exception.RoleNotExistException;
import com.advisor.flight.exception.UserNotExistException;
import com.advisor.flight.exception.UserUsernameExistException;
import com.advisor.flight.mapper.UserMapper;
import com.advisor.flight.repository.RoleRepository;
import com.advisor.flight.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private UserMapper userMapper;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userMapper = userMapper;
	}

	public List<UserDto> getAll() {
		return this.userRepository.findAll().stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
	}

	public UserDto store(UserDto userDto)
			throws UserUsernameExistException, RoleNotExistException, NoSuchAlgorithmException {

		Role role = roleRepository.findById(userDto.getRoleDto().getId())
				.orElseThrow(() -> new RoleNotExistException());
		User user = userMapper.toEntity(userDto);
		user.setSalt(createSalt());
		user.setPassword(user.getSalt() + "_" + hashPassword(user.getPassword()));
		user.setRole(role);

		Optional<User> existingUser = this.userRepository.findByUserName(user.getUserName());
		if (existingUser.isPresent()) {
			throw new UserUsernameExistException();
		}

		return userMapper.toDto(this.userRepository.save(user));
	}

	public UserDto update(UserDto userDto)
			throws RoleNotExistException, UserNotExistException, UserUsernameExistException {
		Role role = roleRepository.findById(userDto.getRoleDto().getId())
				.orElseThrow(() -> new RoleNotExistException());
		Optional<User> existingUser = userRepository.findById(userDto.getId());
		if (existingUser.isEmpty()) {
			throw new UserNotExistException();
		}
		User sameUser = userRepository.findByUserName(userDto.getUserName()).orElse(null);
		User user = userMapper.toEntity(userDto);

		if (!user.equals(sameUser)) {
			throw new UserUsernameExistException();
		}

		user.setRole(role);
		return userMapper.toDto(userRepository.save(user));
	}

	public UserDto find(Long id) throws UserNotExistException {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException());

		return userMapper.toDto(user);
	}

	public void delete(Long id) throws UserNotExistException {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException());
		userRepository.delete(user);
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes());
		byte[] bytes = m.digest();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return s.toString();
	}

	public UserDto login(String username, String password) throws NoSuchAlgorithmException, LoginException {
		String salt = userRepository.getSaltByUserName(username).orElse(null);
		User user = userRepository.findByUserNameAndPassword(username, salt + "_" + hashPassword(password))
				.orElseThrow(() -> new LoginException());
		return userMapper.toDto(user);
	}

	private String createSalt() {
		String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(5);

		for (int i = 0; i < 5; i++) {
			int index = (int) (data.length() * Math.random());
			sb.append(data.charAt(index));
		}

		return sb.toString();
	}
}
