package com.SecureSeat.Booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.service.FloorService;

@RestController
@RequestMapping("/floors")
public class FloorController {
	
	
	@Autowired
	private FloorService floorService;
	
	
	    @GetMapping
	    public ResponseEntity<List<FloorDetails>> getAllFloors() {
	        List<FloorDetails> floors = floorService.getAllFloors();
	        return new ResponseEntity<>(floors, HttpStatus.OK);
	    }
	    
	    
	    
	    
	    
	    

}
