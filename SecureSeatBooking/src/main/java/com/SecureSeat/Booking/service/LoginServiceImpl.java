package com.SecureSeat.Booking.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDetailsRepo userRepo;

	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private SecurityConfig cofig;

	@Override
	public Optional<UserDeatils> findUserByUsername(int id) {
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
	public Employee findEmployeeByName(String name) {
		return empRepo.findByEmployeeEmail(name);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public String loginResponse(String userName, String password, String role) {

		List<Employee> emps = empRepo.findAll();
		// System.out.println(emps);
		// String userNameStatus = null;
		boolean found = false;
		boolean passwordStatus = false;

		for (Employee name : emps) {
			// System.out.println(name);
			if (name.getEmployeeEmail().equals(userName)) {
				found = true;
				// System.out.println("there");
				Employee employee = empRepo.findByEmployeeEmail(userName);
				// System.out.println(employee);
				UserDeatils user = userRepo.findByUserId(employee.getEmployeeId()).get();
				// System.out.println(user);
				if (user.getPassword().equals(password)) {
					// System.out.println(user.getPassword());
					// System.out.println(password);
					passwordStatus = true;
					Set<Role> roles = user.getRoles();
					for (Role role1 : roles) {
						// System.out.println(role1.getRoleName());
						if (role1.getRoleName().equals("EMPLOYEE")) {
							role = "EMPLOYEE";
							// System.out.println("EMPLOYEE");
						}
						if (role1.getRoleName().equals("ADMIN")) {
							role = "ADMIN";
							// System.out.println("ADMIN");
						}
						if (role1.getRoleName().equals("DEVELOPER")) {
							role = "DEVELOPER";
							// System.out.println("DEVELOPER");
						}
					}
				}

				break;
			}

		}

		if (found && passwordStatus) {
			return "VALID USER " + role;
		} else if (found && !passwordStatus) {
			return "VALID USERNAME AND INVALID PASSWORD";
		} else {
			return "INVALID LOGIN CREDENTIALS";
		}
	}

}
