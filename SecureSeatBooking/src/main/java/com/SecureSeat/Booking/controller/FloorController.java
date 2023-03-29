package com.SecureSeat.Booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.repo.FloorDetailsRepo;
import com.SecureSeat.Booking.service.FloorService;

@RestController()
@RequestMapping("/api/developer")
@CrossOrigin("http://10.191.80.118:3001/")
public class FloorController {
	
	
	@Autowired
	private FloorService floorService;
	
	
	@Autowired
	private FloorDetailsRepo floorDetailsRepo;
	
	
	    @GetMapping("/getAllFloorDetails")
	    public ResponseEntity<List<FloorDetails>> getAllFloors() {
	        List<FloorDetails> floors = floorService.getAllFloors();
	        return new ResponseEntity<>(floors, HttpStatus.OK);
	    }
	    
	    
  
	   
	    
	    

	    
	    
	    //add seats to floors
	    @PostMapping("/Addseats/{floorId}")
	    public FloorDetails addSeatsToFloors(@PathVariable int floorId, @RequestBody int noOfSeats) {
	        return floorService.addSeatsToFloorDetails(floorId, noOfSeats);
	    }
	    
	    
	    //update seats to floor
	    @PutMapping("/Updateseats/{floorId}")
	    public FloorDetails updateSeatsToFloors(@PathVariable int floorId, @RequestBody int noOfSeats) {
	        return floorService.updateSeatsToFloorDetails(floorId, noOfSeats);
	    }

	   
   
	    

//	    @PostMapping("/addfloor")
//	      public ResponseEntity<FloorDetails> addFloorWithSeats(@RequestParam("floorId") int floorId,
//	                                                             @RequestParam("floorName") String floorName,
//	                                                             @RequestParam("noOfSeats") int noOfSeats) {
//	          FloorDetails floorDetails = new FloorDetails(floorId, floorName, noOfSeats);
//	          FloorDetails newFloorDetails = floorDetailsRepo.save(floorDetails);
//	          return new ResponseEntity<>(newFloorDetails, HttpStatus.CREATED);
//	      }
//	    
	    
	    //Add a new floor with details
	    @PostMapping("/addfloor/{floorId}")
	    public ResponseEntity<FloorDetails> addFloorWithDetails(@RequestBody FloorDetails floorDetails) {
	        FloorDetails newFloor = floorService.addFloorWithDetails(floorDetails);
	        return ResponseEntity.status(HttpStatus.CREATED).body(newFloor);
	    }
	    
	    
	 // Update floor details by ID
	    @PutMapping("/Updatefloor/{id}")
	    public ResponseEntity<FloorDetails> updateFloorWithDetails(@PathVariable("id") int floorId, 
	                                                            @RequestBody FloorDetails floorDetails) {
	        FloorDetails updatedFloorDetails = floorService.updateFloorWithDetails(floorId, floorDetails);
	        if (updatedFloorDetails != null) {
	            return new ResponseEntity<>(updatedFloorDetails, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	

	    
	    
	    
	    


	    
	    
	    
	
	    
	  

	    
	    
   
	    @DeleteMapping("/delete/{floorId}")
	    public ResponseEntity<Void> deleteFloor(@PathVariable("floorId") int floorId){
	    	FloorDetails floor = floorService.getFloorById(floorId);
	    	
	    	if(floor == null) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	floorService.deleteFloor(floorId);
	    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	
	    }
	    
	    
	    
	    
	    
	    @GetMapping("/{floorId}")
	    public ResponseEntity<FloorDetails> getFloorById(@PathVariable ("floorId") int floorId){
	    	FloorDetails floor = floorService.getFloorById(floorId);
	    	if(floor == null) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	return new ResponseEntity<>(floor,HttpStatus.OK);
	    	
	    	
	    	
	    }
	    
	    

	    

}
