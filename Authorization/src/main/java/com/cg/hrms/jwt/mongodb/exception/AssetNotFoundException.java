package com.cg.hrms.jwt.mongodb.exception;
@SuppressWarnings("serial")
public class AssetNotFoundException extends Exception {

	public AssetNotFoundException()
	{
		//Default Constructor
	}
	
	public AssetNotFoundException(String message)
	{
		super(message);
	}



}