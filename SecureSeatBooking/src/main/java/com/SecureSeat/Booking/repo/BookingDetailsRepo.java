package com.SecureSeat.Booking.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

@Repository
public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Integer> {
	
	List<BookingDetails> findByUserDeatils(UserDeatils user);

	List<BookingDetails> findAllByDateAndLoginTimeIsNull(LocalDate date1);

}
