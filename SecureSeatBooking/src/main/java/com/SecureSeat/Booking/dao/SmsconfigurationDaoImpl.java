package com.SecureSeat.Booking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.SMSconfiguration;

@Component
public class SmsconfigurationDaoImpl implements SmsconfigurationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void changeConfig(SMSconfiguration smSconfiguration) {
	    String sql = "UPDATE `seatsb`.`smsconfiguration` SET `auth_token` = ?, `sms_number` = ? WHERE (`account_sid` = ?)";
	    jdbcTemplate.update(sql, smSconfiguration.getAUTH_TOKEN(), smSconfiguration.getSMS_NUMBER(), smSconfiguration.getACCOUNT_SID());
	}
	

}
