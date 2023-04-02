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
import com.SecureSeat.Booking.service.MailService;

@RestController()
@RequestMapping("/api/developer")
@CrossOrigin("http://10.191.80.118:3001/")
public class FloorController {
	
	
	@Autowired
	private FloorService floorService;
	
	
	@Autowired
	private FloorDetailsRepo floorDetailsRepo;
	
	@Autowired
	private MailService mailService;
	

	    
	    @GetMapping("/getAllFloorDetails")
	    public ResponseEntity<List<FloorDetails>> getAllFloors() {
	        try {
	            List<FloorDetails> floors = floorService.getAllFloors();
	            if (floors.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }
	            return new ResponseEntity<>(floors, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	   
	    
	    

	    
    
	    

	    //add seats to floors
	    @PostMapping("/Addseats/{floorId}")
	    public ResponseEntity<FloorDetails> addSeatsToFloors(@PathVariable int floorId, @RequestBody int noOfSeats) {
	        try {
	            FloorDetails floor = floorService.addSeatsToFloorDetails(floorId, noOfSeats);
	            if (floor != null) {
	                return new ResponseEntity<>(floor, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    
	    
	  
   
	    
	    
	    // update seats to floor
	    @PutMapping("/Updateseats/{floorId}")
	    public ResponseEntity<FloorDetails> updateSeatsToFloors(@PathVariable int floorId, @RequestBody int noOfSeats) {
	        try {
	            FloorDetails updatedFloorDetails = floorService.updateSeatsToFloorDetails(floorId, noOfSeats);
	            if (updatedFloorDetails != null) {
	                return new ResponseEntity<>(updatedFloorDetails, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    
	       
	    // Add a new floor with details
	    @PostMapping("/addfloor")
	    public ResponseEntity<FloorDetails> addFloorWithDetails(@RequestBody FloorDetails floorDetails) {
	        try {
	            FloorDetails newFloor = floorService.addFloorWithDetails(floorDetails);
	            mailService.addedFloor(floorDetails);
	            return ResponseEntity.status(HttpStatus.CREATED).body(newFloor);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
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
		public ResponseEntity<Void> deleteFloor(@PathVariable("floorId") int floorId) {
			try {
				FloorDetails floor = floorService.getFloorById(floorId);
				if (floor == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				floorService.deleteFloor(floorId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	    
	    

	    
		@GetMapping("/{floorId}")
		public ResponseEntity<FloorDetails> getFloorById(@PathVariable("floorId") int floorId) {
			try {
				FloorDetails floor = floorService.getFloorById(floorId);
				if (floor == null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(floor, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	    
		
		

	    

}
