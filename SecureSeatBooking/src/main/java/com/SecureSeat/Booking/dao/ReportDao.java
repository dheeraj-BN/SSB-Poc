package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface ReportDao {
	
	

	

	//by month and emp id
	//List<BookingDetails> getBookingDetailsByEmployeeIdAndMonthYear(int employeeId, int month, int year);

	
	


	List<BookingDetails> getBookingDetailsByDate(LocalDate bookingDate);

	List<BookingDetails> getBookingsByDateAndStatus(LocalDate bookingDate, String bookingStatus);

//	List<BookingDetails> getBookingDetailsByMonthYear(int month, int year);


	
	
	
	
	

	
	
	
	

}