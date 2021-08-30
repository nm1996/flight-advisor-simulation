package com.advisor.flight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.advisor.flight.dto.RouteDto;
import com.advisor.flight.entity.Route;

@Mapper
public interface RouteMapper {

	@Mappings({ @Mapping(target = "sourceAirportDto", source = "sourceAirport"),
			@Mapping(target = "destinationAirportDto", source = "destinationAirport") })
	RouteDto toDto(Route route);

	@Mappings({ @Mapping(target = "sourceAirport", source = "sourceAirportDto"),
			@Mapping(target = "destinationAirport", source = "destinationAirportDto") })
	Route toEntity(RouteDto routeDto);
}
