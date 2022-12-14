package com.cg.hrms.jwt.mongodb.exception;
@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException(String msg)
	{
		super(msg);
	}
}