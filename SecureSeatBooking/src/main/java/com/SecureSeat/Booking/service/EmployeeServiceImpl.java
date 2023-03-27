package com.SecureSeat.Booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
//	@Override
//	public BookingDetails getEmpBookedInfo(int id) {
//		UserDeatils user=userDetailsRepo.findByUserId(id).get();
////		BookingDetails info=bookingDetailsRepo.findByUserDetails(user);
//		return info;
//		
//	}
	
//	@Override
//	public UserDetails getEmployeeBookedInfo(int id) {
//		UserDetails info=userDetailsRepo.findByUserId(id);
//		return info;
//		
//	}
	
	
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
