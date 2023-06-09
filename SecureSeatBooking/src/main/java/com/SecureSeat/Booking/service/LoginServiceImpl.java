package com.SecureSeat.Booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.controller.LoginController;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

	@Autowired
	private UserDetailsRepo userRepo;

	@Autowired
	private EmployeeRepo empRepo;

	// Initialize logger
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public Optional<UserDeatils> findUserByUsername(int id) {
		// Get user details by id
		UserDeatils user = userRepo.findByUserId(id).get();

		Set<Role> roles = user.getRoles();

		for (Role role : roles) {
			System.out.println(role.getRoleName());
		}

		System.out.println(user.getEmployee().getEmployeeName());
		System.out.println(user.getPassword());
		System.out.println(user.getRoles());
		return userRepo.findByUserId(id);
	}

	@Override
	public List<Employee> findAllEmployees() {
		// Retrieving all employee details from database
		return empRepo.findAll();
	}
	
	@Override
	public Employee getUserInfo(int id ) {
		UserDeatils user=userRepo.findById(id).get();
		Employee employee=empRepo.findById(user.getEmployee().getEmployeeId());
		return employee;
	}
	

	// This method is currently not implemented. This i have implemented for test purpose
	@Override
	public String loginResponse(String userName, String password) {

		return "";

//		List<Employee> emps = empRepo.findAll();
//		// System.out.println(emps);
//		// String userNameStatus = null;
//		boolean found = false;
//		boolean passwordStatus = false;
//		String role=null;
//
//		for (Employee name : emps) {
//			// System.out.println(name);
//			if (name.getEmployeeEmail().equals(userName)) {
//				found = true;
//				// System.out.println("there");
//				Employee employee = empRepo.findByEmployeeEmail(userName);
//				// System.out.println(employee);
//				UserDeatils user = userRepo.findByUserId(employee.getEmployeeId()).get();
//				// System.out.println(user);
//				if (user.getPassword().equals(password)) {
//					// System.out.println(user.getPassword());
//					// System.out.println(password);
//					passwordStatus = true;
//					Set<Role> roles = user.getRoles();
//					for (Role role1 : roles) {
//						// System.out.println(role1.getRoleName());
//						if (role1.getRoleName().equals("EMPLOYEE")) {
//							role = "EMPLOYEE";
//							// System.out.println("EMPLOYEE");
//						}
//						if (role1.getRoleName().equals("ADMIN")) {
//							role = "ADMIN";
//							// System.out.println("ADMIN");
//						}
//						if (role1.getRoleName().equals("DEVELOPER")) {
//							role = "DEVELOPER";
//							// System.out.println("DEVELOPER");
//						}
//					}
//				}
//
//				break;
//			}
//
//		}
//
//		if (found && passwordStatus) {
//			return  role;
//		} else if (found && !passwordStatus) {
//			return "VALID USERNAME AND INVALID PASSWORD";
//		} else {
//			return "INVALID LOGIN CREDENTIALS";
//		}
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userNameAuth, password = null, role = null;
		List<GrantedAuthority> authorities = null;

		List<Employee> employee = empRepo.findByEmployeeEmail(username);
		if (employee.size() == 0) {
			logger.warn("user details not found with email : " + username);
			throw new UsernameNotFoundException("User details not found for the user : " + username);
		} else {
			// Get user details by employee email
			userNameAuth = employee.get(0).getEmployeeEmail();
			UserDeatils user = userRepo.findByEmployee(employee.get(0)).get();
			password = user.getPassword();
			Set<Role> roles = user.getRoles();
			for (Role role1 : roles) {
				role = role1.getRoleName();
			}

			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(role));

		}
		logger.info("user has been loaded " + username);
		return new User(username, password, authorities);
	}

}
