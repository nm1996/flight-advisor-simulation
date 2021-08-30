package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class RouteExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3290911239181261251L;

	public RouteExistException() {
		super(ExceptionConstants.ROUTE_EXIST);
	}

	public static String getExceptionMessage() {
		return new RouteExistException().getMessage();
	}
}
