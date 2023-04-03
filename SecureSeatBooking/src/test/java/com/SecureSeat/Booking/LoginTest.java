package com.SecureSeat.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.SecureSeat.Booking.controller.LoginController;
import com.SecureSeat.Booking.model.AuthenticationRequest;
import com.SecureSeat.Booking.model.AuthenticationResponse;

@SpringBootTest
public class LoginTest {
	
	@Autowired
	private LoginController controller;
	
	@Test
	void testLoginWithValidCredentials() throws Exception {
	    AuthenticationRequest request = new AuthenticationRequest("admin@gmail.com", "admin");
	    ResponseEntity<?> responseEntity = controller.login(request);
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertNotNull(responseEntity.getBody());
	    assertNotNull(((AuthenticationResponse) responseEntity.getBody()).getToken());
	}

	@Test
	void testLoginWithInvalidCredentials() {
	    AuthenticationRequest request = new AuthenticationRequest("admin@gmail.com", "incorrectPassword");
	    assertThrows(RuntimeException.class, () -> controller.login(request), "Incorrect email or password");
	}

	
}


