package com.advisor.flight.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserDto {

	private Long id;

	@NotNull
	@NotBlank
	@JsonProperty("firstname")
	private String firstName;

	@NotNull
	@NotBlank
	@JsonProperty("lastname")
	private String lastName;

	@NotNull
	@NotBlank
	@JsonProperty("username")
	private String userName;

	@NotNull
	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String salt;

	@NotNull
	@JsonProperty("role")
	private RoleDto roleDto;

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(Long id, String firstName, String lastName, String userName, String password, String salt,
			RoleDto roleDto) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.roleDto = roleDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}

}
