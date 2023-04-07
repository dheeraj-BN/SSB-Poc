package com.SecureSeat.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.RoleRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.Userservice;



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
		
		String response = userService.addUser(employeeId);
		
		assertEquals("USER NOT FOUND", response);
	}
	
//	@Test
//	public void testValidateToken() throws Exception {
//		// Create a booking and save it to the repository
//		String token = "1234567890";
//		BookingDetails bookingDetails = new BookingDetails();
//		bookingDetails.setToken(token);
//		bookingDetailsRepo.save(bookingDetails);
//		
//		// Call validateToken with the token
//		Employee employee = userService.validateToken(token);
//		
//		// Assert that the employee returned is not null
//		assertNotNull(employee);
//		
//		// Assert that the employee is associated with the booking
//		assertEquals(employee.getBookingDetails().getToken(), token);
//		
//		// Assert that the booking details were updated
//		LocalDate currentDate = LocalDate.now();
//		BookingDetails updatedBookingDetails = bookingDetailsRepo.findByToken(token);
//		assertEquals(updatedBookingDetails.getStatus(), "LOGGED-IN");
//		assertTrue(updatedBookingDetails.getLoginTime().toLocalDate().isEqual(currentDate));
//	}
//}






}
