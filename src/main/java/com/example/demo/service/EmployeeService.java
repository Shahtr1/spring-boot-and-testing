package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployee();
	
	public Employee getEmployeeByid(Long id)  throws ResourceNotFoundException;
	
	public Employee createEmployee(Employee employee);
	
	public Employee updateEmployee(long id, Employee employee) throws ResourceNotFoundException;
	
	public Map<String, Boolean> deleteEmployee(long id) throws ResourceNotFoundException;
	

}
