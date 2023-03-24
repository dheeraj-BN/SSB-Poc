package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

	
}
