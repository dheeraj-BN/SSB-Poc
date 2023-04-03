package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;

public interface EmployeeDAO {

	void changePasswor(String password,int id);

	List<BookingDetails> getEmpBookedInfoBookedNext(int id, LocalDate date);

	void changePasswordAndMakeStatusTrue(String password, int id);

}