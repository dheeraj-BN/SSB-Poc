package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.Configuration;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.repo.ConfigurationRepo;
import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class SendSMSImpl implements SendSMS {
	
	//public static final String ACCOUNT_SID = "ACe165455b3f498dd288a7ffa8aa7a3d5c";
	//public static final String AUTH_TOKEN = "8c0cea3e80fdacf7b0e53ffbd75ce5f7";
	@Autowired
	private ConfigurationRepo smsConfigurationRepo;
	
	@Override
	public String SendSms(Employee employee,String body) throws AuthenticationException {
		
		Configuration sconfiguration=  smsConfigurationRepo.findById(1).get();
		Twilio.init(sconfiguration.getACCOUNT_SID(), sconfiguration.getAUTH_TOKEN());
try {

		Message message = Message.creator(new PhoneNumber("+91"+employee.getEmployeePhoneNo()),
				new PhoneNumber(sconfiguration.getSMS_NUMBER()),body).create();
}
catch (Exception e) {
	System.out.println("SMS not sent");
	return " SMS not sent";
}
return "SMS sent";
	}

}
