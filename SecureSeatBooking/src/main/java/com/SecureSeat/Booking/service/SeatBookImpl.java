package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;

@Service
public class SeatBookImpl implements SeatBook {
	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Override
	public String  saveBookedDetails(BookingDetails bookingDetails,LocalDate from,LocalDate to) {
		
	String seatNo= bookingDetails.getSeatNo();
	System.out.println(from.equals(to));
	if(from.equals(to)) {
		LocalDate d1 = bookingDetails.getDate();
		bookingDetails.setDate(from);
		bookingDetails.setBookedTimings(LocalTime.now());
		String date1=d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
		System.out.println(date1);
		UserDeatils user =bookingDetails.getUserDeatils();
		Employee employee = user.getEmployee();
		int employeeid=employee.getEmployeeId();
		String tokenvalue= date1+employeeid;
		bookingDetails.setToken(tokenvalue);
  BookingDetails b=bookingDetailsRepo.save(bookingDetails);
  return b.getToken();
		
	}
	
	else {
		
	
		for (LocalDate i = from;i.isBefore(to);i=i.plusDays(1)) {
			
			LocalDate d1 = i;
			bookingDetails.setDate(i);
			bookingDetails.setBookedTimings(LocalTime.now());
			String date1=d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
			System.out.println(date1);
			UserDeatils user =bookingDetails.getUserDeatils();
			Employee employee = user.getEmployee();
			int employeeid=employee.getEmployeeId();
			String tokenvalue= date1+employeeid;
			bookingDetails.setToken(tokenvalue);
			
			BookingDetails book= new BookingDetails(bookingDetails.getSeatNo(), bookingDetails.isFoodStatus(), d1,LocalTime.now(), null,"PENDING", tokenvalue, user,bookingDetails.getShiftDetails());
	  BookingDetails b=bookingDetailsRepo.save(book);
	
		}
	return "not saved";
	}  
	
	}
	
	
	

}
