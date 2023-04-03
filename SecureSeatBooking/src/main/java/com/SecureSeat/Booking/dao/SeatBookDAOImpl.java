package com.SecureSeat.Booking.dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.ShiftDetails;
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
	
	@Override
	public List<BookingDetails> getbookingdetailsbydate(LocalDate bookedDate) {
		String sql="select * from booking_details  bd INNER JOIN shift_details sh ON sh.shift_id = bd.shift_id INNER JOIN employee em  \r\n"
				+ " INNER JOIN user_deatils  ud  ON em.employee_id = ud.employee_id \r\n"
				+ " INNER JOIN users_roles ur  ON ur.user_id=ud.user_id \r\n"
				+ "INNER JOIN role r ON r.role_id = ur.role_id\r\n"
				+ "ON ud.user_id=bd.user_id where booked_date=?";
		List<BookingDetails> bookingdetails = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[] {bookedDate});
		for(Map row : rows) {
			BookingDetails book  = new BookingDetails();
			Time sqltime = (Time) row.get("BOOKED_TIMINGS");
			LocalTime bookedtime = sqltime.toLocalTime();
			Time sqllogintime = (Time) row.get("LOGIN_TIME");
			LocalTime logintime;
			if(sqllogintime==null) {
				logintime = null;
			}
			else {
				logintime = sqllogintime.toLocalTime();
			}
			Date sqldate = (Date) row.get("BOOKED_DATE");
			LocalDate bookeddate = sqldate.toLocalDate();
			book.setBookingId((Integer)row.get("BOOKING_ID"));
  		book.setDate(bookeddate);
	book.setBookedTimings( bookedtime);
			book.setBookingStatus((String) row.get("BOOKING_STATUS"));
//			book.setFoodStatus((Boolean) row .get("FOODSTATUS"));
			book.setLoginTime(logintime);
			book.setSeatNo((String) row.get("SEAT_NO"));
			book.setToken((String) row.get("TOKEN"));
            UserDeatils user = new UserDeatils();
            user.setUserId((Integer) row.get("USER_ID"));
            user.setPassword((String) row.get("PASSWORD"));
            Employee emp = new Employee();
           emp.setEmployeeId((Integer) row.get("EMPLOYEE_ID"));
			emp.setEmployeeName((String) row .get("EMPLOYEE_NAME"));
			emp.setEmployeeDesignation((String) row.get("EMPLOYEE_DESIGNATION"));
			emp.setEmployeeEmail((String) row.get("EMPLOYEE_EMAIL"));
			emp.setEmployeeGender((String) row.get("EMPLOYEE_GENDER"));
			emp.setEmployeePersonalEmail((String) row.get("EMPLOYEE_PERSONAL_EMAIL"));
			emp.setEmployeePhoneNo((String) row.get("EMPLOYEE_PHONE_NO"));
			Set<Role> roles = new HashSet<Role>();
			Role role = new Role();
			role.setRoleId((Integer) row.get("ROLE_ID"));
			role.setRoleName((String) row.get("ROLE_NAME"));
			roles.add(role);
			user.setRoles(roles);
			user.setEmployee(emp);
			book.setUserDeatils(user);
			ShiftDetails shift = new ShiftDetails();
			shift.setShiftId((Integer) row.get("SHIFT_ID"));
			shift.setShiftTimings((String) row.get("SHIFT_TIMINGS"));
			book.setShiftDetails(shift);
			bookingdetails.add(book);
			
			
		}
		return bookingdetails;
	}
	
	@Override
	public List<BookingDetails> getbookingdetailsbydateandbookingstatus(LocalDate bookedDate ) {
		String sql="select * from booking_details  bd INNER JOIN shift_details sh ON sh.shift_id = bd.shift_id INNER JOIN employee em  \r\n"
				+ " INNER JOIN user_deatils  ud  ON em.employee_id = ud.employee_id and ud.user_id = bd.user_id\r\n"
				+ " INNER JOIN users_roles ur  ON ur.user_id=ud.user_id \r\n"
				+ "INNER JOIN role r ON r.role_id = ur.role_id\r\n"
				+ "where booked_date=? and booking_status='PENDING'";
		List<BookingDetails> bookingdetails = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[] {bookedDate});
		for(Map row : rows) {
			BookingDetails book  = new BookingDetails();
			Time sqltime = (Time) row.get("BOOKED_TIMINGS");
			LocalTime bookedtime = sqltime.toLocalTime();
			Time sqllogintime = (Time) row.get("LOGIN_TIME");
			LocalTime logintime;
			if(sqllogintime==null) {
				logintime = null;
			}
			else {
				logintime = sqllogintime.toLocalTime();
			}
			Date sqldate = (Date) row.get("BOOKED_DATE");
			LocalDate bookeddate = sqldate.toLocalDate();
			book.setBookingId((Integer)row.get("BOOKING_ID"));
  		book.setDate(bookeddate);
	book.setBookedTimings( bookedtime);
			book.setBookingStatus((String) row.get("BOOKING_STATUS"));
//			book.setFoodStatus((Boolean) row .get("FOODSTATUS"));
			book.setLoginTime(logintime);
			book.setSeatNo((String) row.get("SEAT_NO"));
			book.setToken((String) row.get("TOKEN"));
            UserDeatils user = new UserDeatils();
            user.setUserId((Integer) row.get("USER_ID"));
            user.setPassword((String) row.get("PASSWORD"));
            Employee emp = new Employee();
           emp.setEmployeeId((Integer) row.get("EMPLOYEE_ID"));
			emp.setEmployeeName((String) row .get("EMPLOYEE_NAME"));
			emp.setEmployeeDesignation((String) row.get("EMPLOYEE_DESIGNATION"));
			emp.setEmployeeEmail((String) row.get("EMPLOYEE_EMAIL"));
			emp.setEmployeeGender((String) row.get("EMPLOYEE_GENDER"));
			emp.setEmployeePersonalEmail((String) row.get("EMPLOYEE_PERSONAL_EMAIL"));
			emp.setEmployeePhoneNo((String) row.get("EMPLOYEE_PHONE_NO"));
			Set<Role> roles = new HashSet<Role>();
			Role role = new Role();
			role.setRoleId((Integer) row.get("ROLE_ID"));
			role.setRoleName((String) row.get("ROLE_NAME"));
			roles.add(role);
			user.setRoles(roles);
			user.setEmployee(emp);
			book.setUserDeatils(user);
			ShiftDetails shift = new ShiftDetails();
			shift.setShiftId((Integer) row.get("SHIFT_ID"));
			shift.setShiftTimings((String) row.get("SHIFT_TIMINGS"));
			book.setShiftDetails(shift);
			bookingdetails.add(book);
			
			
		}
		return bookingdetails;
	}
	
	@Override
	public int getbookingidfromtoken(String token){
		String sql="select booking_id from booking_details   where token=?";
		int  bookingid = jdbcTemplate.queryForObject(sql, new Object[] {token},Integer.class);
		return bookingid;
	}
	
	@Override
	public void updatebookingstatus(int bookingid) {
		String sql="Update booking_details  set booking_status = 'CANCELLED' where booking_id =?";
		jdbcTemplate.update(sql,bookingid);
	}
	
	@Override
	public void updateseatNo(String seatno,int bookingid) {
		String sql="Update booking_details  set seat_no=? where booking_id =?";
		jdbcTemplate.update(sql,seatno,bookingid);
	}
	
	@Override
	public void updatefoodstatus(Boolean foodstatus,int bookingid) {
		String sql="Update booking_details  set food_status=? where booking_id =?";
		jdbcTemplate.update(sql, foodstatus,bookingid);
	}
	
	@Override
	public void updateseatbooking(Boolean foodstatus,String seatno,int bookingid) {
		String sql="Update booking_details  set food_status=?,seat_no=? where booking_id =?";
		jdbcTemplate.update(sql,foodstatus,seatno,bookingid);
	}
	

	
}
