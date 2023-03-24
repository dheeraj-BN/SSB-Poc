package com.SecureSeat.Booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.service.UserServiceImpl;

@RestController
public class AdminController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	
	@PostMapping("api/addUser/{id}")
	public String addUser(@PathVariable int id) {
		
		userServiceImpl.addUser(id);
		
		return "SUCCESS";
		
		
	}
	

}
