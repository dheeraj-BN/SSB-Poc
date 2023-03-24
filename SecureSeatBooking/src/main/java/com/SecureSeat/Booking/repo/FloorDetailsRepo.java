package com.SecureSeat.Booking.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SecureSeat.Booking.entity.FloorDetails;

@Repository
public interface FloorDetailsRepo extends JpaRepository<FloorDetails ,Integer>{

}
