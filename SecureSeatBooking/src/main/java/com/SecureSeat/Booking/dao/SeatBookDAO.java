package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SeatBookDAO {

	List<String> getseatNoByBookedDate(LocalDate bookedDate);

}