package com.SecureSeat.Booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.dao.ReportDao;
import com.SecureSeat.Booking.entity.BookingDetails;

@RestController
@RequestMapping("/bookingdetails")
public class ReportController {
	
	//private Repor
	
	private ReportDao reportDao;
	
	
	 @GetMapping("/date/{date}")
	    public List<BookingDetails> getBookingsByDate(@PathVariable("date") String dateStr) {
	        LocalDate date = LocalDate.parse(dateStr);
	        return reportDao.getBookingsByDate(date);
	    }

	
	

}
