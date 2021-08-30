package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class RoleNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5060340424753695193L;

	public RoleNotExistException() {
		super(ExceptionConstants.ROLE_NOT_EXIST);
	}
	
	public static String getExceptionMessage() {
		return new RoleNotExistException().getMessage();
	}
}
