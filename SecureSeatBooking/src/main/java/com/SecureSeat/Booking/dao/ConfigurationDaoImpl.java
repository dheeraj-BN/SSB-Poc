package com.SecureSeat.Booking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.Configuration;

@Component
public class ConfigurationDaoImpl implements ConfigurationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void changeConfig(Configuration smSconfiguration) {
	    String sql = "UPDATE `seatsb`.`configuration` SET `auth_token` = ?, `sms_number` = ? WHERE (`account_sid` = ?)";
	    jdbcTemplate.update(sql, smSconfiguration.getAUTH_TOKEN(), smSconfiguration.getSMS_NUMBER(), smSconfiguration.getACCOUNT_SID());
	}
	
	
	@Override
	public void changeEmailConfig(Configuration configuration) {
	    String sql = "UPDATE `seatsb`.`configuration` SET `Email_Id` = ?, `Email_password` = ? WHERE (`account_sid` = ?)";
	    jdbcTemplate.update(sql, configuration.getEmail_Id(), configuration.getEamil_password(), configuration.getACCOUNT_SID());
	}
	
	@Override
	public void changeCancelHourConfig(Configuration configuration) {
	    String sql = "UPDATE `seatsb`.`configuration` SET `Seat_Cancelation_time` = ? WHERE (`account_sid` = ?)";
	    jdbcTemplate.update(sql, configuration.getSeat_Cancelation_time(), configuration.getACCOUNT_SID());
	}
	

}
