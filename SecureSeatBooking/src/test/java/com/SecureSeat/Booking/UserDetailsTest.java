package com.SecureSeat.Booking;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.RoleRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDetailsTest {
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;

	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Autowired
	
	private UserServiceImpl userServiceImpl;
	

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RoleRepo roleRepo;
	
	@Test
	public void addUserTest() {
	Employee employee = new Employee();
	employee.setEmployeeId(1);
	employee.setEmployeeName("John");
	employee.setEmployeeDesignation("Sales Manager");
	
	System.out.println("Start");
	Mockito.when(employeeRepo.findById(1)).thenReturn(employee);
	
	System.out.println(employee);

	Optional<UserDeatils> userDetails = Optional.empty();
	Mockito.when(userDetailsRepo.findByEmployee(employee)).thenReturn(userDetails);

	Role role = new Role();
	role.setRoleId(2);
	role.setRoleName("USER");

	Mockito.when(roleRepo.findById(2).get()).thenReturn(role);

	UserDeatils newUser = new UserDeatils();
	
	newUser.setPassword("Alpha@2022");
	newUser.setEmployee(employee);
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	newUser.setRoles(roles);

	Mockito.when(userDetailsRepo.save(newUser)).thenReturn(newUser);

	String result = userServiceImpl.addUser(1);

	assertEquals("USER ALREDY EXIST", result);

	Mockito.when(userDetailsRepo.findByEmployee(employee)).thenReturn(Optional.of(newUser));

	result = userServiceImpl.addUser(1);

	assertEquals(null, result);
	}

}
