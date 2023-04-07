package com.SecureSeat.Booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureSeat.Booking.entity.Configuration;

@Repository
public interface ConfigurationRepo extends JpaRepository<Configuration, Integer> {

	//SMSconfiguration findByAccountSid(String accountSid);
}
