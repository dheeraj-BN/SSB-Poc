package com.SecureSeat.Booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public Employee getEmployee(int id) {
		Employee e= employeeRepo.findById(id);
		return e;
	}
	
	@Override
	public List<Employee> getAllEmployee(){
		List<Employee> e=employeeRepo.findAll();
		return e;
	}

}
