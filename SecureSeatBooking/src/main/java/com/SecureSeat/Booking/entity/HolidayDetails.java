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

    // default constructor
    public HolidayDetails() {}

    // constructor with arguments
    public HolidayDetails(int holidayId, LocalDate holidayDate, String holidayDescription) {
        this.holidayId = holidayId;
        this.holidayDate = holidayDate;
        this.holidayDescription = holidayDescription;
    }

    // getters and setters
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

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Holiday{" +
                "holidayId=" + holidayId +
                ", holidayDate=" + holidayDate +
                ", holidayDescription='" + holidayDescription + '\'' +
                '}';
    }
}