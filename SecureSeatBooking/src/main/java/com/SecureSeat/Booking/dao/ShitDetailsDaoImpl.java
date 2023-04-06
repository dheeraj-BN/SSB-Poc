package com.SecureSeat.Booking.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.ShiftDetails;

@Component
public class ShitDetailsDaoImpl implements ShiftDetailsDao {

	// Initialize logger
	private static final Logger LOGGER = LoggerFactory.getLogger(ShitDetailsDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Retrieve all shift details from the database
	@Override
	public List<ShiftDetails> getAllShiftDetails() {
		String sql = "SELECT * FROM shift_details";
		List<ShiftDetails> shiftDetailsList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShiftDetails.class));

		// Log the retrieval of shift details
		LOGGER.info("Retrieved {} shift details from the database", shiftDetailsList.size());

		return shiftDetailsList;
	}

	// Delete shift details from the database by shift ID
	@Override
	public boolean deleteShiftDetailsById(int shiftId) {
		int rowsAffected = jdbcTemplate.update("DELETE FROM shift_details WHERE shift_id = ?", shiftId);
		if (rowsAffected > 0) {
			// Log the successful deletion of shift details
			LOGGER.info("Deleted shift details with ID {} from the database", shiftId);
			return true;
		} else {
			return false;
		}
	}

	// Add a new shift time to the database
	@Override
	public void addShiftTime(String shiftTimings) {
		String sql = "INSERT INTO shift_details (shift_timings) VALUES (?)";
		jdbcTemplate.update(sql, shiftTimings);

		// Log the addition of a new shift time
		LOGGER.info("Added new shift time '{}' to the database", shiftTimings);
	}
	
	// Update a shift time in the database by shift ID
	@Override
	public void updateShiftTime(int id, String shiftTimings) {
		String sql="UPDATE shift_details SET shift_timings = ? WHERE shift_id = ?";
	    jdbcTemplate.update(sql,shiftTimings, id);

	    // Log the update of a shift time
	    LOGGER.info("Updated shift time for ID {} to '{}'", id, shiftTimings);
	}
}
