package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeatBookDAOImpl implements SeatBookDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<String> getseatNoByBookedDate(LocalDate bookedDate) {
		String sql="SELECT seat_no from booking_details where booked_date=?";
		List<String> seatNo = jdbcTemplate.queryForList(sql, new Object[] {bookedDate},String.class);
		return seatNo;
	}

}
