package com.SecureSeat.Booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	
	@GetMapping("/user/{id}")
	public Optional<UserDeatils> findById(@PathVariable int id) {
		return loginService.findUserByUsername(id);
	}
	
	@GetMapping("/employee")
	public Employee findEmployeeByName(@RequestParam String email) {
		return loginService.findEmployeeByName(email);
	}
	
	@GetMapping("/login")
	public String loginres(@RequestParam String username,@RequestParam  String password,String role) {
		System.out.println(role);
		return loginService.loginResponse(username, password,role);
		
	}
	
	@GetMapping("/allEmps")
	public List<Employee> findAllEmps(){
		return loginService.findAllEmployees();
	}
}
