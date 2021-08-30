package com.advisor.flight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.advisor.flight.dto.CommentDto;
import com.advisor.flight.entity.Comment;

@Mapper
public interface CommentMapper {

	@Mappings({ @Mapping(target = "cityDto", source = "city"), @Mapping(target = "userDto", source = "user") })
	CommentDto toDto(Comment comment);

	@Mappings({ @Mapping(target = "city", source = "cityDto"), @Mapping(target = "user", source = "userDto") })
	Comment toEntity(CommentDto commentDto);
}
