package com.SecureSeat.Booking.service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.FloorDetails;

public interface MailService {

	void schecludedMailForDailyBooking();

	String passwordChangeConfirmMail(int id);

	String addedFloor(FloorDetails floorDetails);

	void updateBookingDetails(BookingDetails oldBookingDetails, BookingDetails newBookingDetails);

}

