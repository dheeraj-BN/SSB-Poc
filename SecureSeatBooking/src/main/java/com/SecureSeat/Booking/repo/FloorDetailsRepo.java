package com.SecureSeat.Booking.repo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SecureSeat.Booking.entity.FloorDetails;

@Repository
public interface FloorDetailsRepo extends JpaRepository<FloorDetails ,Integer>{

	FloorDetails findByFloorName(String floorName);

	List<FloorDetails> findAllByFloorName(String floorName);

	

	
	  
	

}
