package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class EmployeeControllerTest {
	
	@Autowired
    MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	void getAllEmployee() throws Exception {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("Shahrukh", "Tramboo", "tramboos123@gmail.com"));
		empList.add(new Employee("Urvashi", "Tramboo", "urvashi123@gmail.com"));
		when(employeeService.getAllEmployee()).thenReturn(empList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employees")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}
	
	@Test
	void createEmployee() throws Exception {
		Employee emp = new Employee("renuka", "manzoor", "renu@gmail.com");
		when(employeeService.createEmployee(any(Employee.class))).thenReturn(emp);
		ObjectMapper objectMapper = new ObjectMapper();
		String empJSON = objectMapper.writeValueAsString(emp);
		
		ResultActions result = mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(empJSON)
		);
		
		result.andExpect(status().is(200))
			.andExpect(jsonPath("$.firstName").value("renuka"))
			.andExpect(jsonPath("$.lastName").value("manzoor"));
		
	}
	
}

















