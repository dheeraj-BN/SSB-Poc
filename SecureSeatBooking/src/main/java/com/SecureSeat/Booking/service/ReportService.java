package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface ReportService {


	List<BookingDetails> getBookingDetailsByDate(LocalDate bookingDate);

	List<BookingDetails> getBookingsByDateAndStatus(LocalDate bookingDate, String bookingStatus);

	
	
	List<BookingDetails> findBookingByDateAndStatus(LocalDate date, String status);

	List<BookingDetails> getBookingByDate(LocalDate bookedDate);

	List<BookingDetails> getBookingBySpecificMonth(int year, Month month);

	List<BookingDetails> getBookingBySpecificWeek(int week);

	//List<BookingDetails> getEmployeeBookings(int employeeId);


	List<BookingDetails> getEmployeeMonthlyBookingDetails(int userId, LocalDate month);






	
	

}