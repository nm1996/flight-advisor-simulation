package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class RoutesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -617477494789507838L;

	
	public RoutesNotExistException() {
		super(ExceptionConstants.ROUTES_NOT_EXIST);
	}
	
	public static String getExceptionMessage() {
		return new RoutesNotExistException().getMessage();
	}
}
