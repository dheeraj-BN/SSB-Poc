package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.dao.ReportDao;
import com.SecureSeat.Booking.entity.BookingDetails;

@Service
public class ReportServiceDaoImpl {
	
	@Autowired
	private ReportDao reportDao;
	

	
 
    public List<BookingDetails> getBookingsByDate(LocalDate date) {
        return reportDao.getBookingsByDate(date);
    }
	
	
	
	
	

}
