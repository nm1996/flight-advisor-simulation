package com.advisor.flight.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDto {
	private Long id;

	@NotNull
	@NotBlank
	@JsonProperty
	private String name;

	public RoleDto() {
		// TODO Auto-generated constructor stub
	}

	public RoleDto(Long id, String name) {
		this.id = id;
		this.name = name;
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
}
