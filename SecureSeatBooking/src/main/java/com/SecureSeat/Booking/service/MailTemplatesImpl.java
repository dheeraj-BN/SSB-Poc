package com.SecureSeat.Booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.entity.UserDeatils;

@Service
public class MailTemplatesImpl implements MailTemplates {

	private static final Logger logger = LoggerFactory.getLogger(MailTemplatesImpl.class);

	@Autowired
	private SendMail sendMail;

	/**
	 * Sends a registration mail to a user with their login credentials.
	 * 
	 * @param userInfo The user details object containing the user's information.
	 * @param pass     The password generated for the user.
	 */
	@Override
	public void registrationMail(UserDeatils userInfo, String pass) {
		try {
			// code to send registration mail
			Employee emp = userInfo.getEmployee();
			String subject = "Secure Seat Booking Registed";
			String body = "Hello " + emp.getEmployeeName() + ", \n\n  Welcome To Secure Seat Booking(SSB) Valtech \n\n"
					+ "Below are your login credentials:\r\n" + "Username: " + emp.getEmployeeEmail() + "\n Password : "
					+ pass
					+ "\n Please keep these credentials safe and secure. We recommend that you change your password periodically to ensure the security of your account.\r\n"
					+ "\r\n"
					+ "If you have any questions or concerns, please do not hesitate to contact our customer support team. We are always here to assist you in any way we can."
					+ "\n\nBest regards," + "\n\nAdmin" + "\nSecure Seat Booking";
			sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
			logger.info("Sending registration mail to user {}", userInfo.getEmployee().getEmployeeEmail());

		} catch (Exception e) {
			logger.error("Error occurred while sending registration mail: {}", e.getMessage());
			throw new RuntimeException("Registration mail could not be sent.");
		}

	}

	@Override
	public void dailyBookedSeatReminder(BookingDetails bookingDetails) {
		try {
			Employee emp = bookingDetails.getUserDeatils().getEmployee();
			String subject = "Secure Seat Booking Scan Remainder";
			String body = "Dear " + emp.getEmployeeName() + ",\n\n"
					+ "We hope this email finds you well. We would like to inform you that starting from "
					+ bookingDetails.getDate()
					+ ", we will be implementing a new system to ensure the safety and well-being of our employees. To access the office premises, all employees will be required to scan their reserved seat QR code upon entry"
					+ "Seat Details:\r\n" + "Booking Id: " + bookingDetails.getBookingId() + "\r\n" + "Seat Number: "
					+ bookingDetails.getSeatNo() + "\r\n" + "Shift Timmings: "
					+ bookingDetails.getShiftDetails().getShiftTimings() + "\r\n" + "Food Option: "
					+ bookingDetails.isFoodStatus() + "\r\n"
					+ "Thank you for your cooperation and understanding as we work to ensure the safety and well-being of our employees.\r\n"
					+ "\r\n" + "Best regards,\r\n" + "\r\n" + "Admin\r\n" + "Secure Seat Booking";
			sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
			logger.info("Daily booked seat reminder email sent to user {}", emp.getEmployeeEmail());
		} catch (Exception e) {
			logger.error("An error occurred while sending daily booked seat reminder email: {}", e.getMessage());
		}
	}

	@Override
	public void passwordChangeMail(UserDeatils userInfo) {
		try {
			Employee emp = userInfo.getEmployee();
			String subject = "Password Change Successful";
			String body = "Dear " + emp.getEmployeeName() + ",\r\n" + "\r\n"
					+ "We are writing to confirm that your password has been successfully changed. Your account security is important to us, and we appreciate your effort to maintain it.\r\n"
					+ "\r\n"
					+ "If you did not make this change, please contact us immediately to ensure the security of your account.\r\n"
					+ "\r\n"
					+ "Thank you for your cooperation in keeping your account secure. If you have any questions or concerns, please do not hesitate to contact our customer support team.\r\n"
					+ "\r\n" + "Best regards,\r\n" + "\r\n" + "Admin\r\n" + "Secure Seat Booking";
			sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
			logger.info("Password change confirmation email sent to " + emp.getEmployeeEmail());
		} catch (Exception e) {
			logger.error("Error sending password change confirmation email: " + e.getMessage());
		}
	}

