package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Autowired
	private MailTemplates mailTemplates;
	
	
	 @Override
	@Scheduled(cron = "0 0 10 * * ?") // runs every day at 12:00 PM  //0 0 10 * * ?
	 public void schecludedMailForDailyBooking() {
		 
		 LocalDate date1=LocalDate.now();//need to change after ching format
		 List<BookingDetails> bookingDL=bookingDetailsRepo.findAllByLoginTimeIsNullAndBookedDateEquals(date1);
		 for (BookingDetails bookingDetails : bookingDL) {
			mailTemplates.dailyBookedSeatReminder(bookingDetails);
		}
		 
		 
		 
	 }

}
