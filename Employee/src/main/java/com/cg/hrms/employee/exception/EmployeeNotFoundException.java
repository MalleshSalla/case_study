package com.cg.hrms.employee.exception;
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