package com.SecureSeat.Booking.dao;

import com.SecureSeat.Booking.entity.Configuration;

public interface ConfigurationDao {

	void changeConfig(Configuration smSconfiguration);

	void changeEmailConfig(Configuration configuration);

	void changeCancelHourConfig(Configuration configuration);

}