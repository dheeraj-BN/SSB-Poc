package com.SecureSeat.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FloorDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int floorId;
	private String floorName;
	private int noOfSeats;

	public FloorDetails() {
	}

	public FloorDetails(String floorName, int noOfSeats) {
		super();
		this.floorName = floorName;
		this.noOfSeats = noOfSeats;
	}

	public FloorDetails(int floorId, String floorName, int noOfSeats) {
		super();
		this.floorId = floorId;
		this.floorName = floorName;
		this.noOfSeats = noOfSeats;
	}
	
	

	public FloorDetails(int floorId, int noOfSeats) {
		super();
		this.floorId = floorId;
		this.noOfSeats = noOfSeats;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	@Override
	public String toString() {
		return "FloorDetails [floorId=" + floorId + ", floorName=" + floorName + ", noOfSeats=" + noOfSeats + "]";
	}

}
