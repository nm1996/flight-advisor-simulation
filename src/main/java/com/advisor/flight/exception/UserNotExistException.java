package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class UserNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030083032023927618L;

	public UserNotExistException() {
		super(ExceptionConstants.USER_NOT_EXIST);
	}

	public static String getExceptionMessage() {
		return new UserNotExistException().getMessage();
	}
}
