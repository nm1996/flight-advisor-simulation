package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class CountryNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8583990548288534748L;

	public CountryNotExistException() {
		super(ExceptionConstants.COUNTRY_NOT_EXIST);
	}

	public static String getExceptionMessage() {
		return new CountryNotExistException().getMessage();
	}

}
