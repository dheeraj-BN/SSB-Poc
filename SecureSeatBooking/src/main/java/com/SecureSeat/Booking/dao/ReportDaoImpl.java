package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

@Component
public class ReportDaoImpl implements ReportDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	public List<BookingDetails> getBookingsForDate(LocalDate date) {
//	    String sql = "SELECT * FROM booking_details WHERE booked_date = ?";
//	    List<BookingDetails> bookings = jdbcTemplate.query(sql, new Object[]{date}, new BookingDetailsRowMapper());
//	    return bookings;
//	}
//

	
	//by date
	@Override
	public List<BookingDetails> getBookingsByDate(LocalDate date) {
	    String sql = "SELECT * FROM booking_details WHERE booked_date = ?";
	    List<BookingDetails> bookings = jdbcTemplate.query(sql, new Object[]{date}, new BeanPropertyRowMapper<>(BookingDetails.class));
	    return bookings;
	}
	
	
	
//	public List<BookingDetails> getBookingsByMonthAndYear(int month, int year) {
//	    String sql = "SELECT u.employee_id, bd.booked_date, bd.booked_timings, bd.booking_status, bd.food_status, bd.login_time, bd.seat_no, bd.token, bd.shift_id " +
//	                 "FROM user_details u " +
//	                 "INNER JOIN booking_details bd ON u.user_id = bd.user_id " +
//	                 "WHERE MONTH(bd.booked_date) = ? AND YEAR(bd.booked_date) = ? " +
//	                 "ORDER BY u.employee_id, bd.booked_date";
//
//	    return jdbcTemplate.query(sql, new Object[]{month, year}, (rs, rowNum) -> {
//	        BookingDetails booking = new BookingDetails();
//	        booking.setUserDeatils(new UserDeatils());
//	        booking.setShiftDetails(new ShiftDetails());
//
//	        booking.getUserDeatils().setEmployee(rs.getString("employee_id"));
//	        booking.setDate(rs.getDate("booked_date").toLocalDate());
//	        booking.setBookedTimings(rs.getTime("booked_timings").toLocalTime());
//	        booking.setBookingStatus(rs.getString("booking_status"));
//	        booking.setFoodStatus(rs.getBoolean("food_status"));
//	        booking.setLoginTime(rs.getTime("login_time").toLocalTime());
//	        booking.setSeatNo(rs.getString("seat_no"));
//	        booking.setToken(rs.getString("token"));
//	        booking.getShiftDetails().setShiftId(rs.getInt("shift_id"));
//
//	        return booking;
//	    });
//	}
	
	
	//by month
	@Override
	public List<BookingDetails> getBookingsByMonthAndYear(int month, int year) {
		String sql = "SELECT u.employee_id, bd.booked_date, bd.booked_timings, bd.booking_status, bd.food_status, bd.login_time, bd.seat_no, bd.token, bd.shift_id "
				+ "FROM user1 u "
				+ "INNER JOIN booking_details bd ON u.user_id = bd.user_id "
				+ "WHERE MONTH(bd.booked_date) = ? AND YEAR(bd.booked_date) = ? "
				+ "ORDER BY u.employee_id, bd.booked_date";
		
		return jdbcTemplate.query(sql, new Object[] { month, year }, new BeanPropertyRowMapper<>(BookingDetails.class));
	}
	
	
//by month and emp id
@Override
public List<BookingDetails> getBookingDetailsByEmployeeIdAndMonthYear(int employeeId, int month, int year) {
    String query = "SELECT u.employee_id, bd.booked_date, bd.booked_timings, bd.booking_status, bd.food_status, bd.login_time, bd.seat_no, bd.token, bd.shift_id " +
                   "FROM user1 u " +
                   "INNER JOIN booking_details bd ON u.user_id = bd.user_id " +
                   "WHERE u.employee_id = ? AND MONTH(bd.booked_date) = ? AND YEAR(bd.booked_date) = ? " +
                   "ORDER BY bd.booked_date";
    
    return jdbcTemplate.query(query, new Object[]{employeeId, month, year}, (rs, rowNum) -> 
        new BookingDetails(rs.getString("seat_no"), rs.getBoolean("food_status"), rs.getDate("booked_date").toLocalDate(), rs.getTime("booked_timings").toLocalTime(),
                            rs.getTime("login_time").toLocalTime(), rs.getString("booking_status"), rs.getString("token"), null, null)
    );
}

	
	


}
