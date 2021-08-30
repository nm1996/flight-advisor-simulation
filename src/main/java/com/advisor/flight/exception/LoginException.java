package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7645034567761082870L;

	public LoginException() {
		super(ExceptionConstants.LOGIN_CREDENTIALS_NOT_EXIST);
	}

	public static String getExceptionMessage() {
		return new LoginException().getMessage();
	}

}
