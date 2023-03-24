package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;

@Service
public class SeatBookImpl implements SeatBook {
	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Override
	public BookingDetails  saveBookedDetails(BookingDetails bookingDetails) {
		
	String seatNo= bookingDetails.getSeatNo();
	
		LocalDate d1 = LocalDate.now();
		bookingDetails.setDate(d1);
		bookingDetails.setBookedTimings(LocalTime.now());
		String date1=d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
		System.out.println(date1);
		UserDeatils user =bookingDetails.getUserDeatils();
		Employee employee = user.getEmployee();
		int employeeid=employee.getEmployeeId();
		String tokenvalue= date1+employeeid;
		bookingDetails.setToken(tokenvalue);
  BookingDetails b=bookingDetailsRepo.save(bookingDetails);
  return b;
	}
	
	
	

}
