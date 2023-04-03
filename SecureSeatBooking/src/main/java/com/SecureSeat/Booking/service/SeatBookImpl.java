package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.dao.SeatBookDAO;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.HolidayDetailsRepo;
import com.SecureSeat.Booking.repo.ShiftDetailsRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class SeatBookImpl implements SeatBook {

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private HolidayDetailsRepo holidayDetailsRepo;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	@Autowired
	private ShiftDetailsRepo shiftDetailsRepo;
	
	@Autowired
	private SeatBookDAO seatBookDAO;

	@Override
	public String savebookeddetails(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
		String message;
		if (from.equals(to)) {
			message = saveBookedDetailsforday(bookingDetails, from);
			return message;
		} else {
			message = seatbookingforweek(bookingDetails, from, to);
			return message;

		}
	}

	@Override
	public String saveBookedDetailsforday(BookingDetails bookingDetails, LocalDate from) {
		int flag = 0;
		int checkholiday = checkholiday(from);
		System.out.println(checkholiday);
		if (checkholiday == 0) {
			int checkuser = checkbookingdetails(from, bookingDetails);
			if (checkuser == 0) {
				LocalDate d1 = from;
				bookingDetails.setDate(from);
				bookingDetails.setBookedTimings(LocalTime.now());
				String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
				UserDeatils user = bookingDetails.getUserDeatils();
				Employee employee = user.getEmployee();
				int employeeid = employee.getEmployeeId();
				String tokenvalue = date1 + employeeid;
				bookingDetails.setToken(tokenvalue);
				bookingDetails.setBookingStatus("PENDING");
				bookingDetailsRepo.save(bookingDetails);
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
        int flag=0;
		LocalDate to1 = to.plusDays(1);
		for (LocalDate i = from; i.isBefore(to1); i = i.plusDays(1)) {
			int checkweekends = checkweekends(i);
			if(checkweekends==1) {
			int checkholiday = checkholiday(i);
			if (checkholiday == 0) {
				int checkuser = checkbookingdetails(i, bookingDetails);
				if (checkuser == 0) {
					LocalDate bookeddate = i;
					bookingDetails.setDate(i);
					bookingDetails.setBookedTimings(LocalTime.now());
					String date1 = bookeddate.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
					UserDeatils user = bookingDetails.getUserDeatils();
					Employee employee = user.getEmployee();
					int employeeid = employee.getEmployeeId();
					String tokenvalue = date1 + employeeid;
					bookingDetails.setToken(tokenvalue);

					BookingDetails book = new BookingDetails(bookingDetails.getSeatNo(), bookingDetails.isFoodStatus(),
							bookeddate, LocalTime.now(), null, "PENDING", tokenvalue, user,
							bookingDetails.getShiftDetails());
					bookingDetailsRepo.save(book);
                    flag=1;
				}

			}

		}
		}
		
		if(flag==1) {
			return "seat has been booked for week";
		}
		else {
			return "seat has been already booked for that week ";
		}

		

	}

	@Override
	public int checkholiday(LocalDate date) {
		int flag = 0;

		List<HolidayDetails> holidayDetails = holidayDetailsRepo.findAll();
		if (holidayDetails.isEmpty()) {
			return 0;
		} else {
			for (HolidayDetails holi : holidayDetails) {

				if ((date.equals(holi.getHolidayDate()))) {
					flag = 1;
				}
			}
			if (flag == 0) {
				return 0;
			} else {
				return 1;
			}
		}

	}
	
	@Override
	public int checkweekends(LocalDate date) {
		int flag=0;
		String dayofweek = date.getDayOfWeek().toString();
		if("SATURDAY".equalsIgnoreCase(dayofweek) || "SUNDAY".equalsIgnoreCase(dayofweek)) {
			return 0;
		}
		else {
			return 1;
		}
		
	}

	@Override
	public int checkbookingdetails(LocalDate date, BookingDetails bookingDetails1) {
		int flag = 0;

		List<BookingDetails> bookingDetails = bookingDetailsRepo.findAllByUserDeatils(bookingDetails1.getUserDeatils());
		System.out.println(bookingDetails);
		for (BookingDetails book2 : bookingDetails) {
			if ((date.equals(book2.getDate()))
					&& (bookingDetails1.getUserDeatils().getUserId() == book2.getUserDeatils().getUserId())) {
				flag = 1;

			}
		}
		if (flag == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public String checkseatalreadybooked(String seatNo, LocalDate date1) {
		int flag = 0;
		List<BookingDetails> bookDetails = bookingDetailsRepo.findAll();
		for (BookingDetails book : bookDetails) {
			if (seatNo.equals(book.getSeatNo()) && date1.equals(book.getDate())) {
				flag = 1;
			}
		}
		if (flag == 1) {
			return "seat Booked";
		} else {
			return "seat not booked";
		}

	}
	
	@Override
	public List<String> getSeatNoByDate(LocalDate bookeddate){
		List<String> seatNos=seatBookDAO.getseatNoByBookedDate(bookeddate);
		return seatNos;
	}
	
	@Override
	public List<BookingDetails> getbookingdetails(LocalDate bookeddate){
//		List<BookingDetails> bookingdetails = bookingDetailsRepo.findByBookedDate(bookeddate);
	List<BookingDetails> bookingdetails = seatBookDAO.getbookingdetailsbydate(bookeddate);
		return bookingdetails;
	}
	
	@Override
	public UserDeatils getuserbyid(int id) {
		System.out.println(userDetailsRepo.getById(id));
		return  userDetailsRepo.getById(id);
	}
	
	@Override
	public ShiftDetails getshiftdetails(int id) {
		return shiftDetailsRepo.getById(id);
	}
	
	@Override
	public void updatecanceledetails(String token) {
		int booking_id=seatBookDAO.getbookingidfromtoken(token);
		seatBookDAO.updatebookingstatus(booking_id);
		
	}
	
	@Override
	public void updateseatno(String token,String seatno) {
		int bookingid=seatBookDAO.getbookingidfromtoken(token);
		seatBookDAO.updateseatNo(seatno, bookingid);
	}
	@Override
	public void updatefoodstatus(String token,Boolean foodstatus) {
		int bookingid=seatBookDAO.getbookingidfromtoken(token);
		seatBookDAO.updatefoodstatus(foodstatus, bookingid);
	}
	
	@Override
	public void updateseatbooking(String token,Boolean foodstatus,String seatno) {
		int bookingid=seatBookDAO.getbookingidfromtoken(token);
		seatBookDAO.updateseatbooking(foodstatus, seatno, bookingid);
	}
	
	@Override
	@Scheduled(cron = "0 0 10 * * ?") 
	public void updatecancelforschedule(){
		LocalDate date = LocalDate.now();
		List<BookingDetails> bookingDetails = seatBookDAO.getbookingdetailsbydateandbookingstatus(date);
		for(BookingDetails book: bookingDetails) {
			String shift_timings=book.getShiftDetails().getShiftTimings();
 	     String shift=shift_timings.substring(0,2);
		    int i = Integer.parseInt(shift);
		    LocalTime time = LocalTime.now();
		    int hour = time.getHour();
		    int j=i+5;
		    System.out.println(j);
		    if(j>=24) {
		    	j=j-24;
		    }
		    if(j>=hour) {
		    	seatBookDAO.updatebookingstatus(book.getBookingId());
		    }
		}
	}

}
