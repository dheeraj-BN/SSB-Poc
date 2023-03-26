package com.SecureSeat.Booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.service.UserServiceImpl;

@RestController
public class AdminController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	

	
	@PostMapping("api/addUser/{id}")
	public String addUser(@PathVariable int id) {
		
		
		
		return userServiceImpl.addUser(id);
		
		
	}
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleMyException(Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred: " + ex.getMessage());
	    }
	
	

}
