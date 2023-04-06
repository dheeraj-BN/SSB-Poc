package com.SecureSeat.Booking.dao;

import java.util.List;

import com.SecureSeat.Booking.entity.ShiftDetails;

public interface ShiftDetailsDao {

	//Retrieving shift details from database
	List<ShiftDetails> getAllShiftDetails();

	//deleting shift details with id
	boolean deleteShiftDetailsById(int shiftId);

	//adding new shift timings
	void addShiftTime(String shiftTimings);

	//updating already exists shift timings
	void updateShiftTime(int id, String shiftTimings);

}