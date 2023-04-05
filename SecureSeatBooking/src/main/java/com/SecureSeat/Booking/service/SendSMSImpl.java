package com.SecureSeat.Booking.service;

import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.Employee;
import com.twilio.Twilio;

import com.twilio.exception.AuthenticationException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class SendSMSImpl implements SendSMS {
	
	public static final String ACCOUNT_SID = "ACe165455b3f498dd288a7ffa8aa7a3d5c";
	public static final String AUTH_TOKEN = "8c0cea3e80fdacf7b0e53ffbd75ce5f7";
	
	
	@Override
	public String SendSms(Employee employee,String body) throws AuthenticationException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
try {

		Message message = Message.creator(new PhoneNumber("+91"+employee.getEmployeePhoneNo()),
				new PhoneNumber("+15855752981"),body).create();
}
catch (Exception e) {
	return " SMS not sent";
}
return "SMS sent";
	}

}
