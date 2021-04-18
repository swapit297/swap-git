package com.infosys.employeemanager;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.infosys.employeemanager.service.EmployeeServiceImpl;

@SpringBootApplication
public class EmployeeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagerApplication.class, args);
	}
/*
	@Autowired
	private EmployeeServiceImpl esi;
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException
	{
		//esi.sendEmail("swapit297@gmail.com", "Hello Spring Boot Email", "Spring Boot Email");
		esi.sendEmailWithAttachment("swapit297@gmail.com", "New Spring Boot Email With Attachment", "Spring Boot Email","C:\\Users\\Swapnil\\Desktop\\pom.docx");
	}*/
}
