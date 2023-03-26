package com.SecureSeat.Booking.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.RoleRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private MailTemplatesImpl mailTemplatesImpl;
	
	public String addUser(int employeeId) {
		
		Employee employee= employeeRepo.findById(employeeId);
		
		Role role= roleRepo.findById(2).get();
		Set<Role> roles =new HashSet<Role>();
		roles.add(role);
		
		UserDeatils user = new UserDeatils("Alpha@2022",employee);		
		user.setRoles(roles);
		
	userDetailsRepo.save(user);
		
		//return mailTemplatesImpl.registrationMail(user);
		
	return "SUCCESS";
		
		
	}

}
