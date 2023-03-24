package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.UserDeatils;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDeatils, Integer>{

}
