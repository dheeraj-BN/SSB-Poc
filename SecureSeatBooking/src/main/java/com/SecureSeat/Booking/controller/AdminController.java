package com.SecureSeat.Booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.service.UserServiceImpl;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://10.191.80.120:3000")
public class AdminController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	

	
	@PostMapping("/addUser/{id}")
	public String addUser(@PathVariable int id) {
		
		return userServiceImpl.addUser(id);
	}
	
	
	@GetMapping("/validateToken/")
	public Employee validateTocken(@RequestParam String token) throws Exception {
		
		return userServiceImpl.validateTocken(token);
	}
	
	
	
	
	
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleMyException(Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred: " + ex.getMessage());
	    }
	
	

}