package com.advisor.flight.exception;

import com.advisor.flight.utils.ExceptionConstants;

public class CommentNotExist extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8796386487663183255L;

	public CommentNotExist() {
		super(ExceptionConstants.COMMENT_NOT_EXIST);
	}

	public static String getExceptionMessage() {
		return new CommentNotExist().getMessage();
	}

}
