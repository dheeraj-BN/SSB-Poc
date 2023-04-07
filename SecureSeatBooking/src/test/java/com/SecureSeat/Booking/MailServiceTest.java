package com.SecureSeat.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.service.MailService;


@SpringBootTest
public class MailServiceTest {
	
	
//	@Autowired
//	private MailService mailService;
//	
//	@Test
//	public void testChangePasswordMail() {
//		assertEquals("SUCCESS", mailService.passwordChangeConfirmMail(24));
//		assertEquals("FAILURE",  mailService.passwordChangeConfirmMail(100));
//		
//	}
//	
//	@Test
//	public void testAddFloor() {
//		FloorDetails floor= new FloorDetails("fourth floor",20);
//		assertEquals("SUCCESS", mailService.addedFloor(floor));
//		
//	}

}
