package com.SecureSeat.Booking.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface MailService {

	void schecludedMailForDailyBooking();

	void passwordChangeConfrimMail(int id);

}