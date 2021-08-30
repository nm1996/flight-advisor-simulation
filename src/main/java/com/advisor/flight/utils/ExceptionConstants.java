package com.advisor.flight.utils;

public class ExceptionConstants {

	public static final String ROLE_NOT_EXIST = "There is no role by provided id.";
	public static final String ROLE_NAME_EXIST = "Role with provided name exist.";

	public static final String USER_NOT_EXIST = "There is no user by provided id.";
	public static final String USER_USERNAME_EXIST = "User with provided username exist.";
	public static final String LOGIN_CREDENTIALS_NOT_EXIST = "There is no user by provided credentials.";

	public static final String COUNTRY_NOT_EXIST = "There is no country by provided id.";
	public static final String COUNTRY_NAME_EXIST = "Country with provided name exist.";

	public static final String CITY_NOT_EXIST = "There is no city by provided id.";
	public static final String CITY_NAME_NOT_EXIST = "City with provided name not exist.";
	public static final String CITY_SAME_EXIST = "There is city with provided name and country.";

	public static final String COMMENT_NOT_EXIST = "There is no comment by provided id.";

	public static final String AIRPORT_EXIST = "There is airport with provided id.";
	public static final String AIRPORTS_NOT_EXIST = "There are no airports for provided city.";

	public static final String ROUTE_EXIST = "There is same route.";
	
	public static final String ROUTES_NOT_EXIST = "There are no routes for provided city.";

	private ExceptionConstants() {
	};

}
