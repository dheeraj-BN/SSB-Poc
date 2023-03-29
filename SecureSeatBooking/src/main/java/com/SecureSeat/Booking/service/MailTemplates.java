package com.SecureSeat.Booking.service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

public interface MailTemplates {

	void registrationMail(UserDeatils userInfo,String pass);

	void dailyBookedSeatReminder(BookingDetails bookingDetails);

	void passwordChangeMail(UserDeatils userInfo);

	void addedFloorTemplete(FloorDetails floorDetails, String adminEmail);

}