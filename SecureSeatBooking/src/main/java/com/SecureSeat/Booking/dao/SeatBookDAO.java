package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface SeatBookDAO {

	List<String> getseatNoByBookedDate(LocalDate bookedDate);

	List<BookingDetails> getbookingdetailsbydate(LocalDate bookedDate);

	void updatebookingstatus(int booking_id);

	int getbookingidfromtoken(String token);

	void updateseatNo(String seatno, int bookingid);

	void updatefoodstatus(Boolean foodstatus, int bookingid);

	void updateseatbooking(Boolean foodstatus, String seatno, int bookingid);

	List<BookingDetails> getbookingdetailsbydateandbookingstatus(LocalDate bookedDate);

	BookingDetails getlatestbookingdetailsofid(int id);

//	List<BookingDetails> getbookingdetailsbydate(LocalDate bookedDate);

}