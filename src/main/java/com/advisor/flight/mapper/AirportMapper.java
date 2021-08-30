package com.advisor.flight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.advisor.flight.dto.AirportDto;
import com.advisor.flight.entity.Airport;

@Mapper
public interface AirportMapper {

	@Mapping(target = "cityDto", source = "city")
	AirportDto toDto(Airport airport);

	@Mapping(target = "city", source = "cityDto")
	Airport toEntity(AirportDto airportDto);
}
