package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class CityNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 993324761708291277L;

	public CityNotExistException() {
		super(ExceptionConstants.CITY_NOT_EXIST);
	}

	public static String getExceptionMessage() {
		return new CityNotExistException().getMessage();
	}
}
