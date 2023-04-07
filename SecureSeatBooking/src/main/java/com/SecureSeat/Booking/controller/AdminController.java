package com.SecureSeat.Booking.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.Configuration;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.service.SMSServiceConfiguration;
import com.SecureSeat.Booking.service.SendSMS;
import com.SecureSeat.Booking.service.Userservice;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private Userservice userService;
	
	
	@Autowired
	private SendSMS smsImpl;
	
	@Autowired
	private SMSServiceConfiguration  smsServiceConfiguration;
	
	/**

	This class defines the REST endpoints for the user and holiday services.
	It contains methods to add a user, validate a token, save a holiday, edit a holiday,
	retrieve a list of employees not registered, configure SMS service, and send SMS to admin.
	*/
	
	@PostMapping("/addUser/{id}")
	public ResponseEntity<Map<String, String>> addUser(@PathVariable int id) {
		// Method to add a new user
		return userService.addUser(id);
	}
	
	
	@PutMapping("/validateToken")
	public Employee validateTocken(@RequestParam String token) throws Exception {
		// Method to validate token
		return userService.validateToken(token);
	}
	
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleMyException(Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred: " + ex.getMessage());
	    }
	
	@PostMapping("/addholiday")
	public String saveholiday(@RequestBody HolidayDetails holidayDetails) {
		// Method to save holiday details
		String s= userService.addHolidays(holidayDetails);
		return s;
	}
	
	@GetMapping("/notRegistered")
	public List<Employee> listOfEmployeeNotRegistered(){
		// Method to retrieve a list of employees not registered
		return userService.listOfEmployeeNotRegistered();
	}
	
	@PutMapping("/configSMS")
	public void smsConfig(@RequestBody Configuration sconfiguration) {
		// Method to configure SMS service
		smsServiceConfiguration.SMSConfigsave(sconfiguration);
	}
	
	@PutMapping("/configEmail")
	public void emailConfig(@RequestBody Configuration sconfiguration) {
		// Method to configure SMS service
		smsServiceConfiguration.emailChange(sconfiguration);
	}
	
	@PutMapping("/confighours")
	public void hourConfig(@RequestBody Configuration sconfiguration) {
		// Method to configure SMS service
		smsServiceConfiguration.hourChange(sconfiguration);
	}
	
	
	@GetMapping("/checkSMS")
	public String chechSMS() {
		// Method to send SMS to admin
		Employee employee = userService.adminInfo();
		
		return smsImpl.SendSms(employee, "Checking the Token value experied 1");
	}

	@GetMapping("/allHolidays")
	public List<HolidayDetails> listofHolidays() {
		return userService.allHolidays();
	}
	
	@DeleteMapping("/deleteHoliday")
	public String deleteHoliday(@RequestParam LocalDate holidayDetails) {
		
		System.out.println(holidayDetails);
		return userService.deleteHoliday(holidayDetails);
	}
		
	@GetMapping("/employeeList")	
	
	public List<Employee> list(){
		return userService.registeredEmployee();
	
	
	}
}