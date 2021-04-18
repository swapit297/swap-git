package com.infosys.employeemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException unfe)
	{
		//System.out.println("In User Not Found Exception" +unfe.getMessage());
		return new ResponseEntity<Object>(unfe.getMessage(),HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(EmptyEmployeeException.class)
	public ResponseEntity<Object> emptyEmployeeException(EmptyEmployeeException eee)
	{
		//System.out.println("In User Not Found Exception" +unfe.getMessage());
		return new ResponseEntity<Object>(eee.getMessage(),HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(NoElementInDatabaseException.class)
	public ResponseEntity<Object> emptyDatabaseException(NoElementInDatabaseException nede)
	{
		//System.out.println("In User Not Found Exception" +unfe.getMessage());
		return new ResponseEntity<Object>(nede.getMessage(),HttpStatus.NOT_FOUND);
	
	}
	
}
