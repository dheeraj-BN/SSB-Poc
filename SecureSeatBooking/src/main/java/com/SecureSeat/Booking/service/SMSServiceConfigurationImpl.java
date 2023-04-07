package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.dao.ConfigurationDao;
import com.SecureSeat.Booking.entity.Configuration;

@Service
public class SMSServiceConfigurationImpl implements SMSServiceConfiguration {
	
	@Autowired
	private ConfigurationDao smsconfigurationDao;
	
	
	
	
	@Override
	public void SMSConfigsave(Configuration smSconfiguration) {
		
		
		smsconfigurationDao.changeConfig(smSconfiguration);
		
		
	}
	
	
	@Override
	public void emailChange(Configuration emailConfigration) {
		smsconfigurationDao.changeEmailConfig(emailConfigration);
	}
	
	@Override
	public void hourChange(Configuration emailConfigration) {
		smsconfigurationDao.changeCancelHourConfig(emailConfigration);
	}
	

}
