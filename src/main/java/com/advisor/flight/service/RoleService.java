package com.advisor.flight.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.RoleDto;
import com.advisor.flight.entity.Role;
import com.advisor.flight.exception.RoleNameExistException;
import com.advisor.flight.exception.RoleNotExistException;
import com.advisor.flight.mapper.RoleMapper;
import com.advisor.flight.repository.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
	private RoleMapper roleMapper;

	@Autowired
	public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
	}

	public RoleDto store(RoleDto roleDTO) throws RoleNameExistException {
		Optional<Role> existingRole = roleRepository.findByName(roleDTO.getName());
		if (existingRole.isPresent()) {
			throw new RoleNameExistException();
		}
		return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDTO)));
	}

	public List<RoleDto> getAll() {
		return roleRepository.findAll().stream().map(role -> roleMapper.toDto(role))
				.collect(Collectors.toList());
	}

	public RoleDto update(RoleDto roleDto) throws RoleNotExistException, RoleNameExistException {
		Role existingRole = roleRepository.findById(roleDto.getId()).orElseThrow(() -> new RoleNotExistException());
		Role sameRole = roleRepository.findByName(roleDto.getName()).orElse(null);
		if (sameRole != null) {
			if (!existingRole.equals(sameRole)) {
				throw new RoleNameExistException();
			}
		}
		return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
	}

	public RoleDto find(Long id) throws RoleNotExistException {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotExistException());
		return roleMapper.toDto(role);
	}

	public void delete(Long id) throws RoleNotExistException {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotExistException());
		roleRepository.delete(role);
	}

}
