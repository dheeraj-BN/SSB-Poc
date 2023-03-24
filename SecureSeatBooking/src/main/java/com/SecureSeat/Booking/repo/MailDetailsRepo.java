package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.MailDetails;

@Repository
public interface MailDetailsRepo extends JpaRepository<MailDetails, Integer> {

}
