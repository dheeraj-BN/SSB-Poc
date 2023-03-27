package com.SecureSeat.Booking.service;

import java.util.List;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;

public interface EmployeeService {

	Employee getEmployee(int id);

	List<Employee> getAllEmployee();

//	BookingDetails getEmpBookedInfo(int id);

//	UserDetails getEmployeeBookedInfo(int id);

//	List<BookingDetails> getEmpBookedInfo(int id);

//	List<BookingDetails> getEmpBookedInfo(UserDeatils user);




}