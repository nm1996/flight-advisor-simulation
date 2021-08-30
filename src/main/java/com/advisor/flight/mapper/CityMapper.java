package com.advisor.flight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.advisor.flight.dto.CityDto;
import com.advisor.flight.entity.City;

@Mapper
public interface CityMapper {

	@Mappings({ @Mapping(target = "countryDto", source = "country"),
			@Mapping(target = "commentsDto", source = "comments") })
	CityDto toDto(City city);

	@Mappings({ @Mapping(target = "country", source = "countryDto"),
			@Mapping(target = "comments", source = "commentsDto") })
	City toEntity(CityDto cityDto);

}
