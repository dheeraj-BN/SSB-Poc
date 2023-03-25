package com.SecureSeat.Booking.service;

import java.time.LocalDate;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface SeatBook {




	String saveBookedDetails(BookingDetails bookingDetails, LocalDate from, LocalDate to);

}