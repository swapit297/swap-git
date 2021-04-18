package com.infosys.employeemanager.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.employeemanager.exception.EmptyEmployeeException;
import com.infosys.employeemanager.exception.NoElementInDatabaseException;
import com.infosys.employeemanager.model.Employee;
import com.infosys.employeemanager.model.SmsRequest;
import com.infosys.employeemanager.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmployeeController
{
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
	{
		
		System.out.println("Employee "+employee);
		//Employee emp=employeeService.addEmployee(employee);
		//return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> findAllEmployee()
	{
		List<Employee> listEmployee=employeeService.findAllEmployee();
	
		
		return new ResponseEntity<>(listEmployee,HttpStatus.OK);
		// OR return new ResponseEntity<List<Employee>>(listEmployee,HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee)
	{
		
		Employee emp=employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) //ResponseEntity<?> ==> 
	{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) 
	{
		Employee emp=employeeService.findEmployeeById(id);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	//*******************************SEND EMAIL************************
	@GetMapping("/sendEmail")
	public ResponseEntity<?> sendEmail()/*@PathVariable("email") String email,
			@PathVariable("subject") String subject,@PathVariable("body") String body,@PathVariable("attachment") String attachment) */throws MessagingException 
	{
		
		String email="swapit297@gmail.com";
		String body="New Spring Boot Email With Attachment"; 
		String subject="Spring Boot Email";
		String attachment="C:\\Users\\Swapnil\\Desktop\\pom.docx";
		System.out.println("Hello");
		employeeService.sendEmailWithAttachment(email, body, subject, attachment);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//**************************SEND SMS--TWILIO API*****************
	
	@PostMapping("/sendSms")
	public void sendSms(@RequestBody SmsRequest smsRequest)
	{
		//SmsRequest smsRequest=new SmsRequest("+919309854740", "Hello First Message");
		//System.out.println("In SMS " + smsRequest.getPhoneNumber()+" "+smsRequest.getMessage());
		employeeService.sendSms(smsRequest);
	}

}
