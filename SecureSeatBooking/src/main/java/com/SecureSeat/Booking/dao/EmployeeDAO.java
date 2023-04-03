package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

public interface EmployeeDAO {

	void changePasswor(String password,int id);

	List<BookingDetails> getEmpBookedInfoBookedNext(int id, LocalDate date);


	int forgotPassword(String phoneNo);


	void restPassword(String password, int userid);


	void changePasswordAndMakeStatusTrue(String password, int id);

	void otp(String Otp, int userid);

}