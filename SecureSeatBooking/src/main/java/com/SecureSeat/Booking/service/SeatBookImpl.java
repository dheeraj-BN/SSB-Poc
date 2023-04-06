package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.Session;

import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Autowired
	private MailTemplates mailTemplates;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private SmsTemplate smsTemplate;

	private static final Logger logger = LoggerFactory.getLogger(SeatBookImpl.class);

//	If user books seat for a day ,from date and to date will be equal ,it call the function saveBookedDetailsforday.
//  If user books seat for a week , from date and to date will be different , it call the function seatbookingforweek.
	@Override
	public String savebookeddetails(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
		logger.info("Request to save booking details for {} to {}", from, to);
		String message;
		if (from.equals(to)) {
			message = saveBookedDetailsforday(bookingDetails, from);
			logger.info("Saved booking details for {}", from);
			return message;
		} else {
			message = seatbookingforweek(bookingDetails, from, to);
			logger.info("Saved booking details for {} to {}", from, to);
			return message;

		}
	}

// If user books seat for a day,
// First it check whether the day is holiday or not , if it is holiday then seat will not be booked for that date.
// If the day is not holiday ,Then it check whether the user booked the seat on that date .
// If the user already booked the seat for that date , seat will not book.
// If user not booked seat on that day earlier , then seat will be booked on that day.
	@Override
	public String saveBookedDetailsforday(BookingDetails bookingDetails, LocalDate from) {
		logger.info("seat booking details for  date {}", from);
		int flag = 0;
		logger.info("checking holiday for date { }", from);
		int checkholiday = checkholiday(from);
		if (checkholiday == 0) {
			logger.info("checking if user already booked seat for date{}", from);
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
				logger.info("seat has been booked for user {} for date {}", employee, from);
				smsTemplate.SuccessfulSeatBooking(employee, bookingDetails, tokenvalue);
				return "Seat has been booked for today";

			} else {
				logger.info("user {} has already booked seat for day {}", bookingDetails.getUserDeatils().getEmployee(),
						from);
				return "Seat has already  booked for today";

			}
		}

		else {
			logger.info("{} is holiday user {} can't able to book seat", from,
					bookingDetails.getUserDeatils().getEmployee());
			return "That is holiday you can't able to book";
		}

	}

//		If user books seat weekly
//  From date and to date will be different , Seat will be booked for all the days between from date and to date including from and to date .
// For every dates from from date to to date holiday will be checked.If any of dates have holiday then seat will be not booked for that date.
// For every dates , check whether user had already booked seat for any of the dates , If it so seat will not book for those days
//If user not booked seat, then seat will be booked for all those days.
	@Override
	public String seatbookingforweek(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
		logger.info("seat booking details for date{} to {}", from, to);
		int flag = 0;
		String tokenvalue = null;
		LocalDate to1 = to.plusDays(1);
		for (LocalDate i = from; i.isBefore(to1); i = i.plusDays(1)) {
			logger.info("checking weekends for date { }", i);
			int checkweekends = checkweekends(i);
			if (checkweekends == 1) {
				logger.info("checking holiday for date { }", i);
				int checkholiday = checkholiday(i);
				if (checkholiday == 0) {
					logger.info("checking if user already booked seat for date{}", i);
					int checkuser = checkbookingdetails(i, bookingDetails);
					if (checkuser == 0) {
						LocalDate bookeddate = i;
						bookingDetails.setDate(i);
						bookingDetails.setBookedTimings(LocalTime.now());
						String date1 = bookeddate.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
						UserDeatils user = bookingDetails.getUserDeatils();
						Employee employee = user.getEmployee();
						int employeeid = employee.getEmployeeId();
						tokenvalue = date1 + employeeid;
						bookingDetails.setToken(tokenvalue);

						BookingDetails book = new BookingDetails(bookingDetails.getSeatNo(),
								bookingDetails.isFoodStatus(), bookeddate, LocalTime.now(), null, "PENDING", tokenvalue,
								user, bookingDetails.getShiftDetails());
						bookingDetailsRepo.save(book);
						flag = 1;
					}

				}

			}
		}

		if (flag == 1) {
			logger.info("seat has been booked for week");
			smsTemplate.SuccessfulSeatBooking(bookingDetails.getUserDeatils().getEmployee(), bookingDetails,tokenvalue);
			return "seat has been booked for week";
		} else {
			logger.info("seat has been already booked for that week");
			return "seat has been already booked for that week ";
		}

	}

//  Check the given date is holiday or not 
// If the date is holiday ,return 0.
// If the date is holiday , return 1.
	@Override
	public int checkholiday(LocalDate date) {
		logger.info("Checking if {} is a holiday", date);
		int flag = 0;
		logger.info("Fetching holiday details");
		List<HolidayDetails> holidayDetails = holidayDetailsRepo.findAll();
		if (holidayDetails.isEmpty()) {
			logger.info("No holiday details found");
			return 0;
		} else {
			for (HolidayDetails holi : holidayDetails) {

				if ((date.equals(holi.getHolidayDate()))) {
					flag = 1;
				}
			}
			if (flag == 0) {
				logger.info("{} is not a holiday", date);
				return 0;
			} else {
				logger.info("{} is a holiday", date);
				return 1;
			}
		}

	}

