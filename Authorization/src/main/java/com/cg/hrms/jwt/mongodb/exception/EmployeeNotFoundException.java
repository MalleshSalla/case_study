package com.cg.hrms.jwt.mongodb.exception;
@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {

	public EmployeeNotFoundException()
	{
		//Default Constructor
	}
	
	public EmployeeNotFoundException(String message)
	{
		super(message);
	}



}