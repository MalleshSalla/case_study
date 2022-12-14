package com.cg.hrms.jwt.mongodb.exception;
/**Custom exception class*/
@SuppressWarnings("serial")
public class InvalidTokenException extends Exception {

	/**
	 * @param message
	 */
	public InvalidTokenException(String message){
		super(message);
	}
}