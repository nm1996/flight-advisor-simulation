package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class RoleNameExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6914083047578772175L;

	public RoleNameExistException() {
		super(ExceptionConstants.ROLE_NAME_EXIST);
	}

	public static String getExceptionMessage() {
		return new RoleNameExistException().getMessage();
	}
}
