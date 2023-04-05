package com.SecureSeat.Booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.Employee;

@Repository
public interface EmployeeRepo  extends JpaRepository<Employee, Integer>{
	
	Employee findById(int id);
	
	List<Employee> findByEmployeeEmail(String email);
	
	List<Employee> findAll();
	
	Employee findByEmployeePhoneNo(String phoneNo);


}
