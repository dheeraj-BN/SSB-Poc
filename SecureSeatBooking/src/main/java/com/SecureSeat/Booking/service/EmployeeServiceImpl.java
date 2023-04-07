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
	private SmsTemplate smsTemplate;

	@Autowired
	private FloorDetailsRepo floorDetailsRepo;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	// Get the details of the last booking made by the user with the given ID
	@Override
	public BookingDetails getbookingdetailsbyid(int id) {
		logger.info("Last booking details for user ID ", id);
		return seatBookDAO.getlatestbookingdetailsofid(id);
	}

	// This method saves the details of the last booking made by a user, for a given
	// ID, and checks if the seat is available
	@Override
	public String savelastbookingdetails(int id, LocalDate from, LocalDate to) {

		// Get the details of the last booking made by the user
		BookingDetails bookingDetails = seatBookDAO.getlatestbookingdetailsofid(id);

		// Create a new BookingDetails object with the same details as the last booking
		BookingDetails book = new BookingDetails();
		book.setSeatNo(bookingDetails.getSeatNo());
		book.setFoodStatus(bookingDetails.isFoodStatus());
		book.setUserDeatils(bookingDetails.getUserDeatils());
		book.setShiftDetails(bookingDetails.getShiftDetails());
		// Check if the seat is available for the given date range
		logger.info("Seat availability status for user ID  {}", id);
		return seatBook.checkseatfordate(book, from, to);

	}

	// forgot password change
	@Override
	public String forgotPasword(int id, String password) {
		int userid = id;
		logger.info("Resetting password for user ID: {}", userid);
		employeeDAO.restPassword(config.passwordEncoder().encode(password), userid);
		logger.info("Password reset for user ID: {}", userid);
		return "Password Changed";

	}

	// find userid by employee phone number
	@Override
	public int findUserIdByPhoneNo(String phoneNo) {
		logger.info("Finding user ID for phone number: {}", phoneNo);
		int userid = employeeDAO.forgotPassword(phoneNo);
		logger.info("User ID found: {}", userid);
		return userid;

	}

	// random number generate
	@Override
	public String getRandomNumberString() {
		logger.info("Generating random number string");
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	// generate the otp
	@Override
	public String generateOtp(String phoneNo) {
		logger.info("Generating OTP for phone number: {}", phoneNo);
		String otp1 = getRandomNumberString();
		Employee emp1 = employeeRepo.findByEmployeePhoneNo(phoneNo);
		smsTemplate.sentOTP(emp1, otp1);
		logger.info("OTP sent to employee: {}", emp1);
		return otp1;

	}

	// This method is used to change the password for a user
	@Override
	public String changePassword(int id, String oldPassword, String newPassword) {
		logger.info("getting the user_id and user details for change password");
		logger.info("user_id is" + id);
		// retrieving the user details from the database using user_id
		UserDeatils user = userDetailsRepo.findByUserId(id).get();
		logger.info("userInfo" + user);
		// checking if the old password entered by the user is correct
		if (config.passwordEncoder().matches(oldPassword, user.getPassword())) {
			// if the old password is correct, encoding the new password and updating the
			// password in the database
			employeeDAO.changePasswor(config.passwordEncoder().encode(newPassword), id);
			return "Password changed";
		} else {
			logger.error("password wrong");
			return "Old Password doesn't matched";
		}
	}

	// This method is used to retrieve the booking details of an employee for today.
	@Override
	public BookingDetails getEmpBookedInfo(int id) {
		logger.info("Fetching booking details of employee booked for today");
		// retrieving the user details from the database using the user id
		UserDeatils user = userDetailsRepo.findByUserId(id).get();
		logger.info("employee " + user);
		// retrieving the booking details of the employee for today
		BookingDetails info = bookingDetailsRepo.findByUserDeatilsAndBookedDateEquals(user, LocalDate.now());
		return info;
	}

	// This method retrieves a list of BookingDetails for an employee booked for the
	// next days
	@Override
	public List<BookingDetails> getEmpBookedInfoBookedNext(int id) {
		logger.info("Fetching booking details of employee booked for next days");
		UserDeatils user = userDetailsRepo.findByUserId(id).get();
		logger.info("employee " + user);
		// retrieve a list of BookingDetails objects for the given employee id and for
		// the next days
		List<BookingDetails> info = employeeDAO.getEmpBookedInfoBookedNext(id, LocalDate.now());
		return info;
	}

	// This method retrieves the details of an employee by their ID
	@Override
	public Employee getEmployee(int id) {
		logger.info("Retreving the particular employee details");
		// Use the employee repository to find the employee with the given ID
		Employee e = employeeRepo.findById(id);
		logger.info("employee details" + e);
		return e;
	}

	// This method retrieves the details of an list employee by their ID
	@Override
	public List<Employee> getAllEmployee() {
		logger.info("List of all employee");
		List<Employee> e = employeeRepo.findAll();
		logger.info("loaded" + e.size() + "employees");
		logger.info("employees are" + e);
		return e;
	}

	// This method retrieves the details of an list floor details by their ID
	@Override
	public List<FloorDetails> getAll() {
		logger.info("List of all the floors");
		List<FloorDetails> floor = floorDetailsRepo.findAll();
		logger.info("loaded" + floor.size() + "floors");
		return floor;

	}

}
