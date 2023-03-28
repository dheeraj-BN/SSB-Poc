package com.SecureSeat.Booking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDeatils, Integer>{

	
	Optional<UserDeatils> findByUserId(int id);
	
	Optional<UserDeatils> findByEmployee(Employee e);
	
	
}
