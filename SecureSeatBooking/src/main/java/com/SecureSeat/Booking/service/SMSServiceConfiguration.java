package com.SecureSeat.Booking.service;

import com.SecureSeat.Booking.entity.Configuration;

public interface SMSServiceConfiguration {

	void SMSConfigsave(Configuration smSconfiguration);

	void emailChange(Configuration emailConfigration);

	void hourChange(Configuration emailConfigration);

}