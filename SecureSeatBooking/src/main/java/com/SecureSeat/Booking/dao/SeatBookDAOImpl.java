package com.SecureSeat.Booking.dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Component
public class SeatBookDAOImpl implements SeatBookDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	@Override
	public List<String> getseatNoByBookedDate(LocalDate bookedDate) {
		String sql="SELECT seat_no from booking_details where booked_date=?";
		List<String> seatNo = jdbcTemplate.queryForList(sql, new Object[] {bookedDate},String.class);
		return seatNo;
	}
	
//	@Override
//	public List<BookingDetails> getbookingdetailsbydate(LocalDate bookedDate) {
//		String sql="SELECT * from booking_details where booked_date=?";
//		List<BookingDetails> bookingdetails = new ArrayList<>();
//		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[] {bookedDate});
//		for(Map row : rows) {
//			BookingDetails book  = new BookingDetails();
//			Time sqltime = (Time) row.get("BOOKED_TIMINGS");
//			LocalTime bookedtime = sqltime.toLocalTime();
//			Time sqllogintime = (Time) row.get("LOGIN_TIME");
//			LocalTime logintime;
//			if(sqllogintime==null) {
//				logintime = null;
//			}
//			else {
//				logintime = sqllogintime.toLocalTime();
//			}
//			Date sqldate = (Date) row.get("BOOKED_DATE");
//			LocalDate bookeddate = sqldate.toLocalDate();
//			book.setBookingId((Integer)row.get("BOOKING_ID"));
//  		book.setDate(bookeddate);
//	book.setBookedTimings( bookedtime);
//			book.setBookingStatus((String) row.get("BOOKING_STATUS"));
////			book.setFoodStatus((Boolean) row .get("FOODSTATUS"));
//			book.setLoginTime(logintime);
//			book.setSeatNo((String) row.get("SEATNO"));
//			book.setToken((String) row.get("TOKEN"));
////			Integer user =(Integer) row.get("USER_ID"); 
////			UserDeatils userDeatils = userDetailsRepo.getById(user);
////			book.setUserDeatils(userDeatils);
//			
//			bookingdetails.add(book);
//			
//		}
//		return bookingdetails;
//	}

	
}
