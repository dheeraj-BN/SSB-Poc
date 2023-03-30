package com.SecureSeat.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.ShiftDetailsRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.SeatBook;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@SpringBootTest
@AutoConfigureMockMvc
public class SeatBookTest {
	
	@Autowired
	private SeatBook seatBook;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	

	
	@Test
	@WithAnonymousUser
	public void getuserdetails() throws Exception {
		mockMvc.perform(get("/getuser")).andExpect(status().isOk());
		
	}
	
	@Test
	public void savedbookeddetailsforday() {
		UserDeatils userDeatils = userDetailsRepo.findById(18).get();
		ShiftDetails shiftDetails =seatBook.getshiftdetails(1);
		LocalDate date1=LocalDate.parse("2023-03-29");
		BookingDetails bookingDetails = new BookingDetails("G12",true,date1, null, null, null, null,userDeatils, shiftDetails);
        seatBook.savebookeddetails(bookingDetails, date1, date1);
		assertEquals("Seat has already  booked for today","Seat has already  booked for today");
		
		
	}
	
	@Test
	public void savedbookeddetailsforweek() {
		UserDeatils userDeatils = userDetailsRepo.findById(22).get();
		ShiftDetails shiftDetails =seatBook.getshiftdetails(1);
		LocalDate date1=LocalDate.parse("2023-04-01");
		LocalDate date2 = LocalDate.parse("2023-04-07");
		BookingDetails bookingDetails = new BookingDetails("G12",true,date1, null, null, null, null,userDeatils, shiftDetails);
        seatBook.savebookeddetails(bookingDetails, date1, date2);
		assertEquals("seat has been already booked for that week","seat has been already booked for that week");
		
		
	}

}
