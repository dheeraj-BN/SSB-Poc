package com.SecureSeat.Booking.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HolidayDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int holidayId;
	private LocalDate holidayDate;
	private String holidayDescription;

	public HolidayDetails() {
	}

	public HolidayDetails(int holidayId, LocalDate holidayDate, String holidayDescription) {
		super();
		this.holidayId = holidayId;
		this.holidayDate = holidayDate;
		this.holidayDescription = holidayDescription;
	}

	public int getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayDescription() {
		return holidayDescription;
	}

	public void setHolidayDescription(String holidayDescription) {
		this.holidayDescription = holidayDescription;
	}

	@Override
	public String toString() {
		return "HolidayDetails [holidayId=" + holidayId + ", holidayDate=" + holidayDate + ", holidayDescription="
				+ holidayDescription + "]";
	}
	
	

}
