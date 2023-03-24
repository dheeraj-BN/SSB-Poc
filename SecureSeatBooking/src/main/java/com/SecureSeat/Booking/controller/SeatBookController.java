package com.SecureSeat.Booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.ShiftDetailsRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.SeatBook;

@RestController
public class SeatBookController {
	
	@Autowired
	private SeatBook seatBook;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo ;
	
	@Autowired
	private ShiftDetailsRepo shiftDetailsRepo;
	
	
	@GetMapping("/getuser")
	public List<UserDeatils> getuser() {
		return userDetailsRepo.findAll();
	}
	
	@GetMapping("/getshift")
	public List<ShiftDetails> getshift() {
		return shiftDetailsRepo.findAll();
	}
	
	
	@PostMapping("/savebookdetails")
	public String saveBookedDetails(@RequestBody BookingDetails bookingDetails) {
	BookingDetails b=seatBook.saveBookedDetails(bookingDetails);
	String tokenvalue = b.getToken();
		return tokenvalue;
	}

}
