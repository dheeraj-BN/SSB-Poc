package com.SecureSeat.Booking.service;

import java.util.List;

import com.SecureSeat.Booking.entity.ShiftDetails;

public interface ShiftDetailsService {

	List<ShiftDetails> getAllShiftDetails();

	String deleteShift(int shiftId);

	String addShiftTime(String shiftTime);

	String updateShiftTime(int id, String shiftTime);

}