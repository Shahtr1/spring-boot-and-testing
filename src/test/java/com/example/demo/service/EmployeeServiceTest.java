package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@AfterEach
    void tearDown(){
		employeeRepository.deleteAll();
	}
	
	@Test
	void getAllEmployee() {
		Employee emp = new Employee("shahrukh", "tramboo", "tramboos123@gmail.com");
		employeeRepository.save(emp);
		
		EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
		
		List<Employee> empList = employeeService.getAllEmployee();
		System.out.println(">>>>> List >>>>>");
		System.out.println(empList);
		Employee lastEmp = empList.get(empList.size()-1);
		
		assertEquals(emp.getEmail(), lastEmp.getEmail());
		assertEquals(emp.getFirstName(), lastEmp.getFirstName());
		assertEquals(emp.getLastName(), lastEmp.getLastName());
		
	}
	
	@Test
	void createEmployee() {
		EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
		Employee emp = new Employee("faisal", "tramboo", "tramboos123@gmail.com");
		
		employeeService.createEmployee(emp);
		
		assertEquals(1.0, employeeRepository.count());
	}
	
}





