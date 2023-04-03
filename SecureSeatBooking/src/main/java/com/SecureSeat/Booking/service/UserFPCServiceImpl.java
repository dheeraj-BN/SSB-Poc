package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.dao.EmployeeDAO;
//import com.SecureSeat.Booking.entity.UserDeatils;
//import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class UserFPCServiceImpl implements UserFPCService {
	
//	@Autowired
//	private UserDetailsRepo userDetailsRepo;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private SecurityConfig config;
	
	
	@Override
	public String firstTimeCHangeOfPassword(int userId,String newPassword) {
//		UserDeatils user=userDetailsRepo.findByUserId(userId).get();
		if(newPassword.equals("Alpha@2022")) {
			return "Your Password Cannot Be the same";
		}
		
		
//		employeeDAO.changePasswor(config.passwordEncoder().encode(newPassword),userId);
		employeeDAO.changePasswordAndMakeStatusTrue(config.passwordEncoder().encode(newPassword),userId);
		return "Password changed";
	}

}
