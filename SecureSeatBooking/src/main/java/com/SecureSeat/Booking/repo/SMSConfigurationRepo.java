package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.SMSconfiguration;

@Repository
public interface SMSConfigurationRepo extends JpaRepository<SMSconfiguration, Integer> {

	//SMSconfiguration findByAccountSid(String accountSid);
}
