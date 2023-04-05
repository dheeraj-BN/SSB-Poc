package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;


@Service
public class SmsTemplateImpl implements SmsTemplate {
	
	@Autowired
	private SendSMS sendSMS;
	
	
	@Override
	public void sentOTP(Employee employee ,String otp) {
		
		String message ="Hello "+employee.getEmployeeName()+",\r\n"
				+ "\r\n"
				+ "Your OTP for resetting your password is "+otp+". Please enter this code in the password reset page to complete the process.\r\n"
				+ "\r\n"
				+ "Thank you,\r\n"
				+ "Secure Seat Booking";
		
		sendSMS.SendSms(employee, message);
		
	}
	
	@Override
	public void SuccessfulSeatBooking(Employee employee,BookingDetails bookingDetails,String Bookingtype) {
		
		String message ="Hi "+employee.getEmployeeName()+"!\r\n"
				+ "\r\n"
				+ "Congratulations, your seat booking at Valtech Company has been successfully completed. Here are your booking details:\r\n"
				+ "\r\n"
				+ "Seat Number: "+bookingDetails.getSeatNo()+"\r\n"
				+ "Building: JP nagar\r\n"
				+ "\r\n"
				+ "Your food booking has also been confirmed. Please find the details below:\r\n"
				+ "\r\n"
				+ "Meal Type: "+bookingDetails.isFoodStatus()+"\r\n"
				+ "Date: "+bookingDetails.getDate()+"\r\n"
				+ "Time: "+bookingDetails.getBookedTimings()+"\r\n"
				+ "Booking Type: "+Bookingtype+"\r\n"
				+ "\r\n"
				+ "Thank you for using our seat booking application. If you have any questions or concerns, please feel free to reach out to us.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "Valtech Company";
		sendSMS.SendSms(employee, message);
		
	}
	
	
	@Override
	public void CanceledBooking(Employee employee ,BookingDetails bookingDetails) {
		
		String message ="Dear "+employee.getEmployeeName()+",\r\n"
				+ "\r\n"
				+ "We regret to inform you that your seat booking has been cancelled as the time limit for scanning your workspace has exceeded. Our records show that your workspace was not scanned within the allotted time.\r\n"
				+ "\r\n"
				+ "Seat Number: "+bookingDetails.getSeatNo()+"\r\n"
				+ "Date: "+bookingDetails.getDate()+"\r\n"
				+ "We apologize for any inconvenience caused and encourage you to make a new seat booking request through the Valtech Seat Booking Application for your next visit.\r\n"
				+ "\r\n"
				+ "Thank you for your understanding.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "Valtech Seat Booking Team.";
		sendSMS.SendSms(employee, message);
		
	}

}
