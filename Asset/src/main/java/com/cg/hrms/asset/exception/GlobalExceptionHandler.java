package com.cg.hrms.asset.exception;


import java.time.LocalDateTime;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.hrms.asset.model.ErrorDetails;



@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler({RoleNotFoundException.class})
	public ResponseEntity<ErrorDetails> handleProductNotFound(RoleNotFoundException ex){
		ErrorDetails error=new ErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("id doesn't exist....");
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
	}
	
	//this method is responsible for handling all the internal server errors from class cartnptfoundexception
	@ExceptionHandler({AssetNotFoundException.class})
	public ResponseEntity<ErrorDetails> handleEmployeeIdNotFound(AssetNotFoundException cart){
		ErrorDetails error=new ErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(cart.getMessage());
		error.setReason("id doesn't exist....");
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ErrorDetails> handleBadRequest(MethodArgumentTypeMismatchException ex){
		ErrorDetails error=new ErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(ex.getMessage());
		error.setReason("Wrong url/method typed ....");
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ErrorDetails> handleMethodNotSupportException(HttpRequestMethodNotSupportedException ex){
		ErrorDetails error=new ErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
		error.setMessage(ex.getMessage());
		error.setReason("Wrong method selected....");
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleException(Exception ex){
			Map<String,Object> body=new LinkedHashMap<String, Object>();
		body.put("timestamp",LocalDateTime.now());
		body.put("Status",HttpStatus.NOT_ACCEPTABLE);
		body.put("Message",ex.getMessage());
		body.put("Reason","Wrong url typed....");
		return new ResponseEntity<Object>(body,HttpStatus.NOT_ACCEPTABLE);
	}
}