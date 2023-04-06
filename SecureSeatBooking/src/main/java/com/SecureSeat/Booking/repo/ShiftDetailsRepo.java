package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.ShiftDetails;

@Repository
public interface ShiftDetailsRepo extends JpaRepository<ShiftDetails, Integer>{
	ShiftDetails findByShiftTimings(String shifttimings);

}
