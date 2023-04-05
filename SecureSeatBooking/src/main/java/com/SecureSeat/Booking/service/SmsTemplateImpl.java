package com.SecureSeat.Booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;

@Service
public class SmsTemplateImpl implements SmsTemplate {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsTemplateImpl.class);

	@Autowired
	private SendSMS sendSMS;

	/**
	 * 
	 * This method sends an OTP to the given employee's phone number for resetting
	 * their password. It composes a message with the OTP and sends it using the
	 * SendSms method of the sendSMS object.
	 * 
	 * @param employee The Employee object containing the information of the
	 *                 employee to whom the OTP needs to be sent.
	 * @param otp      The OTP to be sent to the employee.
	 */
	@Override
	public void sentOTP(Employee employee, String otp) {
		// Compose the message with the OTP and the employee's name
		String message = "Hello " + employee.getEmployeeName() + ",\r\n" + "\r\n"
				+ "Your OTP for resetting your password is " + otp
				+ ". Please enter this code in the password reset page to complete the process.\r\n" + "\r\n"
				+ "Thank you,\r\n" + "Secure Seat Booking";
		// Send the message to the employee using the sendSMS object's SendSms method
		LOGGER.info("Sending OTP to the employee with name {} using SendSms method of sendSMS object.",
				employee.getEmployeeName());
		sendSMS.SendSms(employee, message);
	}

	/**
	 * 
	 * This method sends a confirmation message to the given employee's phone number
	 * for the successful seat booking. It composes a message with the booking
	 * details and sends it using the SendSms method of the sendSMS object.
	 * 
	 * @param employee       The Employee object containing the information of the
	 *                       employee who made the booking.
	 * @param bookingDetails The BookingDetails object containing the information of
	 *                       the booking made by the employee.
	 * @param Bookingtype    The type of booking made by the employee.
	 */
	@Override
	public void SuccessfulSeatBooking(Employee employee, BookingDetails bookingDetails, String Bookingtype) {
		// Compose the message with the booking details and the employee's name
		String message = "Hi " + employee.getEmployeeName() + "!\r\n" + "\r\n"
				+ "Congratulations, your seat booking at Valtech Company has been successfully completed. Here are your booking details:\r\n"
				+ "\r\n" + "Seat Number: " + bookingDetails.getSeatNo() + "\r\n" + "Building: JP nagar\r\n" + "\r\n"
				+ "Your food booking has also been confirmed. Please find the details below:\r\n" + "\r\n"
				+ "Meal Type: " + bookingDetails.isFoodStatus() + "\r\n" + "Date: " + bookingDetails.getDate() + "\r\n"
				+ "Time: " + bookingDetails.getBookedTimings() + "\r\n" + "Booking Type: " + Bookingtype + "\r\n"
				+ "\r\n"
				+ "Thank you for using our seat booking application. If you have any questions or concerns, please feel free to reach out to us.\r\n"
				+ "\r\n" + "Best regards,\r\n" + "Valtech Company";
		// Send the message to the employee using the sendSMS object's SendSms method
		sendSMS.SendSms(employee, message);
		LOGGER.info("Success notification message sent to employee: " + employee.getEmployeeName());
	}

	/**
	 * 
	 * This method is used to send an SMS notification to an employee when their
	 * seat booking is cancelled due to the time limit for scanning their workspace
	 * being exceeded. It constructs an SMS message containing the necessary details
	 * and sends it using the sendSMS object.
	 * 
	 * @param employee       The employee who made the seat booking.
	 * @param bookingDetails The details of the cancelled seat booking.
	 */
	@Override
	public void CanceledBooking(Employee employee, BookingDetails bookingDetails) {
		// Construct the message to be sent to the employee containing the details of
		// the cancelled seat booking
		String message = "Dear " + employee.getEmployeeName() + ",\r\n" + "\r\n"
				+ "We regret to inform you that your seat booking has been cancelled as the time limit for scanning your workspace has exceeded. Our records show that your workspace was not scanned within the allotted time.\r\n"
				+ "\r\n" + "Seat Number: " + bookingDetails.getSeatNo() + "\r\n" + "Date: " + bookingDetails.getDate()
				+ "\r\n"
				+ "We apologize for any inconvenience caused and encourage you to make a new seat booking request through the Valtech Seat Booking Application for your next visit.\r\n"
				+ "\r\n" + "Thank you for your understanding.\r\n" + "\r\n" + "Best regards,\r\n"
				+ "Valtech Seat Booking Team.";
		// Send the SMS message to the employee using the sendSMS object
		LOGGER.info("Sending cancellation message to the employee with name {} using SendSms method of sendSMS object.",
				employee.getEmployeeName());
		sendSMS.SendSms(employee, message);
	}

}
