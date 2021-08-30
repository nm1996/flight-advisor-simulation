package com.advisor.flight.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDto {

	private Long id;

	@NotNull
	@NotBlank
	@JsonProperty
	private String description;

	@JsonProperty
	private Date createdAt;

	@JsonProperty
	private Date updatedAt;

	@JsonProperty("user")
	private UserDto userDto;

	@JsonProperty("city")
	private CityDto cityDto;

	public CommentDto() {
	}

	public CommentDto(Long id, String description, Date createdAt, Date updatedAt, UserDto userDto, CityDto cityDto) {
		super();
		this.id = id;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userDto = userDto;
		this.cityDto = cityDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public CityDto getCityDto() {
		return cityDto;
	}

	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}

}
