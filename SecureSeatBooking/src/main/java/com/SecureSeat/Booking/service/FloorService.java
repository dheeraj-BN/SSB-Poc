package com.SecureSeat.Booking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.FloorDetails;


public interface FloorService {


	

	FloorDetails getFloorById(int floorId);

	


	FloorDetails addFloor(String floorName, int noOfSeats);

	FloorDetails getFloorDetailsByFloorName(String floorName);


	FloorDetails getFloorByFloorName(String floorName);




	FloorDetails updateFloorByFloorName(String floorName, FloorDetails floorDetails);




	void deleteFloorByFloorName(String floorName);




	//FloorDetails updateFloorByFloorName(String floorName, FloorDetails floorDetails);



	


	
	
	
	
	
	

	



}


