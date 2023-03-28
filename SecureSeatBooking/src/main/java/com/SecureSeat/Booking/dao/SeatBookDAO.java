package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface SeatBookDAO {

	List<String> getseatNoByBookedDate(LocalDate bookedDate);

	List<BookingDetails> getbookingdetailsbydate(LocalDate bookedDate);

}