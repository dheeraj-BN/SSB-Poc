package com.SecureSeat.Booking.service;

import com.SecureSeat.Booking.entity.Employee;
import com.twilio.exception.AuthenticationException;

public interface SendSMS {

	String SendSms(Employee employee, String body) throws AuthenticationException;

}