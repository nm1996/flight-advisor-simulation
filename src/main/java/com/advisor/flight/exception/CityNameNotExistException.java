package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class CityNameNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5432090675421223392L;

	public CityNameNotExistException() {
		super(ExceptionConstants.CITY_NAME_NOT_EXIST);
	}

	public static String getExceptionMessage() {
		return new CityNameNotExistException().getMessage();
	}
}