// Check whether the user booked the seat for that date
// If the user already booked seat for that date ,return 1
// else return 0;
	@Override
	public int checkweekends(LocalDate date) {
		logger.info("Checking if {} is a saturday or sunday", date);
		int flag = 0;
		String dayofweek = date.getDayOfWeek().toString();
		if ("SATURDAY".equalsIgnoreCase(dayofweek) || "SUNDAY".equalsIgnoreCase(dayofweek)) {
			logger.info("{} is a weekend", date);
			return 0;
		} else {
			logger.info("{} is not a weekend", date);
			return 1;
		}

	}

// Check whether the user booked the seat for that date
// If the user already booked seat for that date ,return 1
// else return 0;
	@Override
	public int checkbookingdetails(LocalDate date, BookingDetails bookingDetails1) {
		logger.info("checking if user {} booked seat for date{} ", bookingDetails1.getUserDeatils(), date);
		int flag = 0;
		logger.info("Fetching seat booking details for user {}", bookingDetails1.getUserDeatils());
		List<BookingDetails> bookingDetails = bookingDetailsRepo.findAllByUserDeatils(bookingDetails1.getUserDeatils());
		for (BookingDetails book2 : bookingDetails) {
			if ((date.equals(book2.getDate()))
					&& (bookingDetails1.getUserDeatils().getUserId() == book2.getUserDeatils().getUserId())) {
				flag = 1;

			}
		}
		if (flag == 1) {
			logger.info("User has already booked seat for date {}", date);
			return 1;
		} else {
			logger.info("User has not booked seat for date {}", date);
			return 0;
		}

	}

// Checking seat already booked for the given date or not
// If the seat for that date is booked return seat booked
// else return seat not booked
	@Override
	public int checkseatalreadybooked(String seatNo, LocalDate date1) {
		logger.info("checking seatno {} is booked for date {}", seatNo, date1);
		BookingDetails booked = bookingDetailsRepo.findByBookedDateAndSeatNo(date1, seatNo);
		if (booked == null) {
			logger.info("seatno {} is not booked for date {}", seatNo, date1);
			return 1;
		}
		logger.info("seatno {} is  booked for date {}", seatNo, date1);
		return 0;

	}

//	Returns a list of seat numbers that have been booked on the given date.
	@Override
	public List<String> getSeatNoByDate(LocalDate bookeddate) {
		logger.info("checking seatno list for date{}", bookeddate);
		List<String> seatNos = seatBookDAO.getseatNoByBookedDate(bookeddate);
		logger.debug("seatnos {} for date {}", seatNos, bookeddate);
		return seatNos;
	}

//	Returns a list of booking details that have been booked on the given date.
	@Override
	public List<BookingDetails> getbookingdetails(LocalDate bookeddate) {
//		List<BookingDetails> bookingdetails = bookingDetailsRepo.findByBookedDate(bookeddate);
		logger.info("Fetching booking details for date {}", bookeddate);
		List<BookingDetails> bookingdetails = seatBookDAO.getbookingdetailsbydate(bookeddate);
		return bookingdetails;
	}

//	Fetching user details from user id
	@Override
	public UserDeatils getuserbyid(int id) {
		logger.info("fetching userdetails for id {}", id);
		return userDetailsRepo.findById(id).get();
	}
	
//	Fetching user details	
	@Override
	public List<UserDeatils> getuserdetails(){
		return userDetailsRepo.findAll();
	}

//	Fetching shift details from shift id
	@Override
	public ShiftDetails getshiftdetails(int id) {
		logger.info("fetching shiftdetails for id {}", id);
		return shiftDetailsRepo.findById(id).get();
	}
	
//	Fetching shift details 
	@Override
	public List<ShiftDetails> getshift() {
		return shiftDetailsRepo.findAll();
	}

//	Fetch booking id from token value
// Update booking status as cancel for that booking id
	@Override
	public void updatecanceledetails(String token) {
		logger.info("Fetching bookingdetails by token {}", token);
		int booking_id = seatBookDAO.getbookingidfromtoken(token);
		logger.info("Updating booking status has cancel");
		seatBookDAO.updatebookingstatus(booking_id);
		BookingDetails bookingDetails = bookingDetailsRepo.findById(booking_id).get();
		mailTemplates.cancledYourBooking(bookingDetails);

	}

//	Fetch booking id from token value
// Update new seat no for that booking id 	
	@Override
	public void updateseatno(String token, String seatno) {
		logger.info("Fetching bookingdetails by token {}", token);
		int bookingid = seatBookDAO.getbookingidfromtoken(token);
		logger.info("Updating seatno {} for bookingId{}", seatno, bookingid);
		seatBookDAO.updateseatNo(seatno, bookingid);
	}

