package com.SecureSeat.Booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.dao.ShiftDetailsDao;
import com.SecureSeat.Booking.entity.ShiftDetails;

@Service
public class ShiftDetailsServiceImpl implements ShiftDetailsService {

	@Autowired
	private ShiftDetailsDao shiftDetailsDao;

	@Override
	public List<ShiftDetails> getAllShiftDetails() {
		return shiftDetailsDao.getAllShiftDetails();
	}

	@Override
	public String deleteShift(int shiftId) {
		boolean success = shiftDetailsDao.deleteShiftDetailsById(shiftId);
		if (success) {
			return "Shift Details with Id " + shiftId + " has been deleted successfully";
		} else {
			return "Failed to delete the shift details with id " + shiftId;
		}
	}
	
	@Override
	public String addShiftTime(String shiftTime) {
		try {
			shiftDetailsDao.addShiftTime(shiftTime);
			return "Shift Time added successfully";
		}catch (Exception e) {
           return "Failed to added the shift time "+e.getMessage();
		}
		
	}
	
	@Override
	public String updateShiftTime(int id,String shiftTime) {
		try {
			shiftDetailsDao.updateShiftTime(id, shiftTime);
			return "Shift time added successfully"; 
		}catch(Exception e) {
			return "Failed to update the shift time "+e.getMessage(); 
		}
	}

}
