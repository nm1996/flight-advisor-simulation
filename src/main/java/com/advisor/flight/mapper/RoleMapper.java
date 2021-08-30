package com.advisor.flight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.advisor.flight.dto.RoleDto;
import com.advisor.flight.entity.Role;

@Mapper
public interface RoleMapper {

	@Mapping(target = "name", source = "name") // kada oboje imaju isto, ne treba navoditi
	RoleDto toDto(Role role);

	@Mapping(target = "name", source = "name")
	Role toEntity(RoleDto roleDTO);
}
