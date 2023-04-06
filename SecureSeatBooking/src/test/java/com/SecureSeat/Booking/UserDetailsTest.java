package com.SecureSeat.Booking;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.RoleRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.Userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsTest {
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;

	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Autowired
	
	private Userservice userService;
	

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private SecurityConfig securityConfig;

	
	/*
	 * @Test public void testAddUser_Success() { int employeeId = 8;
	 * 
	 * ResponseEntity<Map<String, String>> response =
	 * userService.addUser(employeeId);
	 * 
	 * assertEquals(HttpStatus.CREATED, response.getStatusCode());
	 * assertEquals("SUCCESS", response.getBody().get("message")); }
	 */
	
	@Test
	public void testAddUser_UserNotFound() {
		int employeeId = 99999;
		
		ResponseEntity<Map<String, String>> response = userService.addUser(employeeId);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("USER NOT FOUND", response.getBody().get("message"));
	}
	
	

}
