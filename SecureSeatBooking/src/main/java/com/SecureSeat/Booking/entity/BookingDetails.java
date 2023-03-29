package com.SecureSeat.Booking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private String seatNo;
	private boolean foodStatus;
	private LocalDate bookedDate;
	private LocalTime bookedTimings;
	private LocalTime loginTime;
	private String bookingStatus;
	private String token;
	
	@ManyToOne(targetEntity = UserDeatils.class,  fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private UserDeatils userDeatils;
	
	@OneToOne(targetEntity = ShiftDetails.class)
	@JoinColumn(name = "shiftId", referencedColumnName = "shiftId")
	private ShiftDetails shiftDetails;
	
	
	

	public BookingDetails() {
	}

	public BookingDetails(String seatNo, boolean foodStatus, LocalDate date, LocalTime bookedTimings, LocalTime loginTime,
			String bookingStatus, String token, UserDeatils userDeatils, ShiftDetails shiftDetails) {
		super();
		this.seatNo = seatNo;
		this.foodStatus = foodStatus;
		bookedDate = date;
		this.bookedTimings = bookedTimings;
		this.loginTime = loginTime;
		this.bookingStatus = bookingStatus;
		this.token = token;
		this.userDeatils = userDeatils;
		this.shiftDetails = shiftDetails;
	}

	public BookingDetails(int bookingId, String seatNo, boolean foodStatus, LocalDate date, LocalTime bookedTimings,
			LocalTime loginTime, String bookingStatus, String token, UserDeatils userDeatils,
			ShiftDetails shiftDetails) {
		super();
		this.bookingId = bookingId;
		this.seatNo = seatNo;
		this.foodStatus = foodStatus;
		bookedDate = date;
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

	

	public boolean isFoodStatus() {
		return foodStatus;
	}

	public void setFoodStatus(boolean foodStatus) {
		this.foodStatus = foodStatus;
	}

	public LocalDate getDate() {
		return bookedDate;
	}

	public void setDate(LocalDate date) {
		bookedDate = date;
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

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", seatNo=" + seatNo + ", foodStatus=" + foodStatus
				+ ", Date=" + bookedDate + ", bookedTimings=" + bookedTimings + ", loginTime=" + loginTime
				+ ", bookingStatus=" + bookingStatus + ", token=" + token + ", userDeatils=" + userDeatils
				+ ", shiftDetails=" + shiftDetails + "]";
	}

	
	
	

	
}
