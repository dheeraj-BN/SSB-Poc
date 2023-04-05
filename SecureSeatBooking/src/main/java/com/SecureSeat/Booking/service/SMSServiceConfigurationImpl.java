package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.dao.SmsconfigurationDao;
import com.SecureSeat.Booking.entity.SMSconfiguration;
import com.SecureSeat.Booking.repo.SMSConfigurationRepo;

@Service
public class SMSServiceConfigurationImpl implements SMSServiceConfiguration {
	
	@Autowired
	private SmsconfigurationDao smsconfigurationDao;
	
	
	
	
	@Override
	public void SMSConfigsave(SMSconfiguration smSconfiguration) {
		
		
		smsconfigurationDao.changeConfig(smSconfiguration);
		
		
	}
	
	

}
