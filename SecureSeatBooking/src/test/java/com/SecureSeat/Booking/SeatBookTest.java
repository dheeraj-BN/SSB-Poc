package com.SecureSeat.Booking;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.service.SeatBook;

@SpringBootTest
public class SeatBookTest {
	
	@Autowired
	private SeatBook seatBook;
	
//	@Test
//	public String saveBookedDetails() {
//		
//		BookingDetails bookingDetails = new BookingDetails("G10", true, LocalDate.now(), null, null, null, null, null, null);
//	}
	

}
