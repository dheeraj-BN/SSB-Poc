package com.SecureSeat.Booking.service;

import java.util.List;

import com.SecureSeat.Booking.entity.Employee;

public interface EmployeeService {

	Employee getEmployee(int id);

	List<Employee> getAllEmployee();


}