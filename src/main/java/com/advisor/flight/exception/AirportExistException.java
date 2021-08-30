package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class AirportExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5301524444357049472L;

	public AirportExistException() {
		super(ExceptionConstants.AIRPORT_EXIST);
	}

	public static String getExceptionMessage() {
		return new AirportExistException().getMessage();
	}
}
