package com.SecureSeat.Booking.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;

import jakarta.annotation.PostConstruct;

public interface Userservice {

	void init();

	/**
	 * Adds a user with employeeId
	 * 
	 * @param employeeId
	 * @return SUCCESS if user is added successfully, USER NOT FOUND if employee
	 *         with employeeId is not found, and USER ALREADY EXIST if user with the
	 *         given employee is already present
	 */
	ResponseEntity<Map<String, String>> addUser(int employeeId);

	/**
	 * Validates the token and returns the employee associated with the booking.
	 * 
	 * @param token
	 * @return employee associated with the booking or null if booking is not found.
	 * @throws Exception
	 */

	Employee validateToken(String token) throws Exception;

	/**
	 * 
	 * Adds a new holiday to the system, if it doesn't already exist.
	 * 
	 * @param holidayDetails An object containing details about the holiday to be
	 *                       added.
	 * 
	 * @return A String indicating whether the holiday was successfully added or if
	 *         it already existed.
	 */
	String addHolidays(HolidayDetails holidayDetails);

	Employee adminInfo();

	List<Employee> listOfEmployeeNotRegistered();

	List<HolidayDetails> allHolidays();

}