package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class CityExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7930869462914341537L;

	public CityExistException() {
		super(ExceptionConstants.CITY_SAME_EXIST);
	}

	public static String getExceptionMessage() {
		return new CityExistException().getMessage();
	}
}
