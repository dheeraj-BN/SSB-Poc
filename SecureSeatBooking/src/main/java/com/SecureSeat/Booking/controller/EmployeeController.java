package com.SecureSeat.Booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@GetMapping("/test")
	public String test() {
		return "Hello Test";
	}
}