	@Override
	public void addedFloorTemplete(FloorDetails floorDetails, String adminEmail) {
		String subject = "New Floor added to building";
		String body = "Dear Admin,\r\n" + "\r\n"
				+ "We are pleased to inform you that we have successfully added a new floor to our office building"
				+ floorDetails.getFloorName() + ". The new floor is now ready for use with "
				+ floorDetails.getNoOfSeats()
				+ " number of seats and we have already completed all necessary installations and inspections.\r\n"
				+ "\r\n"
				+ "We would like to take this opportunity to thank you for your support and cooperation throughout this process. We appreciate your patience and understanding as we worked to expand our facilities.\r\n"
				+ "\r\n" + "Best regards,\r\n" + "\r\n" + "Developer\r\n" + "Secure Seat Booking";

		try {
			// send email to admin
			sendMail.sendMail(adminEmail, subject, body);

			// log successful email delivery
			logger.info("Added floor template email sent successfully to admin {}", adminEmail);
		} catch (Exception e) {
			// log error if email sending failed
			logger.error("Error sending added floor template email to admin {}", adminEmail, e);
		}
	}

	@Override
	public void dailyBookedSeatCancled(BookingDetails bookingDetails) {
		try {
			Employee emp = bookingDetails.getUserDeatils().getEmployee();
			String subject = "Booked Seat Cancelled";
			String body = "Dear " + emp.getEmployeeName() + ",\n\n"
					+ "We hope this email finds you well. We would like to inform you that your booked seat on "
					+ bookingDetails.getDate() + " has been cancelled as it has exceeded the scan limit. "
					+ "Seat Details:\r\n" + "Booking Id: " + bookingDetails.getBookingId() + "\r\n" + "Seat Number: "
					+ bookingDetails.getSeatNo() + "\r\n" + "Shift Timings: "
					+ bookingDetails.getShiftDetails().getShiftTimings() + "\r\n" + "Food Option: "
					+ bookingDetails.isFoodStatus() + "\r\n" + "Thank you for your cooperation and understanding.\r\n"
					+ "\r\n" + "Best regards,\r\n" + "\r\n" + "Admin\r\n" + "Secure Seat Booking";
			sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
			logger.info("Email sent successfully to " + emp.getEmployeeEmail() + " for the cancelled booking with ID "
					+ bookingDetails.getBookingId());
		} catch (Exception e) {
			logger.error("Error sending email for the cancelled booking with ID " + bookingDetails.getBookingId() + ": "
					+ e.getMessage());
		}
	}

