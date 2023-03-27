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
	public String saveBookedDetails(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
		int flag = 0;
		int checkseat=checkseatalreadybooked(bookingDetails);
		
		if (checkseat == 0) {
				int chechholiday = checkholiday(from);				
				if (chechholiday ==0) {
					int checkuser = checkbookingdetails(from,bookingDetails.getUserDeatils());
					System.out.println(checkuser);
					if (checkuser ==0) {
						LocalDate d1 = from;
						bookingDetails.setDate(from);
						bookingDetails.setBookedTimings(LocalTime.now());
						String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
						System.out.println(date1);
						UserDeatils user = bookingDetails.getUserDeatils();
						Employee employee = user.getEmployee();
						int employeeid = employee.getEmployeeId();
						String tokenvalue = date1 + employeeid;
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
		
		else {
			return "That seat has been already booked for that date";
		}
	}
  @Override
	public String seatbookingforweek(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
	  int checkseat=checkseatalreadybooked(bookingDetails);
	  
			LocalDate to1 = to.plusDays(1);
			for (LocalDate i = from; i.isBefore(to1); i = i.plusDays(1)) {
				int checkholiday = checkholiday(i);
				if (checkholiday==0) {
					int checkuser = checkbookingdetails(i,bookingDetails.getUserDeatils());
					System.out.println(checkuser);
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
	
	public int checkholiday(LocalDate date) {
		int flag =0;
		try {
			List<HolidayDetails> holidayDetails = holidayDetailsRepo.findAll();
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
		catch (Exception e) {
			return 0;
		}
	}
	
	public int checkbookingdetails(LocalDate date,UserDeatils userdetDeatils) {
		int flag=0;
		try {
			List<BookingDetails> bookingDetails = bookingDetailsRepo.findAllByUserDeatils(userdetDeatils);
			System.out.println(bookingDetails);
			for (BookingDetails book2 : bookingDetails) {
            
				if (date.equals(book2.getDate())){
					if(userdetDeatils.equals(book2.getUserDeatils())) {
					flag = 1;
				}
					
				}
			}
			if(flag==0) {
				return 1;
			}
			else {
				return 0;
			}
			
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	public int checkseatalreadybooked(BookingDetails bookingDetails) {
		int flag=0;
		try {
			List<BookingDetails> bookDetails = bookingDetailsRepo.findAll();
			for (BookingDetails book: bookDetails) {
				if(bookingDetails.getSeatNo().equals(book.getSeatNo()) && bookingDetails.getDate().equals(book.getDate())) {
					flag = 1;
				}
			}
			if(flag==0) {
				return 0;
			}
			else {
				return 1;
			}
			
		}catch (Exception e) {
			return 0;
		}
		
	}

}
