package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.BookingDetails;


@Repository
public interface EmployeeReportRepo extends JpaRepository<BookingDetails ,Integer  > {
	
	
	
	
	

}