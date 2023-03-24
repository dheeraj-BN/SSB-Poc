package com.SecureSeat.Booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/test")
	public String test() {
		return "Hello Test";
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return employeeService.getEmployee(id);
	}
	
	@GetMapping("/employeeList")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployee();
	}
}
