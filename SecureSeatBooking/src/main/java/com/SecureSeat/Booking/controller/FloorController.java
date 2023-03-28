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
	    
	    
	   
//	    @PostMapping("/addFloor")
//	    public ResponseEntity<FloorDetails> addFloor(@RequestBody FloorDetails floorDetails) {
//	        FloorDetails savedFloor = floorDetailsRepo.save(floorDetails);
//	        return new ResponseEntity<>(savedFloor, HttpStatus.CREATED);
//	    }

	    
	  

	    
	    
   
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
