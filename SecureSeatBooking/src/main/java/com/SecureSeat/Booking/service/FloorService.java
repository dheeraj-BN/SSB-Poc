package com.SecureSeat.Booking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.FloorDetails;


public interface FloorService {

	List<FloorDetails> getAllFloors();

	void deleteFloor(int floorId);

	FloorDetails getFloorById(int floorId);

	FloorDetails addSeatsToFloorDetails(int floorId, int noOfSeats);

	FloorDetails updateSeatsToFloorDetails(int floorId, int noOfSeats);

	
	FloorDetails addFloorWithDetails(FloorDetails floorDetails);

	FloorDetails updateFloorWithDetails(int floorId, FloorDetails floorDetails);
	
	
	
	
	
	

	//ResponseEntity<FloorDetails> updateFloorDetails(int floorId, FloorDetails floorDetails);
	

	//FloorDetails updateFloorWithDetails(int id, FloorDetails floorDetails);

	
	
	//FloorDetails saveFloor(int floorId, String floorName, int noOfSeats);

	//FloorDetails updateFloorDetails(int floorId, String floorName, int noOfSeats);
	
	//FloorDetails updateFloorDetails(int floorId, FloorDetails floorDetails);
	
	
	//FloorDetails addFloor(FloorDetails floor);

		//FloorDetails updateFloor(int floorId, FloorDetails floorDetails);





}


