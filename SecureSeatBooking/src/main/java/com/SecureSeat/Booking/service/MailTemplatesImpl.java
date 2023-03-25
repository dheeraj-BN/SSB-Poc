package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
//import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

//import org.springframework.scheduling.annotation.Scheduled;


@Service
public class MailTemplatesImpl implements MailTemplates {
	
//	@Autowired
//	private EmployeeRepo employeeRepo;
	
	@Autowired
	private SendMail sendMail;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	@Override
	public void registrationMail(UserDeatils userInfo) {
		Employee emp=userInfo.getEmployee();
		String subject="Secure Seat Booking Registed";
		String body= "Hello "+emp.getEmployeeName()+", \n\n  Welcome To Secure Seat Booking(SSB) Valtech \n\n"
				+ "Below are your login credentials:\r\n"
				+ "Username: "+emp.getEmployeeEmail()+ "\n Password : "+userInfo.getPassword()+
				"\n Please keep these credentials safe and secure. We recommend that you change your password periodically to ensure the security of your account.\r\n"
				+ "\r\n"
				+ "If you have any questions or concerns, please do not hesitate to contact our customer support team. We are always here to assist you in any way we can."
				+"\n\nBest regards,"
				+"\n\nAdmin"
				+"\nSecure Seat Booking";
		sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
		
	}
	
	
	public void dailyBookedSeatReminder(BookingDetails bookingDetails) {
		
		Employee emp=bookingDetails.getUserDeatils().getEmployee();
		String subject="Secure Seat Booking Scan Remainder";
		String body="Dear "+emp.getEmployeeName()+",\n\n"
				+ "We hope this email finds you well. We would like to inform you that starting from "+bookingDetails.getDate()+", we will be implementing a new system to ensure the safety and well-being of our employees. To access the office premises, all employees will be required to scan their reserved seat QR code upon entry"
				+"Seat Details:\r\n"
				+"Booking Id: "+bookingDetails.getBookingId()+"\r\n"
				+ "Seat Number: "+bookingDetails.getSeatNo()+"\r\n"
				+ "Shift Timmings: "+bookingDetails.getShiftDetails().getShiftTimings()+"\r\n"
				+ "Food Option: "+bookingDetails.isFoodStatus()+"\r\n"
				+"Thank you for your cooperation and understanding as we work to ensure the safety and well-being of our employees.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "\r\n"
				+ "Admin\r\n"
				+ "Secure Seat Booking"	;
		sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
		
	}

	
//	 @Scheduled(cron = "0 0 12 * * ?") // runs every day at 12:00 PM
	
}
