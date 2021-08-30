package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class AirportNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5140554225412922973L;

	public AirportNotExistException() {
		super(ExceptionConstants.AIRPORTS_NOT_EXIST);
	}

	public static String getExceptionMessage() {
		return new AirportNotExistException().getMessage();
	}
}
