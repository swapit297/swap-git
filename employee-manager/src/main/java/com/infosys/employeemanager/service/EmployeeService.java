package com.infosys.employeemanager.service;

import java.util.List;

import javax.mail.MessagingException;

import com.infosys.employeemanager.model.Employee;
import com.infosys.employeemanager.model.SmsRequest;

public interface EmployeeService 
{
	
	public Employee addEmployee(Employee employee);
	public List<Employee> findAllEmployee();
	public Employee updateEmployee(Employee employee);
	public void deleteEmployee(Long id);
	public Employee findEmployeeById(Long id);
	public void sendEmailWithAttachment(String email,String body, String subject,String attachment) throws MessagingException;
	void sendSms(SmsRequest smsRequest);
}
