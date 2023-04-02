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
	
	
	
//	 @Override
//	@Scheduled(cron = "0 0 10 * * ?") // runs every day at 12:00 PM  //0 0 10 * * ?
////	 @Scheduled(cron = "0 * * * * ?")// for evey min
//	 public void schecludedMailForDailyBooking() {
//		 
//		 LocalDate date1=LocalDate.now();//need to change after ching format
////		 List<BookingDetails> bookingDL=bookingDetailsRepo.findAllByLoginTimeIsNullAndBookedDateEquals(date1);
//		 List<BookingDetails> bookingDL=bookingDetailsRepo.findAllByBookingStatusAndBookedDateEquals("PENDING",date1);
//		 for (BookingDetails bookingDetails : bookingDL) {
//			mailTemplates.dailyBookedSeatReminder(bookingDetails);
//		}
//		 
//		 
//		 
//	 }
	
	
	@Override
	@Scheduled(cron = "0 0 10 * * ?") // runs every day at 10:00 AM
	public void schecludedMailForDailyBooking() {
	    LocalDate currentDate = LocalDate.now();
	    logger.info("Started sending daily booking reminder emails for {}", currentDate);
	    List<BookingDetails> bookingDL = bookingDetailsRepo.findAllByBookingStatusAndBookedDateEquals("PENDING", currentDate);
//	    for (BookingDetails bookingDetails : bookingDL) {
//	        mailTemplates.dailyBookedSeatReminder(bookingDetails);
//	    }
//	    logger.info("Completed sending daily booking reminder emails for {}", currentDate);
	    
	    try {
	        for (BookingDetails bookingDetails : bookingDL) {
	            mailTemplates.dailyBookedSeatReminder(bookingDetails);
	            logger.info("Completed sending daily booking reminder emails for {}", currentDate);
	        }
	    } catch (Exception e) {
	        logger.error("Error occurred while sending daily booking reminder emails: {}", e.getMessage());
	        e.printStackTrace();
	    }

	    
	    
	    
	    
	}

	
	
	
	 
//	 @Override
//	 public String passwordChangeConfrimMail(int id) {
//		 
//		UserDeatils usr=userDetailsRepo.findByUserId(id).get();
//		mailTemplates.passwordChangeMail(usr);
//	 
//		return "SUCCESS";
//		 
//	 }
	 
	 
	 @Override
	 public String passwordChangeConfrimMail(int id) {
	     UserDeatils usr = userDetailsRepo.findByUserId(id).orElse(null);
	     if (usr == null) {
	         logger.warn("User details not found for user ID {}", id);
	         return "FAILURE";
	     }
	     mailTemplates.passwordChangeMail(usr);
	     logger.info("Password change confirmation mail sent to user {}", usr.getEmployee().getEmployeeEmail());
	     return "SUCCESS";
	 }

	 
	 
	 
//	 @Override
//	 public String addedFloor(FloorDetails floorDetails) {
//		Employee emp= userDetailDao.getAdminInfo();
//		mailTemplates.addedFloorTemplete(floorDetails, emp.getEmployeePersonalEmail());
//		return "SUCCESS";
//	 }
	 
	 
	 @Override
	 public String addedFloor(FloorDetails floorDetails) {
	     Employee emp = userDetailDao.getAdminInfo();
	     String adminEmail = emp.getEmployeePersonalEmail();
	     logger.info("Sending added floor notification email to admin at {}", adminEmail);
	     mailTemplates.addedFloorTemplete(floorDetails, adminEmail);
	     return "SUCCESS";
	 }

	 

}
