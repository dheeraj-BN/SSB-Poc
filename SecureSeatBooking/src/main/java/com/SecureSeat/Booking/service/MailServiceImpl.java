package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.dao.UserDetailDao;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = LoggerFactory.getLogger(SendMailImpl.class);

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private MailTemplates mailTemplates;

	@Autowired
	private UserDetailsRepo userDetailsRepo;

	@Autowired
	private UserDetailDao userDetailDao;

	@Override
	@Scheduled(cron = "0 0 10 * * ?") // runs every day at 10:00 AM
	public void schecludedMailForDailyBooking() {
		// get the current date
		LocalDate currentDate = LocalDate.now();
		logger.info("Started sending daily booking reminder emails for {}", currentDate);

		// retrieve a list of bookings with status "PENDING" and booked date equals to
		// the current date
		List<BookingDetails> bookingDL = bookingDetailsRepo.findAllByBookingStatusAndBookedDateEquals("PENDING",
				currentDate);

		try {
			// iterate over each booking and send a daily reminder email
			for (BookingDetails bookingDetails : bookingDL) {
				mailTemplates.dailyBookedSeatReminder(bookingDetails);
				logger.info("Completed sending daily booking reminder email for booking ID {}",
						bookingDetails.getBookingId());
			}
			logger.info("Completed sending daily booking reminder emails for {}", currentDate);
		} catch (Exception e) {
			// if an exception is thrown while sending emails, log the error message and
			// stack trace
			logger.error("Error occurred while sending daily booking reminder emails: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public String passwordChangeConfirmMail(int id) {
		// retrieve user details for the given user ID
		UserDeatils usr = userDetailsRepo.findByUserId(id).orElse(null);
		if (usr == null) {
			// if user details are not found, log a warning and return "FAILURE"
			logger.warn("User details not found for user ID {}", id);
			return "FAILURE";
		}
		// send a password change confirmation email to the user
		mailTemplates.passwordChangeMail(usr);
		logger.info("Password change confirmation mail sent to user {}", usr.getEmployee().getEmployeeEmail());
		return "SUCCESS";
	}

	@Override
	public String addedFloor(FloorDetails floorDetails) {
		// get the admin info and email
		Employee emp = userDetailDao.getAdminInfo();
		String adminEmail = emp.getEmployeePersonalEmail();
		logger.info("Sending added floor notification email to admin at {}", adminEmail);

		// send the added floor notification email to the admin
		mailTemplates.addedFloorTemplete(floorDetails, adminEmail);

		return "SUCCESS";
	}

	@Override
	public void updateBookingDetails(BookingDetails oldBookingDetails, BookingDetails newBookingDetails) {
		try {
			// compare the old and new food status of the booking details
			int i = Boolean.compare(oldBookingDetails.isFoodStatus(), newBookingDetails.isFoodStatus());
			if (!oldBookingDetails.getSeatNo().equals(newBookingDetails.getSeatNo()) && (i != 0)) {
				// if the seat number and food status have both changed, send an email
				// notification
				mailTemplates.updateBookingDetails(oldBookingDetails, newBookingDetails);
				logger.info("Sent booking details update email to user {} with seat number {} and food status {}",
						newBookingDetails.getUserDeatils().getEmployee().getEmployeeEmail(),
						newBookingDetails.getSeatNo(), newBookingDetails.isFoodStatus());
			} else if (i == 0) {
				// if only the seat number has changed, send an email notification
				mailTemplates.updateSeatNumber(oldBookingDetails, newBookingDetails);
				logger.info("Sent seat number update email to user {} with seat number {}",
						newBookingDetails.getUserDeatils().getEmployee().getEmployeeEmail(),
						newBookingDetails.getSeatNo());
			} else {
				// if only the food status has changed, send an email notification
				mailTemplates.updateFoodStatus(newBookingDetails);
				logger.info("Sent food status update email to user {} with food status {}",
						newBookingDetails.getUserDeatils().getEmployee().getEmployeeEmail(),
						newBookingDetails.isFoodStatus());
			}
		} catch (Exception e) {
			logger.error("Error occurred while updating booking details: {}", e.getMessage());
			e.printStackTrace();
		}
	}

}
