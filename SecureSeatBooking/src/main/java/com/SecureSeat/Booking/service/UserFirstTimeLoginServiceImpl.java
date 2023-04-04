package com.SecureSeat.Booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.dao.EmployeeDAO;

@Service
public class UserFirstTimeLoginServiceImpl implements UserFirstTimeLoginService {

	private static final Logger logger = LoggerFactory.getLogger(UserFirstTimeLoginServiceImpl.class);

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private SecurityConfig config;

	@Autowired
	private MailService mailService;

	@Override
	public String firstTimeChangeOfPassword(int userId, String newPassword) {

		try {
			// Check if the new password is the same as the default password
			if (newPassword.equals("Alpha@2022")) {
				return "Your password cannot be the same as the default password.";
			}

			// Encode the new password using the password encoder from the config
			String encodedPassword = config.passwordEncoder().encode(newPassword);

			// Call the employeeDAO to change the password and set the status to true
			employeeDAO.changePasswordAndMakeStatusTrue(encodedPassword, userId);

			// Send a confirmation email to the user
			mailService.passwordChangeConfirmMail(userId);

			return "Password changed successfully.";
		} catch (Exception e) {
			// Log any exceptions that occur
			logger.error("An error occurred while changing the password.", e);
			return "An error occurred while changing the password. Please try again later.";
		}
	}

}
