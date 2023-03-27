package com.SecureSeat.Booking.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void updateBookingDetails(LocalTime loggedIn, String status, int id) throws Exception {
		
		String sql = "UPDATE `seatsb`.`booking_details` SET `login_time` = ?, `booking_status` = ? WHERE (`booking_id` = ?)";
	    jdbcTemplate.update(sql, loggedIn, status, id);
	}

}
