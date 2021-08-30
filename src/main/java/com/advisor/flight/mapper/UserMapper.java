package com.advisor.flight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.advisor.flight.dto.UserDto;
import com.advisor.flight.entity.User;

@Mapper
public interface UserMapper {

	@Mapping(target = "roleDto", source = "role")
	UserDto toDto(User user);

	@Mapping(target = "role", source = "roleDto")
	User toEntity(UserDto userDto);
}
