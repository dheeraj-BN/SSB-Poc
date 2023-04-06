package com.SecureSeat.Booking.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

@Component
public class ReportDaoImpl implements ReportDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	
//	//by date
//	@Override
//	public List<BookingDetails> getBookingsByDate(LocalDate date) {
//	    String sql = "SELECT * FROM booking_details WHERE booked_date = ?";
//	    List<BookingDetails> bookings = jdbcTemplate.query(sql, new Object[]{date}, new BeanPropertyRowMapper<>(BookingDetails.class));
//	    return bookings;
//	}
	
	
	


@Override
public List<BookingDetails> getBookingDetailsByDate(LocalDate bookingDate) {
    String sql = "SELECT booking_id as bookingId, booked_date as bookedDate, booked_timings as bookedTimings, booking_status as bookingStatus, food_status as foodStatus, login_time as loginTime, seat_no as seatNo, token as token , shift_id as shiftId , user_id as userId FROM booking_details WHERE booked_date =?";
    List<BookingDetails> bookingDetailsList = jdbcTemplate.query(sql, new Object[]{bookingDate}, new BeanPropertyRowMapper<>(BookingDetails.class));
    return bookingDetailsList;
}


@Override
public List<BookingDetails> getBookingsByDateAndStatus(LocalDate bookingDate, String bookingStatus) {
    String query = "SELECT booking_id as bookingId, booked_date as bookedDate, "
                 + "booked_timings as bookedTimings, booking_status as bookingStatus, "
                 + "food_status as foodStatus, login_time as loginTime, seat_no as seatNo, "
                 + "token as token, shift_id as shiftId, user_id as userId "
                 + "FROM booking_details "
                 + "WHERE booked_date = ? AND booking_status = ?";
    
    List<BookingDetails> bookingDetailsList = jdbcTemplate.query(query, new Object[]{bookingDate, bookingStatus}, new BeanPropertyRowMapper<>(BookingDetails.class));
    return bookingDetailsList;
}


//@Override
//public List<BookingDetails> getBookingDetailsByMonthYear(int month, int year) {
//
//    String sql = "SELECT u.employee_id as employeeId, bd.booked_date as bookedDate, bd.booked_timings as bookedTimings, "
//            + "bd.booking_status as bookingStatus, bd.food_status as foodStatus, bd.login_time as loginTime, "
//            + "bd.seat_no as seatNo , bd.token as token, bd.shift_id as shiftId "
//            + "FROM user_deatils u "
//            + "INNER JOIN booking_details bd ON u.user_id = bd.user_id "
//            + "WHERE MONTH(bd.booked_date) = ? AND YEAR(bd.booked_date) = ? "
//            + "ORDER BY u.employee_id, bd.booked_date";
//    
//    List<BookingDetails> bookingDetailsList = jdbcTemplate.query(sql, new Object[]{month, year}, new BeanPropertyRowMapper<>(BookingDetails.class));




//
//@Override
//public List<BookingDetails> getBookingsByDateAndStatus(LocalDate bookedDate, String bookingStatus) {
//    String query = "SELECT booking_id as bookingId, booked_date as bookedDate, "
//                 + "booked_timings as bookedTimings, booking_status as bookingStatus, "
//                 + "food_status as foodStatus, login_time as loginTime, seat_no as seatNo, "
//                 + "token as token, shift_id as shiftId, user_id as userId "
//                 + "FROM booking_details "
//                 + "WHERE booked_date = ? AND booking_status = ?";
//    
//    return jdbcTemplate.query(query, new Object[]{bookedDate, bookingStatus}, new BookingDetailsRowMapper());
//}



}




