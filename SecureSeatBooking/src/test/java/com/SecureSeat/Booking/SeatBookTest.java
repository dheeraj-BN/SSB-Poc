package com.SecureSeat.Booking;

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

//@SpringBootTest
//@AutoConfigureMockMvc
public class SeatBookTest {
	
	@Autowired
	private SeatBook seatBook;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	

	
//	@Test
//	@WithAnonymousUser
//	public void getuserdetails() throws Exception {
//		mockMvc.perform(get("/getuser")).andExpect(status().isOk());
//		
//	}
//	
//	@Test
//	public void savedbookeddetails() {
////		List<UserDeatils> userDeatilss= userDetailsRepo.findAll();
//		UserDeatils userDeatils = seatBook.getuserbyid(22);
//		ShiftDetails shiftDetails =seatBook.getshiftdetails(1);
//		LocalDate date1=LocalDate.parse("2023-03-29");
//		BookingDetails bookingDetails = new BookingDetails("G12",true,date1, null, null, null, null, userDeatils, shiftDetails);
//		
//		seatBook.savebookeddetails(bookingDetails, date1, date1);
//	}

}
