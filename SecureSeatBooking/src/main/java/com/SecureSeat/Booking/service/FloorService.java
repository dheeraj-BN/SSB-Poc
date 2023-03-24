package com.SecureSeat.Booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.FloorDetails;


public interface FloorService {

	List<FloorDetails> getAllFloors();

	FloorDetails addFloor(FloorDetails floor);

	//FloorDetails getFloorById(int floorId);

}


