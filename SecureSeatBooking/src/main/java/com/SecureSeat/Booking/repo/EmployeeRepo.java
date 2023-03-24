package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.Employee;

@Repository
public interface EmployeeRepo  extends JpaRepository<Employee, Integer>{
	
	Employee findById(int id);

}
