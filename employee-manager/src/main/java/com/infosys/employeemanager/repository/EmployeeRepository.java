package com.infosys.employeemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.employeemanager.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	//void deleteEmployeeById(Long id);
	Optional<Employee> findEmployeeById(Long id); //Return Type Optional<Employee>(of Employee) == As Id may not be Present in Data Base
	//Employee findEmployeeById(Long id);
}
