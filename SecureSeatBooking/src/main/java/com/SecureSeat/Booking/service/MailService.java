package com.SecureSeat.Booking.service;

import com.SecureSeat.Booking.entity.FloorDetails;

public interface MailService {

	void schecludedMailForDailyBooking();

	String passwordChangeConfrimMail(int id);

	String addedFloor(FloorDetails floorDetails);

}