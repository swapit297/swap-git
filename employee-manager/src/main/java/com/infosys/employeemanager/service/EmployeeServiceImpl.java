package com.infosys.employeemanager.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.infosys.employeemanager.TwilioConfiguration;
import com.infosys.employeemanager.exception.EmptyEmployeeException;
import com.infosys.employeemanager.exception.NoElementInDatabaseException;
import com.infosys.employeemanager.exception.UserNotFoundException;
import com.infosys.employeemanager.model.Employee;
import com.infosys.employeemanager.model.SmsRequest;
import com.infosys.employeemanager.repository.EmployeeRepository;
import com.sun.mail.handlers.multipart_mixed;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;



@Service
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	public Employee addEmployee(Employee employee)
	{
		
		//System.out.println("In Add Service");
		if(employee.getName().equals(""))
		{
			throw new EmptyEmployeeException("Please Do Enter Employee With Valid Credentials");
		}
		employee.setEmployeeCode(UUID.randomUUID().toString()); //Setting random value as Employee Code
		return employeeRepo.save(employee);
	}
	
	public List<Employee> findAllEmployee()
	{
		List<Employee> listEmployee=employeeRepo.findAll();
		if(listEmployee.isEmpty())
		{
			throw new NoElementInDatabaseException("Sorry Emty Database");
		}
		return listEmployee;
	}
	
	public Employee updateEmployee(Employee employee)
	{
		
		return employeeRepo.save(employee);
		
	}
	
	public void deleteEmployee(Long id)
	{
		System.out.println("Delete");
		//employeeRepo.deleteEmployeeById(id);
		employeeRepo.deleteById(id);
	}

	@Override
	public Employee findEmployeeById(Long id) {
		
		return  employeeRepo.findEmployeeById(id)
					.orElseThrow(() -> new UserNotFoundException("User By Id "+id+" Was Not Found"));
	}
	
	
	
	
	//************************************************************
	
	@Autowired
	private JavaMailSender mailSender;
	public void sendEmail(String email,String body, String subject)
	{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(email);
		message.setText(body);
		message.setSubject(subject);
		message.setFrom("swabaw297@gmail.com");
		
		mailSender.send(message);
		System.out.println("Mail Sent");
	}
	
	public void sendEmailWithAttachment(String email,String body, String subject,String attachment) throws MessagingException
	{
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
		
		mimeMessageHelper.setFrom("swabaw297@gmail.com");
		mimeMessageHelper.setTo(email);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
		FileSystemResource fileSystem= new FileSystemResource(new File(attachment));
		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
		mailSender.send(message);
		
		System.out.println("Mail Sent With Attachment");
	}

	
	
	//*************************************TWILIO SMS********************
	
	@Autowired
	private TwilioConfiguration twilioConfiguration;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public void sendSms(SmsRequest smsRequest) 
	{
		
		if(isPhoneNumberValid(smsRequest.getPhoneNumber()))
		{
			PhoneNumber fromNumber= new PhoneNumber(twilioConfiguration.getTrialNumber());
			PhoneNumber toNumber=new PhoneNumber(smsRequest.getPhoneNumber());
			String message=smsRequest.getMessage();
			MessageCreator messageCreator=Message.creator(toNumber, fromNumber, message);
			messageCreator.create();
			LOGGER.info("Send SMS "+smsRequest);
		}
		else
		{
			throw new IllegalArgumentException("Phone Number:- "+smsRequest.getPhoneNumber()+" Is Not Valid Number");
		}
		
	}
	
	public boolean isPhoneNumberValid(String phoneNumber)
	{
		return true;
	}
	
}