//	Fetch booking id from token value
// Update new food status for that booking id 	
	@Override
	public void updatefoodstatus(String token, Boolean foodstatus) {
		logger.info("Fetching bookingdetails by token {}", token);
		int bookingid = seatBookDAO.getbookingidfromtoken(token);
		logger.info("Updating foodstatus {} for bookingId{}", foodstatus, bookingid);
		seatBookDAO.updatefoodstatus(foodstatus, bookingid);
	}

//	Fetch booking id from token value
// Update both seat no and food status for that booking id 	
	@Override
	public void updateseatbooking(String token, Boolean foodstatus, String seatno) {
		logger.info("Fetching bookingdetails by token {}", token);
		int bookingid = seatBookDAO.getbookingidfromtoken(token);
		BookingDetails oldbookingdetail = bookingDetailsRepo.findById(bookingid).get();
		BookingDetails oldBookingDetails1 = new BookingDetails();
		oldBookingDetails1.setBookingId(oldbookingdetail.getBookingId());
		oldBookingDetails1.setBookedTimings(oldbookingdetail.getBookedTimings());
		oldBookingDetails1.setBookingStatus(oldbookingdetail.getBookingStatus());
		oldBookingDetails1.setDate(oldbookingdetail.getDate());
		oldBookingDetails1.setFoodStatus(oldbookingdetail.isFoodStatus());
		oldBookingDetails1.setLoginTime(oldbookingdetail.getLoginTime());
		oldBookingDetails1.setSeatNo(oldbookingdetail.getSeatNo());
		oldBookingDetails1.setShiftDetails(oldbookingdetail.getShiftDetails());
		oldBookingDetails1.setToken(oldbookingdetail.getToken());
		oldBookingDetails1.setUserDeatils(oldbookingdetail.getUserDeatils());
		logger.info("Updating foodstatus and phone number for bookingid{}", bookingid);
		seatBookDAO.updateseatbooking(foodstatus, seatno, bookingid);
		BookingDetails newbookingdetail = bookingDetailsRepo.findById(bookingid).get();
		newbookingdetail.setSeatNo(seatno);
		newbookingdetail.setFoodStatus(foodstatus);
		mailService.updateBookingDetails(oldBookingDetails1, newbookingdetail);

	}

//	Schedule cancel 
// If user booking status is pending 
// Check starting time of the shift for booking details 
// If shift time exceeds some time then booking status will update as cancel
	@Override
	@Scheduled(cron = "0 0 * * * ?")
	public void updatecancelforschedule() {
		logger.info("Scheduling cancel updation for the seat");
		LocalDate date = LocalDate.now();
		List<BookingDetails> bookingDetails = seatBookDAO.getbookingdetailsbydateandbookingstatus(date);
		for (BookingDetails book : bookingDetails) {
			String shift_timings = book.getShiftDetails().getShiftTimings();
// 	     String shift=shift_timings.substring(0,2);
			String[] n = shift_timings.split(":");
			int i = Integer.parseInt(n[0]);
			LocalTime time = LocalTime.now();
			int hour = time.getHour();
			int j = i + 5;
			if (j >= 24) {
				j = j - 24;
			}
			if (j >= hour) {
				seatBookDAO.updatebookingstatus(book.getBookingId());
				smsTemplate.CanceledBooking(book.getUserDeatils().getEmployee(), book);
				mailTemplates.dailyBookedSeatCancled(book);
			}
		}
	}

// Fetching latest seat booking details for the particular user	
	@Override
	public BookingDetails getlatestbookingdetailsofid(int id) {
		logger.info("Fetchinglatestbookingdetails for id {}", id);
		return seatBookDAO.getlatestbookingdetailsofid(id);
	}

// Seat booking 
// By fetching seat no and food status for latest seat booking details of the user
//	Book seat for from and to dates
	@Override
	public String checkseatfordate(BookingDetails bookingDetails, LocalDate from, LocalDate to) {
		logger.info("checking bookingdetails for from {} to {}", from, to);
		LocalDate to1 = to.plusDays(1);
		for (LocalDate i = from; i.isBefore(to1); i = i.plusDays(1)) {
			int checkseatstatus = checkseatalreadybooked(bookingDetails.getSeatNo(), i);
			if (checkseatstatus == 1) {
				BookingDetails book = new BookingDetails();
				book.setSeatNo(bookingDetails.getSeatNo());
				book.setFoodStatus(bookingDetails.isFoodStatus());
				book.setShiftDetails(bookingDetails.getShiftDetails());
				book.setUserDeatils(bookingDetails.getUserDeatils());
				saveBookedDetailsforday(book, i);
			} else {
				return "seat has already booked for " + i;
			}
		}

		return "seat booked";
	}

}
