package com.SecureSeat.Booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecureSeatBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureSeatBookingApplication.class, args);
	}

}
