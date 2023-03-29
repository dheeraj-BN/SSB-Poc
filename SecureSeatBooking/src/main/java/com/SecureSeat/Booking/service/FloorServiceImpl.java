package com.SecureSeat.Booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	
//
//	
//	    @Override
//	    public ResponseEntity<FloorDetails> updateFloorDetails(int floorId, FloorDetails floorDetails) {
//	        Optional<FloorDetails> floorDetailsOptional = floorDetailsRepo.findById(floorId);
//	        if (floorDetailsOptional.isPresent()) {
//	            floorDetails.setFloorId(floorId);
//	            floorDetailsRepo.save(floorDetails);
//	            return ResponseEntity.ok().body(floorDetails);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }

	   
	   
	   

	   //add seats to floors
	@Override
	public FloorDetails addSeatsToFloorDetails(int floorId, int noOfSeats) {
		FloorDetails floorDetails = floorDetailsRepo.findById(floorId).orElse(null);
        if (floorDetails != null) {
            floorDetails.setNoOfSeats(floorDetails.getNoOfSeats() + noOfSeats);
            return floorDetailsRepo.save(floorDetails);
        }
		
		return null;
	}






	@Override
	public FloorDetails updateSeatsToFloorDetails(int floorId, int noOfSeats) {
		 FloorDetails floorDetails = floorDetailsRepo.findById(floorId).orElse(null);
	        if (floorDetails != null) {
	            floorDetails.setNoOfSeats(noOfSeats);
	            return floorDetailsRepo.save(floorDetails);
	        }
	        return null;
		
		
	}



	@Override
	public FloorDetails addFloorWithDetails(FloorDetails floorDetails) {
		return floorDetailsRepo.save(floorDetails);
	}



	@Override
	public FloorDetails updateFloorWithDetails(int floorId, FloorDetails floorDetails) {
	
		Optional<FloorDetails> optionalFloorDetails = floorDetailsRepo.findById(floorId);
	    if (optionalFloorDetails.isPresent()) {
	        FloorDetails existingFloorDetails = optionalFloorDetails.get();
	        existingFloorDetails.setFloorName(floorDetails.getFloorName());
	        existingFloorDetails.setNoOfSeats(floorDetails.getNoOfSeats());
	        return floorDetailsRepo.save(existingFloorDetails);
	    } else {
	        return null;
	    }
		
		
	}


//
//	@Override
//	public FloorDetails updateFloorWithDetails(int id, FloorDetails floorDetails) {
//		
//		
//		return null;
//	}
//
//
//
//	@Override
//	public ResponseEntity<FloorDetails> updateFloorDetails(int floorId, FloorDetails floorDetails) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	

	 

}
