package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.entity.UserDeatils;


@Service
public class MailTemplatesImpl implements MailTemplates {
	
//	@Autowired
//	private EmployeeRepo employeeRepo;
	
	@Autowired
	private SendMail sendMail;
	
//	@Autowired
//	private UserDetailsRepo userDetailsRepo;
	
	@Override
	public void registrationMail(UserDeatils userInfo,String pass) {
		Employee emp=userInfo.getEmployee();
		String subject="Secure Seat Booking Registed";
		String body= "Hello "+emp.getEmployeeName()+", \n\n  Welcome To Secure Seat Booking(SSB) Valtech \n\n"
				+ "Below are your login credentials:\r\n"
				+ "Username: "+emp.getEmployeeEmail()+ "\n Password : "+pass+
				"\n Please keep these credentials safe and secure. We recommend that you change your password periodically to ensure the security of your account.\r\n"
				+ "\r\n"
				+ "If you have any questions or concerns, please do not hesitate to contact our customer support team. We are always here to assist you in any way we can."
				+"\n\nBest regards,"
				+"\n\nAdmin"
				+"\nSecure Seat Booking";
		sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
		
	}
	
	@Override
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

	@Override
	public void passwordChangeMail(UserDeatils userInfo) {
		
		Employee emp=userInfo.getEmployee();
		String subject="Password Change succesful";
		String body="Dear "+emp.getEmployeeName()+",\r\n"
				+ "\r\n"
				+ "We are writing to confirm that your password has been successfully changed. Your account security is important to us, and we appreciate your effort to maintain it.\r\n"
				+ "\r\n"
				+ "If you did not make this change, please contact us immediately to ensure the security of your account.\r\n"
				+ "\r\n"
				+ "Thank you for your cooperation in keeping your account secure. If you have any questions or concerns, please do not hesitate to contact our customer support team.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "\r\n"
				+ "Admin\r\n"
				+ "Secure Seat Booking";
		sendMail.sendMail(emp.getEmployeeEmail(), subject, body);
		
	}
	
	@Override
	public void addedFloorTemplete(FloorDetails floorDetails,String adminEmail) {
		String subject="New Floor added to building";
		String body="Dear Admin,\r\n"
				+ "\r\n"
				+ "We are pleased to inform you that we have successfully added a new floor to our office building"+floorDetails.getFloorName()
				+ ". The new floor is now ready for use with "+floorDetails.getNoOfSeats()+" number of seats and we have already completed all necessary installations and inspections.\r\n"
				+ "\r\n"
				+ "We would like to take this opportunity to thank you for your support and cooperation throughout this process. We appreciate your patience and understanding as we worked to expand our facilities.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "\r\n"
				+ "Develpor\r\n"
				+ "Secure Seat Booking";
		sendMail.sendMail(adminEmail, subject, body);
	}
	
	
	
//	 public void sendEmailWithAttachment(String toEmail, String subject, String body, MultipartFile attachment) throws MessagingException, IOException {
//
//	        MimeMessage message = javaMailSender.createMimeMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//	        helper.setTo(toEmail);
//	        helper.setSubject(subject);
//	        helper.setText(body);
//
//	        InputStreamSource attachmentSource = new ByteArrayResource(attachment.getBytes());
//	        helper.addAttachment(attachment.getOriginalFilename(), attachmentSource);
//
////	        javaMailSender.send(message);
//	    }
	
}
