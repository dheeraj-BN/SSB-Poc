package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

public interface EmployeeService {

	Employee getEmployee(int id);

	List<Employee> getAllEmployee();

	BookingDetails getEmpBookedInfo(int id);

	List<BookingDetails> getEmpBookedInfoBookedNext(int id);

	String changePassword(int id, String oldPassword, String Password);


//	String generateOtp(String phoneNo);


	int findUserIdByPhoneNo(String phoneNo);

	String getRandomNumberString();

	String generateOtp( );

	String forgotPasword(int id, String password);


	BookingDetails getbookingdetailsbyid(int id);

	String savelastbookingdetails(int id, LocalDate from, LocalDate to);

	List<FloorDetails> getAll();








	






}