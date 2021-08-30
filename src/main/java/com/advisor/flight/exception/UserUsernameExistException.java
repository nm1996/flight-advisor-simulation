package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class UserUsernameExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2637035189262566182L;

	public UserUsernameExistException() {
		super(ExceptionConstants.USER_USERNAME_EXIST);
	}
	
	public static String getExceptionMessage() {
		return new UserUsernameExistException().getMessage();
	}
}
