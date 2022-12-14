package com.cg.hrms.employee.exception;
@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException(String msg)
	{
		super(msg);
	}
}
