package com.SecureSeat.Booking.service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;

public interface SmsTemplate {

	void sentOTP(Employee employee, String otp);

	void SuccessfulSeatBooking(Employee employee, BookingDetails bookingDetails, String Bookingtype);

	void CanceledBooking(Employee employee, BookingDetails bookingDetails);

}