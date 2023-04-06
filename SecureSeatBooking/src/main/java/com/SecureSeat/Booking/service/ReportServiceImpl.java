//package com.SecureSeat.Booking.service;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.Month;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.TemporalAdjusters;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.SecureSeat.Booking.dao.ReportDao;
//import com.SecureSeat.Booking.entity.BookingDetails;
//import com.SecureSeat.Booking.repo.BookingDetailsRepo;
//
//@Service
//public class ReportServiceImpl implements ReportService {
//	
//	@Autowired
//	private ReportDao reportDao;
//	
//	
//	@Autowired
//	private BookingDetailsRepo bookingDetailsRepo;
//
//	
//	
//
//
//	
//	 @Override
//	    public List<BookingDetails> getBookingDetailsByDate(LocalDate bookingDate) {
//	        return reportDao.getBookingDetailsByDate(bookingDate);
//	    }
//	 
//	 
//	 @Override
//	    public List<BookingDetails> getBookingsByDateAndStatus(LocalDate bookingDate, String bookingStatus) {
//	        return reportDao.getBookingsByDateAndStatus(bookingDate,bookingStatus);
//	    }
//	 
//
//	 
//	 
//	 
//	 
//	 
//	   @Override
//	    public List<BookingDetails> findBookingByDateAndStatus(LocalDate date, String status) {
//	        return bookingDetailsRepo.findByBookedDateAndBookingStatus(date, status);
//	    }
//	 
//	 
//	 
//	   @Override
//	    public List<BookingDetails> getBookingByDate(LocalDate bookedDate) {
//	        return bookingDetailsRepo.findByBookedDate(bookedDate);
//	    }
//
//	 
//	 
//	   @Override
//		public List<BookingDetails> getBookingBySpecificMonth(int year, Month month) {
//			LocalDate startDate = LocalDate.of(year, month, 1);
//			LocalDate endDate = startDate.plusMonths(1).minusDays(1);
//			return bookingDetailsRepo.findByBookedDateBetween(startDate, endDate);
//		}
//	 
//  
//	   
//	   
//	   @Override
//	    public List<BookingDetails> getBookingBySpecificWeek(int week) {
//	        LocalDate currentDate = LocalDate.now();
//	        LocalDate startDateOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).plusWeeks(week);
//	        LocalDate endDateOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).plusWeeks(week);
//
//	        List<BookingDetails> bookingDetailsList = bookingDetailsRepo.findByBookedDateBetween(startDateOfWeek, endDateOfWeek);
//
//	        return bookingDetailsList;
//	    }
//
//
////	@Override
////	public List<BookingDetails> getEmployeeMonthlyBookingDetails(int userId, LocalDate month) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//	   
//
//
//	   @Override
//	    public List<BookingDetails> getEmployeeBookingDetails(int userId) {
//	        return bookingDetailsRepo.findByUserDeatilsByUserId(userId);
//	    }
//	    
//
//	   @Override
//	   public List<BookingDetails> getMonthlyBookingDetailsForUser(int userId, int month, int year) {
//	        LocalDate startDate = LocalDate.of(year, month, 1);
//	        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
//	        return bookingDetailsRepo.findByEmployeeDeatilsByUserIdAndBookedDateBetween(userId, startDate, endDate);
//	    }
//	   
//}
