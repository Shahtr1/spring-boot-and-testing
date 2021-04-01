package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	 
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee getEmployeeByid(Long id) throws ResourceNotFoundException {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
	}

	@Override
	@Transactional
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public Employee updateEmployee(long id, Employee emp) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		
		employee.setEmail(emp.getEmail());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public Map<String, Boolean> deleteEmployee(long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		
		employeeRepository.delete(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
		
	}

	
	
	
	
	
	
	
	
	

}











