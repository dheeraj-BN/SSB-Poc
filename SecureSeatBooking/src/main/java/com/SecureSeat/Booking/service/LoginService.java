package com.SecureSeat.Booking.service;

import java.util.List;
import java.util.Optional;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;

public interface LoginService {

	Optional<UserDeatils> findUserByUsername(int id);

	Employee findEmployeeByName(String name);

	String loginResponse(String userName, String password,String role);

	List<Employee> findAllEmployees();

}