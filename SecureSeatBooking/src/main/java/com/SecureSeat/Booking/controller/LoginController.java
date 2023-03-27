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
//ss@CrossOrigin("http://10.191.80.118:3001")
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
	public String loginres(@RequestParam("email") String email, @RequestParam String password) {
		//System.out.println(role);
		//System.out.println(email);
		if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
			return "Enter valid Email id";
		} else {

			return loginService.loginResponse(email, password);
		}

	}

	@GetMapping("/allEmps")
	public List<Employee> findAllEmps() {
		return loginService.findAllEmployees();
	}
}