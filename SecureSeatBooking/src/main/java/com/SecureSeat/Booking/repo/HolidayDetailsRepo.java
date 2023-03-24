package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.HolidayDetails;

@Repository
public interface HolidayDetailsRepo extends JpaRepository<HolidayDetails, Integer>{

}
