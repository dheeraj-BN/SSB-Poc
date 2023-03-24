package com.SecureSeat.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShiftDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shiftId;
	private String shiftTimings;

	public ShiftDetails() {
	}

	public ShiftDetails(String shiftTimings) {
		super();
		this.shiftTimings = shiftTimings;
	}

	public ShiftDetails(int shiftId, String shiftTimings) {
		super();
		this.shiftId = shiftId;
		this.shiftTimings = shiftTimings;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftTimings() {
		return shiftTimings;
	}

	public void setShiftTimings(String shiftTimings) {
		this.shiftTimings = shiftTimings;
	}

	@Override
	public String toString() {
		return "ShiftDetails [shiftId=" + shiftId + ", shiftTimings=" + shiftTimings + "]";
	}

}
