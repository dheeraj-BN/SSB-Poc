package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;

public interface EmployeeService {

	Employee getEmployee(int id);

	List<Employee> getAllEmployee();

	BookingDetails getEmpBookedInfo(int id);

	List<BookingDetails> getEmpBookedInfoBookedNext(int id);

	String changePassword(int id, String oldPassword, String Password);


	void forgotPasword(String email, String password);





	






}