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
//@CrossOrigin("http://10.191.80.118:3001/")
public class FloorController {
	
	
	@Autowired
	private FloorService floorService;
	
	
	@Autowired
	private FloorDetailsRepo floorDetailsRepo;
	
	@Autowired
	private MailService mailService;
	

	    
	
	 @GetMapping("/floors/{floorName}")
	    public FloorDetails getFloorDetailsByFloorName(@PathVariable String floorName) {
	        return floorService.getFloorDetailsByFloorName(floorName);
	    }
	    
	    
    
	    
	    @PostMapping("/addfloor")
	    public ResponseEntity<FloorDetails> addFloorWithdetails(@RequestBody FloorDetails floorDetails) {
	        try {
	            FloorDetails newFloorDetails = floorService.addFloor(floorDetails.getFloorName(), floorDetails.getNoOfSeats());
	            mailService.addedFloor(floorDetails);
	            return ResponseEntity.status(HttpStatus.CREATED).body(newFloorDetails);
	        } catch (RuntimeException ex) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }

	    

	    @DeleteMapping("/{floorName}")
	    public ResponseEntity<String> deleteFloorByFloorName(@PathVariable String floorName) {
	        try {
	            FloorDetails floorDetails = floorDetailsRepo.findByFloorName(floorName);
	            if (floorDetails != null) {
	                floorDetailsRepo.deleteById(floorDetails.getFloorId());
	                return ResponseEntity.ok().body("Floor " + floorName + " deleted successfully.");
	            } else {
	                return ResponseEntity.badRequest().body("Floor " + floorName + " not found.");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting floor " + floorName + ": " + e.getMessage());
	        }
	    }

	    



	    
	    
	    @PutMapping("/{floorName}")
	    public FloorDetails updateFloorByFloorName(@PathVariable String floorName, @RequestBody FloorDetails floorDetails) {
	        return floorService.updateFloorByFloorName(floorName, floorDetails);
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
	    
		
		@GetMapping("/floordetails")
		public List<FloorDetails> getAllFloorDetails() {
		    List<FloorDetails> floorDetailsList = floorDetailsRepo.findAll();
		    return floorDetailsList;
		}

	    

}
