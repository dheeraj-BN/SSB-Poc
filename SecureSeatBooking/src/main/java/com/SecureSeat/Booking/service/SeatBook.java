package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface SeatBook {




	String saveBookedDetailsforday(BookingDetails bookingDetails, LocalDate from) ;

	String seatbookingforweek(BookingDetails bookingDetails, LocalDate from, LocalDate to);

	String savebookeddetails(BookingDetails bookingDetails, LocalDate from, LocalDate to);

	String checkseatalreadybooked(String seatNo, LocalDate date1);

	int checkbookingdetails(LocalDate date, BookingDetails bookingDetails1);

	int checkholiday(LocalDate date);

	List<String> getSeatNoByDate(LocalDate bookeddate);

}