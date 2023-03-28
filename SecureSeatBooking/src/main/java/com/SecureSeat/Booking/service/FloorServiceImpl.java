package com.SecureSeat.Booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.repo.FloorDetailsRepo;

@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorDetailsRepo floorDetailsRepo;
	
	
	

	@Override
	public List<FloorDetails> getAllFloors() {
		return floorDetailsRepo.findAll();

	}
	
	@Override
    public void deleteFloor(int floorId) {
        floorDetailsRepo.deleteById(floorId);
    }

	
	
	@Override
	public FloorDetails getFloorById(int floorId) {
		  Optional<FloorDetails> result = floorDetailsRepo.findById(floorId);
	        return result.isPresent() ? result.get() : null;
	}

	@Override
	public FloorDetails addFloor(FloorDetails floor) {
		return floorDetailsRepo.save(floor);
	}


	
	


	

	 

}
