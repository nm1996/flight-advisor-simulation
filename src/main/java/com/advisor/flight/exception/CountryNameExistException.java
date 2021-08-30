package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class CountryNameExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2684365526251840856L;

	public CountryNameExistException() {
		super(ExceptionConstants.COUNTRY_NAME_EXIST);
	}

	public static String getExceptionMessage() {
		return new CountryNameExistException().getMessage();
	}
}
