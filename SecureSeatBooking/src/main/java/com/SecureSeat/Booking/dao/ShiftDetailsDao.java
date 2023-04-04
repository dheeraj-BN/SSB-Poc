package com.SecureSeat.Booking.dao;

import java.util.List;

import com.SecureSeat.Booking.entity.ShiftDetails;

public interface ShiftDetailsDao {

	List<ShiftDetails> getAllShiftDetails();

	boolean deleteShiftDetailsById(int shiftId);

	void addShiftTime(String shiftTimings);

	void updateShiftTime(int id, String shiftTimings);

}