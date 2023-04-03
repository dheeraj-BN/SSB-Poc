package com.SecureSeat.Booking.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

@Component
public class EmployeeDaoImpl implements EmployeeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	
//	public List<BookingDetails> lastBookingDetails() {
//		String sql="SELECT * from booking_details where user_id=? ORDER BY booking_id DESC LIMIT";
//		
//		
//	}
	
	
	
	
	@Override
	public void changePasswor(String password,int id) {
		String sql="UPDATE `seatsb`.`user_deatils` SET `password` = ? WHERE (`user_id` = ?)";
		jdbcTemplate.update(sql,password,id);
		
	}
	
	@Override
	public int forgotPassword(String phoneNo) {
		  String sql= "select ud.user_id from employee em INNER JOIN user_deatils ud ON ud.employee_id=em.employee_id "
		  		+ "where employee_phone_no=?";
		  int  userid = jdbcTemplate.queryForObject(sql, new Object[] {phoneNo},Integer.class);
		  System.out.println(userid);
			return userid;
	}
	
	@Override
	public void restPassword(String password,int userid) {
		String sql="Update user_deatils  set password=? where user_id =?";
		jdbcTemplate.update(sql,password,userid);
	}
	
	@Override
	public void otp(String Otp,int userid) {
		String sql="Update user_deatils  set password=? where user_id =?";
		jdbcTemplate.update(sql,Otp,userid);
	}

	
	
	@Override
	public List<BookingDetails> getEmpBookedInfoBookedNext(int id , LocalDate date){
		String sql="select * from booking_details  bd INNER JOIN shift_details sh ON sh.shift_id = bd.shift_id INNER JOIN employee em  \r\n"
				+ " INNER JOIN user_deatils  ud  ON em.employee_id = ud.employee_id \r\n"
				+ " INNER JOIN users_roles ur  ON ur.user_id=ud.user_id \r\n"
				+ "INNER JOIN role r ON r.role_id = ur.role_id\r\n"
				+ "ON ud.user_id=bd.user_id where ud.user_id=? AND booked_date>? AND booking_status='PENDING'";
		List<BookingDetails> bookingdetails = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[] {id,date} );
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
	public void changePasswordAndMakeStatusTrue(String password,int id) {
		String sql=" UPDATE `seatsb`.`user_deatils` SET `password` = ? , `status` = true WHERE (`user_id` = ?) ";
		jdbcTemplate.update(sql,password,id);
		
	}
}

