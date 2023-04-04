package com.SecureSeat.Booking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.service.SendSMS;
import com.SecureSeat.Booking.service.UserServiceImpl;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@Autowired
	private SendSMS smsImpl;
	
	
	@PostMapping("/addUser/{id}")
	public ResponseEntity<Map<String, String>> addUser(@PathVariable int id) {
		
		return userServiceImpl.addUser(id);
	}
	
	
	@PutMapping("/validateToken/")
	public Employee validateTocken(@RequestParam String token) throws Exception {
		
		return userServiceImpl.validateToken(token);
	}
	
	
	
	
	
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleMyException(Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred: " + ex.getMessage());
	    }
	
	@PostMapping("/addholiday")
	public String saveholiday(@RequestBody HolidayDetails holidayDetails) {
		System.out.println(holidayDetails);
		String s= userServiceImpl.addHolidays(holidayDetails);
		return s;
	}
	
	@GetMapping("/notRegistered")
	public List<Employee> listOfEmployeeNotRegistered(){
		
		return userServiceImpl.listOfEmployeeNotRegistered();
	}
	
	

}