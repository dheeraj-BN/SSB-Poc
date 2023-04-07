package com.SecureSeat.Booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.repo.FloorDetailsRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorDetailsRepo floorDetailsRepo;
	
	

	
	
	
	
	
	@Override
	public FloorDetails getFloorById(int floorId) {
		  Optional<FloorDetails> result = floorDetailsRepo.findById(floorId);
	        return result.isPresent() ? result.get() : null;
	}

	




//
//	@Override
//	public FloorDetails addFloor(String floorName, int noOfSeats) {
//		
//		FloorDetails floorDetails = new FloorDetails(floorName, noOfSeats);
//        return floorDetailsRepo.save(floorDetails);
//		
//	}

	
	
	@Override
	public FloorDetails addFloor(String floorName, int noOfSeats) {
	    // Check if a floor with the given name already exists
	    if (floorDetailsRepo.existsByFloorName(floorName)) {
	        throw new RuntimeException("Floor name must be unique");
	    }

	    FloorDetails floorDetails = new FloorDetails(floorName, noOfSeats);
	    return floorDetailsRepo.save(floorDetails);
	}



	@Override
	public FloorDetails getFloorDetailsByFloorName(String floorName) {
		
		  return floorDetailsRepo.findByFloorName(floorName);
	}



	    @Override
	    public void deleteFloorByFloorName(String floorName) {
	        FloorDetails floorDetails = floorDetailsRepo.findByFloorName(floorName);
	        
	        if (floorDetails != null) {
	            floorDetailsRepo.deleteById(floorDetails.getFloorId());
	        }

	    }








	@Override
	public FloorDetails getFloorByFloorName(String floorName) {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public FloorDetails updateFloorByFloorName(String floorName, FloorDetails floorDetails) {
		
		 FloorDetails existingFloor = floorDetailsRepo.findByFloorName(floorName);
	        if (existingFloor != null) {
	            ((FloorDetails) existingFloor).setFloorName(floorDetails.getFloorName());
	            ((FloorDetails) existingFloor).setNoOfSeats(floorDetails.getNoOfSeats());
	            return floorDetailsRepo.save(existingFloor);
	        } else {
	            // handle not found case
	            return null;
	        }
	    }
	







	}






	
	
	
	
	
	
	
	
	
	
	
	
	
	










	 


