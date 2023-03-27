package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.dao.UserDetailDao;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.RoleRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class UserServiceImpl {

	@Autowired
	private UserDetailsRepo userDetailsRepo;

	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RoleRepo roleRepo;
	
	
	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private MailTemplatesImpl mailTemplatesImpl;

	public String addUser(int employeeId) {

		Employee employee = employeeRepo.findById(employeeId);

		if(employee == null) {
			return "USER NOT FOUND";
		}
		if (userDetailsRepo.findByEmployee(employee).isPresent()) {
			return "USER ALREDY EXIST";

		} else {
			System.out.println(employee);

			Role role = roleRepo.findById(2).get();
			Set<Role> roles = new HashSet<Role>();
			roles.add(role);

			UserDeatils user = new UserDeatils("Alpha@2022", employee);
			user.setRoles(roles);

			userDetailsRepo.save(user);

		}
		
		// return mailTemplatesImpl.registrationMail(user);

		return "SUCCESS";

	}

	
	public String validateTocken(String token) throws Exception {
		
		//UserDeatils user =userDetailsRepo.findByUserId(user_id).get();
		try {
		
		LocalDate l = LocalDate.now();
		BookingDetails bookingDetails= bookingDetailsRepo.findByToken(token);
		
		if(bookingDetails.getToken().equals(token)) {
		
		userDetailDao.updateBookingDetails(LocalTime.now(), "LOGGED-IN", bookingDetails.getBookingId());
		return "valid";
		}
		}
		catch (NullPointerException e) {
			return "Invalid";
		}
		return "Invalid";
		
	}
	
	
	
}
