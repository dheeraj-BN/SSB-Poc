package com.SecureSeat.Booking.dao;

import java.time.LocalTime;
import java.util.List;

import com.SecureSeat.Booking.entity.Employee;

public interface UserDetailDao {

	void updateBookingDetails(LocalTime loggedIn, String status, int id) throws Exception;

	Employee getEmployeeDetailsAfterValidationSuccess(String token);

	Employee getAdminInfo();

	List<Employee> getemployee();

	List<Employee> getRegisteredemployee();

}