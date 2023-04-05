package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.dao.EmployeeDAO;
import com.SecureSeat.Booking.dao.SeatBookDAO;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.FloorDetailsRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service

public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private SeatBook seatBook;
	
	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private SecurityConfig config;
	
	@Autowired
	private SeatBookDAO seatBookDAO;
	
	@Autowired
	private SendSMS sendSMS;
	
	@Autowired
	private FloorDetailsRepo floorDetailsRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	
	
	@Override
	public BookingDetails getbookingdetailsbyid(int id) {
		return seatBookDAO.getlatestbookingdetailsofid(id);
	}
	
	@Override
	public String savelastbookingdetails(int id,LocalDate from,LocalDate to) {
		BookingDetails bookingDetails = seatBookDAO.getlatestbookingdetailsofid(id);
		System.out.println(bookingDetails);
		BookingDetails book = new BookingDetails();
		book.setSeatNo(bookingDetails.getSeatNo());
		book.setFoodStatus(bookingDetails.isFoodStatus());
		book.setUserDeatils(bookingDetails.getUserDeatils());
		book.setShiftDetails(bookingDetails.getShiftDetails());
		return seatBook.checkseatfordate(book, from, to);
		
	}
	
	
	@Override
	public String forgotPasword(int id,String password) {
			int userid=id;
			employeeDAO.restPassword(config.passwordEncoder().encode(password), userid);		
			return "Password Changed";
		
	}
	

	
	@Override
	public int findUserIdByPhoneNo(String phoneNo) {
		int userid=employeeDAO.forgotPassword(phoneNo);
		return userid;
		
	}
	
	@Override
	public  String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}
	
	@Override
	public String generateOtp() {
		String otp1=getRandomNumberString();
		
		System.out.println(otp1);
		return otp1;
	
	}
	
	
	@Override
	public String changePassword(int id,String oldPassword,String newPassword) {
		logger.info("getting the user_id and user details for change password");
		logger.debug("user_id is" +id);
		UserDeatils user=userDetailsRepo.findByUserId(id).get();
		logger.debug("userInfo" +user);
//		System.out.println(config.passwordEncoder().matches(oldPassword,user.getPassword()));
		if(config.passwordEncoder().matches(oldPassword,user.getPassword())) {
			
			employeeDAO.changePasswor(config.passwordEncoder().encode(newPassword),id);
			return "Password changed";
		}else {
			logger.error("password wrong");
		return "Old Password doesn't matched";
		}
	}
	
	
	@Override
	public BookingDetails getEmpBookedInfo(int id) {
		logger.info("Fetching booking details of employee booked for today");
		UserDeatils user=userDetailsRepo.findByUserId(id).get();
		logger.debug("employee "+user);
		System.out.println(user);
		BookingDetails info=bookingDetailsRepo.findByUserDeatilsAndBookedDateEquals(user, LocalDate.now());
		return info;	
	}
	
	@Override
	public List<BookingDetails> getEmpBookedInfoBookedNext(int id) {
		logger.info("Fetching booking details of employee booked for next days");
		UserDeatils user=userDetailsRepo.findByUserId(id).get();
		logger.debug("employee "+user);
		List<BookingDetails> info=employeeDAO.getEmpBookedInfoBookedNext(id, LocalDate.now());
		return info;	
	}
	
	@Override
	public Employee getEmployee(int id) {
		logger.info("Retreving the particular employee details");
		Employee e= employeeRepo.findById(id);
		logger.debug("employee details" +e);
		return e;
	}
	
	@Override
	public List<Employee> getAllEmployee(){
		logger.info("List of all employee");
		List<Employee> e=employeeRepo.findAll();
		logger.info("loaded" +e.size() + "employees");
		logger.debug("employees are" +e);
		return e;
	}

	@Override
	public List<FloorDetails> getAll() {
		List<FloorDetails> floor= floorDetailsRepo.findAll();
		return floor;
		
	}

	

	



	


}
