package com.SecureSeat.Booking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.ShiftDetails;

@Component
public class ShitDetailsDaoImpl implements ShiftDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ShiftDetails> getAllShiftDetails() {
		String sql = "SELECT * FROM shift_details";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShiftDetails.class));
	}

	@Override
	public boolean deleteShiftDetailsById(int shiftId) {
		int rowsAffected = jdbcTemplate.update("DELETE FROM shift_details WHERE shift_id = ?", shiftId);
		if (rowsAffected > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addShiftTime(String shiftTimings) {
		String sql = "INSERT INTO shift_details (shift_timings) VALUES (?)";
		jdbcTemplate.update(sql, shiftTimings);

	}
	
	@Override
	public void updateShiftTime(int id, String shiftTimings) {
		String sql="UPDATE shift_details SET shift_timings = ? WHERE shift_id = ?";
	    jdbcTemplate.update(sql,shiftTimings, id);
	}


}
