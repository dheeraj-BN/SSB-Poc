package com.SecureSeat.Booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class LoginServiceImpl implements LoginService,UserDetailsService {

	@Autowired
	private UserDetailsRepo userRepo;

	@Autowired
	private EmployeeRepo empRepo;
	
	

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
	public List<Employee> findAllEmployees() {
		return empRepo.findAll();
	}

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
	public Employee findEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userNameAuth,password=null,role=null;
		List<GrantedAuthority> authorities = null;
		
	    List<Employee> employee =  empRepo.findByEmployeeEmail(username);
	    if(employee.size() == 0) {
	    	throw new UsernameNotFoundException("User details not found for the user : " + username);
	    }else {
	    	userNameAuth=employee.get(0).getEmployeeEmail();
	    	UserDeatils user = userRepo.findByEmployee(employee.get(0)).get();
	    	password=user.getPassword();
	    	Set<Role> roles = user.getRoles();
	    	for (Role role1 : roles) {
	    		role=role1.getRoleName();
	    	}
	    	
	    	authorities = new ArrayList<>();
	    	authorities.add(new SimpleGrantedAuthority(role));
	    	 
	    }
		
		return new User(username,password,authorities);
	}

}


