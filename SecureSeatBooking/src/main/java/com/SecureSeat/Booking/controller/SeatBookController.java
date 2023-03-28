package com.SecureSeat.Booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String saveBookedDetails(@RequestBody BookingDetails bookingDetails,@RequestParam("from") LocalDate from,@RequestParam("to") LocalDate to) {
		String message =seatBook.savebookeddetails(bookingDetails, from, to);
		return message;
	}
	
	@GetMapping("/seatnumber/{seatno}/{date1}")
	public String seatBookedOrNot(@PathVariable String seatno,@PathVariable LocalDate date1) {

	String message = seatBook.checkseatalreadybooked(seatno, date1);
	return message;
		
	}
	

}
