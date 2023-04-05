package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface ReportDao {

	//by date
	List<BookingDetails> getBookingsByDate(LocalDate date);

	//by month
	List<BookingDetails> getBookingsByMonthAndYear(int month, int year);

	//by month and emp id
	List<BookingDetails> getBookingDetailsByEmployeeIdAndMonthYear(int employeeId, int month, int year);

}