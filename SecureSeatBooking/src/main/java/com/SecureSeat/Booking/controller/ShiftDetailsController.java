package com.SecureSeat.Booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.service.ShiftDetailsService;

@RestController
@RequestMapping("/api/admin")
public class ShiftDetailsController {

	@Autowired
	private ShiftDetailsService shiftDetailsService;

	@GetMapping("/getAllShiftDetails")
	public List<ShiftDetails> getAllShiftDetails() {
		return shiftDetailsService.getAllShiftDetails();
	}
	
	@DeleteMapping("/deleteShiftDetails/{shiftId}")
	public String deleteShiftDetails(@PathVariable ("shiftId") int shiftId) {
		return shiftDetailsService.deleteShift(shiftId);
	}
	
	@PostMapping("/addShiftTime")
	public String addShiftTime(@RequestParam ("shiftTime") String shiftTime) {
		return shiftDetailsService.addShiftTime(shiftTime);
	}
	
	@PutMapping("/updateShiftTime/{shiftId}")
	public String updateShiftTime(@PathVariable ("shiftId") int shiftId,@RequestParam("shift_timings") String shiftTime) {
		return shiftDetailsService.updateShiftTime(shiftId,shiftTime);
				
	}
}
