package com.SecureSeat.Booking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
