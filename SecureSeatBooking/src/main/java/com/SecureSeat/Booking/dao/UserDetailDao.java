package com.SecureSeat.Booking.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;

@Component
public class UserDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void updateBookingDetails(LocalTime loggedIn, String status, int id) throws Exception {
		
		String sql = "UPDATE `seatsb`.`booking_details` SET `login_time` = ?, `booking_status` = ? WHERE (`booking_id` = ?)";
	    jdbcTemplate.update(sql, loggedIn, status, id);
	}
	
	
	public Employee getEmployeeDetailsAfterValidationSuccess(String token) {
		
		String sql = "SELECT e.* FROM booking_details bd " +
                "INNER JOIN user_deatils ud ON bd.user_id = ud.user_id " +
                "INNER JOIN employee e ON ud.employee_id = e.employee_id " +
                "WHERE bd.token = ?";
		
		
		 return jdbcTemplate.queryForObject(sql, new Object[]{token}, (rs, rowNum) ->
         new Employee(
                 rs.getInt("employee_id"),
                 rs.getString("employee_name"),
                 rs.getString("employee_email"),
                 rs.getString("employee_designation"),
                 rs.getString("employee_phone_no"),
                 rs.getString("employee_gender"),
                 rs.getString("employee_personal_email")
         ));
	}
	
	
	public UserDeatils getAdminInfo() {
		String sql="select * from user_deatils  ud INNER JOIN users_roles ur  ON ur.user_id=ud.user_id \r\n"
				+ "INNER JOIN role r ON r.role_id = ur.role_id where r.role_name ='ADMIN'";
//		return jdbcTemplate.queryForObject(sql, new Object[] ,(rs,rowNum) ->
//			new UserDeatils(
//					rs.getInt("user_id"),
//					rs.getString("password"),
//					rs.getObject("employee_id")
//					)
//				);
		
		
		return (UserDeatils) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserDeatils.class));
	}

}
