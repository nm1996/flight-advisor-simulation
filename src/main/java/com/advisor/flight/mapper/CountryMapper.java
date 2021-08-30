package com.advisor.flight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.advisor.flight.dto.CountryDto;
import com.advisor.flight.entity.Country;

@Mapper
public interface CountryMapper {

	@Mapping(target="name", source="name")
	CountryDto toDto(Country country);

	@Mapping(target="name", source="name")
	Country toEntity(CountryDto countryDto);
}
