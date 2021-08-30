package com.advisor.flight.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CityDto {

	private Long id;

	@NotNull
	@NotBlank
	@JsonProperty
	private String name;

	@NotNull
	@NotBlank
	@JsonProperty
	private String description;

	@NotNull
	@JsonProperty("country")
	private CountryDto countryDto;

	@JsonProperty("comments")
	private List<CommentDto> commentsDto;

	public CityDto() {
	}

	public CityDto(Long id, String name, String description, CountryDto countryDto, List<CommentDto> commentsDto) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.countryDto = countryDto;
		this.commentsDto = commentsDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CountryDto getCountryDto() {
		return countryDto;
	}

	public void setCountryDto(CountryDto countryDto) {
		this.countryDto = countryDto;
	}

	public List<CommentDto> getCommentsDto() {
		return commentsDto;
	}

	public void setCommentsDto(List<CommentDto> commentsDto) {
		this.commentsDto = commentsDto;
	}

}
