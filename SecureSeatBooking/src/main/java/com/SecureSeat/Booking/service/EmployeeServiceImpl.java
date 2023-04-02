package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.dao.EmployeeDAO;
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
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private SecurityConfig config;
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void forgotPasword(String email,String password) {
		int userid=employeeDAO.forgotPassword(email);
		employeeDAO.restPassword(config.passwordEncoder().encode(password), userid);
		
	}
	
	
	
	@Override
	public String changePassword(int id,String oldPassword,String newPassword) {
//		System.out.println(id);
		UserDeatils user=userDetailsRepo.findByUserId(id).get();
//		System.out.println(user);
//		System.out.println(config.passwordEncoder().matches(oldPassword,user.getPassword()));
		if(config.passwordEncoder().matches(oldPassword,user.getPassword())) {
			
			employeeDAO.changePasswor(config.passwordEncoder().encode(newPassword),id);
			return "Password changed";
		}else {
		return "Old Password doesn't matched";
		}
	}
	
	
	@Override
	public BookingDetails getEmpBookedInfo(int id) {
		UserDeatils user=userDetailsRepo.findByUserId(id).get();
		BookingDetails info=bookingDetailsRepo.findByUserDeatilsAndBookedDateEquals(user, LocalDate.now());
		return info;	
	}
	
	@Override
	public List<BookingDetails> getEmpBookedInfoBookedNext(int id) {
		UserDeatils user=userDetailsRepo.findByUserId(id).get();
//	List<BookingDetails> info=bookingDetailsRepo.findByUserDeatilsAndBookedDateGreaterThan(user, LocalDate.now());
		List<BookingDetails> info=employeeDAO.getEmpBookedInfoBookedNext(id, LocalDate.now());
		return info;	
	}
	
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
