package com.SecureSeat.Booking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private int seatNo;
	private boolean foodStatus;
	private LocalDate Date;
	private LocalTime bookedTimings;
	private LocalTime loginTime;
	private boolean bookingStatus;
	private String token;
	
	@ManyToOne(targetEntity = UserDeatils.class, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private UserDeatils userDeatils;
	
	@OneToOne(targetEntity = ShiftDetails.class)
	@JoinColumn(name = "shiftId", referencedColumnName = "shiftId")
	private ShiftDetails shiftDetails;
	
	
	

	public BookingDetails() {
	}

	public BookingDetails(int seatNo, boolean foodStatus, LocalDate date, LocalTime bookedTimings, LocalTime loginTime,
			boolean bookingStatus, String token, UserDeatils userDeatils, ShiftDetails shiftDetails) {
		super();
		this.seatNo = seatNo;
		this.foodStatus = foodStatus;
		Date = date;
		this.bookedTimings = bookedTimings;
		this.loginTime = loginTime;
		this.bookingStatus = bookingStatus;
		this.token = token;
		this.userDeatils = userDeatils;
		this.shiftDetails = shiftDetails;
	}

	public BookingDetails(int bookingId, int seatNo, boolean foodStatus, LocalDate date, LocalTime bookedTimings,
			LocalTime loginTime, boolean bookingStatus, String token, UserDeatils userDeatils,
			ShiftDetails shiftDetails) {
		super();
		this.bookingId = bookingId;
		this.seatNo = seatNo;
		this.foodStatus = foodStatus;
		Date = date;
		this.bookedTimings = bookedTimings;
		this.loginTime = loginTime;
		this.bookingStatus = bookingStatus;
		this.token = token;
		this.userDeatils = userDeatils;
		this.shiftDetails = shiftDetails;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public boolean isFoodStatus() {
		return foodStatus;
	}

	public void setFoodStatus(boolean foodStatus) {
		this.foodStatus = foodStatus;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public LocalTime getBookedTimings() {
		return bookedTimings;
	}

	public void setBookedTimings(LocalTime bookedTimings) {
		this.bookedTimings = bookedTimings;
	}

	public LocalTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalTime loginTime) {
		this.loginTime = loginTime;
	}

	public boolean isBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDeatils getUserDeatils() {
		return userDeatils;
	}

	public void setUserDeatils(UserDeatils userDeatils) {
		this.userDeatils = userDeatils;
	}

	public ShiftDetails getShiftDetails() {
		return shiftDetails;
	}

	public void setShiftDetails(ShiftDetails shiftDetails) {
		this.shiftDetails = shiftDetails;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", seatNo=" + seatNo + ", foodStatus=" + foodStatus
				+ ", Date=" + Date + ", bookedTimings=" + bookedTimings + ", loginTime=" + loginTime
				+ ", bookingStatus=" + bookingStatus + ", token=" + token + ", userDeatils=" + userDeatils
				+ ", shiftDetails=" + shiftDetails + "]";
	}
	
	

	
}
