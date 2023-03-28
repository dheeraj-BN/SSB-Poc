package com.SecureSeat.Booking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoImpl implements EmployeeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void changePasswor(String password,int id) {
		String sql="UPDATE `seatsb`.`user_deatils` SET `password` = ? WHERE (`user_id` = ?)";
		jdbcTemplate.update(sql,password,id);
		
	}

}