	@Override
	public void cancledYourBooking(BookingDetails bookingDetails) {
		try {
			Employee emp = bookingDetails.getUserDeatils().getEmployee();
			String subject = "Booked Seat Cancelled";
			String body = "Dear " + emp.getEmployeeName() + ",\n\n"
					+ "We hope this email finds you well. We would like to inform you that your booked seat on "
					+ bookingDetails.getDate() + " has been cancelled by you. " + "Seat Details:\r\n" + "Booking Id: "
					+ bookingDetails.getBookingId() + "\r\n" + "Seat Number: " + bookingDetails.getSeatNo() + "\r\n"
					+ "Shift Timings: " + bookingDetails.getShiftDetails().getShiftTimings() + "\r\n" + "Food Option: "
					+ bookingDetails.isFoodStatus() + "\r\n" + "Thank you for your cooperation and understanding.\r\n"
					+ "\r\n" + "Best regards,\r\n" + "\r\n" + "Admin\r\n" + "Secure Seat Booking";
			sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
			logger.info(
					"Cancellation email sent to employee " + emp.getEmployeeName() + " at " + emp.getEmployeeEmail());
		} catch (NullPointerException e) {
			logger.error("NullPointerException in cancledYourBooking method: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Exception in cancledYourBooking method: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void updateSeatNumber(BookingDetails oldBookingDetails, BookingDetails newBookingDetails) {
		try {
			// Get employee details from old booking
			Employee emp1 = oldBookingDetails.getUserDeatils().getEmployee();

			// Create email message
			String subject = "Booked Seat Updated";
			String body = "Dear " + emp1.getEmployeeName() + ",\n\n"
					+ "Your seat booking details have been updated for the Date: " + oldBookingDetails.getDate()
					+ "\n\nSeat Details:\r\n" + "Booking Id: " + oldBookingDetails.getBookingId() + "\r\n"
					+ "From Seat Number: " + oldBookingDetails.getSeatNo() + "\r\n" + "To New Seat Number: "
					+ newBookingDetails.getSeatNo() + "\r\n" + "\r\nBest regards,\r\n" + "Admin\r\n"
					+ "Secure Seat Booking";

			// Send email to employee
			sendMail.sendMail(emp1.getEmployeeEmail(), subject, body);

			// Log success message
			logger.info("Successfully sent email to employee " + emp1.getEmployeeName()
					+ " regarding updated seat number.");
		} catch (Exception e) {
			// Log error message and stack trace
			logger.error("Error occurred while sending email regarding updated seat number: " + e.getMessage(), e);
		}
	}

	@Override
	public void updateFoodStatus(BookingDetails bookingDetails) {
		try {
			// Get employee details from booking details
			Employee employee = bookingDetails.getUserDeatils().getEmployee();

			// Determine old and new food status
			String oldFoodStatus, newFoodStatus;
			if (bookingDetails.isFoodStatus()) {
				oldFoodStatus = "Not opted";
				newFoodStatus = "Opted";
			} else {
				newFoodStatus = "Not opted";
				oldFoodStatus = "Opted";
			}

			// Construct email message
			String subject = "Booked Seat Food Status Update";
			String body = "Dear " + employee.getEmployeeName() + ",\n\n"
					+ "Your seat booking details food status has been updated for Date: " + bookingDetails.getDate()
					+ "Seat Details:\r\n" + "Booking Id: " + bookingDetails.getBookingId() + "\r\n"
					+ "Food Status from: " + oldFoodStatus + "\r\n" + "to: " + newFoodStatus + "\r\n"
					+ "\r\nBest regards,\r\n" + "Admin\r\nSecure Seat Booking";

			// Send email
			sendMail.sendMail(employee.getEmployeeEmail(), subject, body);

			// Log success message
			logger.info("Food status updated successfully for booking ID " + bookingDetails.getBookingId());
		} catch (Exception e) {
			// Log error message and throw exception
			logger.error("Failed to update food status for booking ID " + bookingDetails.getBookingId() + ": "
					+ e.getMessage());
			throw new RuntimeException("Failed to update food status", e);
		}
	}

	@Override
	public void updateBookingDetails(BookingDetails oldBookingDetails, BookingDetails newBookingDetails) {
		// Get employee details from old booking
		Employee emp1 = oldBookingDetails.getUserDeatils().getEmployee();

		// Set email subject
		String subject = "Booked Seat Cancelled";

		// Set old and new food status strings for email body
		String oldFS, newFS;
		if (newBookingDetails.isFoodStatus()) {
			oldFS = "Not opted";
			newFS = "Opted";
		} else {
			newFS = "Not opted";
			oldFS = "Opted";
		}

		// Construct email body
		String body = "Dear " + emp1.getEmployeeName() + ",\n\n"
				+ "Your seat booking details has been updated for the Date: " + oldBookingDetails.getDate()
				+ "Seat Details:\r\n" + "Booking Id: " + oldBookingDetails.getBookingId() + "\r\n" + "from  "
				+ "Seat Number: " + oldBookingDetails.getSeatNo() + "\r\n" + "to \n " + "New Seat Number: "
				+ newBookingDetails.getSeatNo() + "\r\n" + "And Food Status from: " + oldFS + "\r\n" + "to  " + newFS
				+ "\r\n" + "\r\n" + "Best regards,\r\n" + "\r\n" + "Admin\r\n" + "Secure Seat Booking";

		try {
			// Send email to employee
			sendMail.sendMail(emp1.getEmployeeEmail(), subject, body);

			// Log successful email sent
			logger.info("Email sent successfully to " + emp1.getEmployeeName() + " regarding booking update");
		} catch (Exception e) {
			// Log error if email fails to send
			logger.error("Error sending email to " + emp1.getEmployeeName() + " regarding booking update: "
					+ e.getMessage());
		}
	}

}
