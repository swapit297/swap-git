package com.infosys.employeemanager.model;

public class SmsRequest 
{

	private final String phoneNumber;
	private final String message;
	
	
	public SmsRequest(String phoneNumber, String message) {
		super();
		this.phoneNumber = phoneNumber;
		this.message = message;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public String getMessage() {
		return message;
	}


	@Override
	public String toString() {
		return "SmsRequest [phoneNumber=" + phoneNumber + ", message=" + message + "]";
	}
	  
	
	
}
