package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.HolidayDetailsRepo;

@Service
public class SeatBookImpl implements SeatBook {

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private HolidayDetailsRepo holidayDetailsRepo;
	
	@Override
	public String savebookeddetails(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
		String message;
		if(from.equals(to)) {
			 message = saveBookedDetailsforday(bookingDetails, from, to);
					return message;
		}
		else {
		 message = seatbookingforweek(bookingDetails, from, to);
					return message;
			
		}
	}

	@Override
	public String saveBookedDetailsforday(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
		int flag = 0;
				int chechholiday = checkholiday(from);	
				System.out.println(chechholiday);
				if (chechholiday ==0) {
					int checkuser = checkbookingdetails(from,bookingDetails);
					if (checkuser ==0) {
						LocalDate d1 = from;
						bookingDetails.setDate(from);
						bookingDetails.setBookedTimings(LocalTime.now());
						String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
						System.out.println(date1);
						UserDeatils user = bookingDetails.getUserDeatils();
						Employee employee = user.getEmployee();
						int employeeid = employee.getEmployeeId();
						String tokenvalue = date1 +employeeid;
						bookingDetails.setToken(tokenvalue);
						bookingDetails.setBookingStatus("PENDING");
						BookingDetails b = bookingDetailsRepo.save(bookingDetails);
						return "Seat has been booked for today";

					} else {
						
							return "Seat has already  booked for today";
						}
				}
						
					
				else {
					return "That is holiday you can't able to book";
				}
            
			}
		
	

  @Override
	public String seatbookingforweek(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
	  
			LocalDate to1 = to.plusDays(1);
			for (LocalDate i = from; i.isBefore(to1); i = i.plusDays(1)) {
				int checkholiday = checkholiday(i);
				if (checkholiday==0) {
					int checkuser = checkbookingdetails(i,bookingDetails);
					if(checkuser==0) {
					LocalDate d1 = i;
					bookingDetails.setDate(i);
					bookingDetails.setBookedTimings(LocalTime.now());
					String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
					System.out.println(date1);
					UserDeatils user = bookingDetails.getUserDeatils();
					Employee employee = user.getEmployee();
					int employeeid = employee.getEmployeeId();
					String tokenvalue = date1 + employeeid;
					bookingDetails.setToken(tokenvalue);

					BookingDetails book = new BookingDetails(bookingDetails.getSeatNo(), bookingDetails.isFoodStatus(),
							d1, LocalTime.now(), null, "PENDING", tokenvalue, user, bookingDetails.getShiftDetails());
					BookingDetails b = bookingDetailsRepo.save(book);

				}

			}
				
			}
			
			return "seat has been booked for week";

		
	}
	@Override
	public int checkholiday(LocalDate date) {
		int flag =0;
		
			List<HolidayDetails> holidayDetails = holidayDetailsRepo.findAll();
			if(holidayDetails.isEmpty()) {
				return 0;
			}
			else {
			for (HolidayDetails holi : holidayDetails) {

				if ((date.equals(holi.getHolidayDate()))) {
					flag = 1;
				} 
			}
			if(flag==0) {
				return 0;
			}
			else {
				return 1;
			}
			}
		
	}
	@Override
	public int checkbookingdetails(LocalDate date,BookingDetails bookingDetails1) {
		int flag=0;
		
			List<BookingDetails> bookingDetails = bookingDetailsRepo.findAllByUserDeatils(bookingDetails1.getUserDeatils());
			System.out.println(bookingDetails);
			for (BookingDetails book2 : bookingDetails) {
				if ((date.equals(book2.getDate())) &&
					(bookingDetails1.getUserDeatils().getUserId()==book2.getUserDeatils().getUserId())) {
					flag= 1;
					
				}
			}
			if(flag==1 ) {
				return 1;
			}
			else {
				return 0;
			}
			
			
			
		
	}
	
	@Override
	public String checkseatalreadybooked(String seatNo,LocalDate date1) {
		int flag=0;
			List<BookingDetails> bookDetails = bookingDetailsRepo.findAll();
			for (BookingDetails book: bookDetails) {
				if(seatNo.equals(book.getSeatNo()) && date1.equals(book.getDate())) {
					flag = 1;
				}
			}
			if(flag==1) {
				return "seat Booked";
			}
			else {
				return "seat not booked";
			}
			
		
		
	}
	
	

}
