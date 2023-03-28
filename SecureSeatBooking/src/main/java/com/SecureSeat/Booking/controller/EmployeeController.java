package com.SecureSeat.Booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.service.EmployeeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PutMapping("/employee/change/password/{id}")
	public String changePassword(@PathVariable int id,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
		String message=employeeService.changePassword(id,oldPassword,newPassword);
		
		return message;
		
	}
	
	@GetMapping("/employee/booked/details/{id}")
	public BookingDetails bookedInfo(@PathVariable int id) {
		return employeeService.getEmpBookedInfo(id);
	}
	
	@GetMapping("/employee/next/booked/details/{id}")
	public List<BookingDetails> nextBookedInfo(@PathVariable int id) {
		return employeeService.getEmpBookedInfoBookedNext(id);
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
