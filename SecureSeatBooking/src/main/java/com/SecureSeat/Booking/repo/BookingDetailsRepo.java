package com.SecureSeat.Booking.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

@Repository
public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Integer> {
	
	List<BookingDetails> findAllByUserDeatils(UserDeatils user);

	List<BookingDetails> findAllByLoginTimeIsNullAndBookedDateEquals(LocalDate date1);
	 
	List<BookingDetails> findBySeatNo(String seatNo);
	
	BookingDetails findByUserDeatilsAndBookedDateEquals(UserDeatils user,LocalDate Bdate);
	
	List<BookingDetails> findByUserDeatilsAndBookedDateGreaterThan(UserDeatils user,LocalDate Ndate);

	BookingDetails  findByToken(String token);
	
	List<BookingDetails> findByBookedDate(LocalDate date1);

}