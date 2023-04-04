package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.dao.UserDetailDao;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.HolidayDetailsRepo;
import com.SecureSeat.Booking.repo.RoleRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDetailsRepo userDetailsRepo;

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private MailTemplatesImpl mailTemplatesImpl;

	@Autowired
	private SecurityConfig securityConfig;

	@Autowired
	private HolidayDetailsRepo holidayDetailsRepo;

	@PostConstruct
	public void init() {
		LOGGER.info("Initializing UserServiceImpl class");
		Employee employee = userDetailDao.getAdminInfo();
		ResponseEntity<Map<String, String>> result = addUser(employee.getEmployeeId());
		LOGGER.info("User Added with the result: " + result);
	}

	/**
	 * Adds a user with employeeId
	 * 
	 * @param employeeId
	 * @return SUCCESS if user is added successfully, USER NOT FOUND if employee
	 *         with employeeId is not found, and USER ALREADY EXIST if user with the
	 *         given employee is already present
	 */
	public ResponseEntity<Map<String, String>> addUser(int employeeId) {
	    LOGGER.info("Adding user with employeeId: " + employeeId);
	    Employee employee = employeeRepo.findById(employeeId);

	    if (employee == null) {
	        LOGGER.error("Employee not found with employeeId: " + employeeId);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "USER NOT FOUND");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    if (userDetailsRepo.findByEmployee(employee).isPresent()) {
	        LOGGER.error("User already exist with employeeId: " + employeeId);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "USER ALREADY EXIST");
	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	    } else {
	        LOGGER.info("Creating a new user for employee: " + employee.toString());
	        Role role = roleRepo.findById(2).get();
	        Set<Role> roles = new HashSet<Role>();
	        roles.add(role);
	        UserDeatils user = new UserDeatils(securityConfig.passwordEncoder().encode("Alpha@2022"), employee, true);
	        user.setRoles(roles);
	        userDetailsRepo.save(user);
	        LOGGER.info("User added successfully for employeeId: " + employeeId);
	        mailTemplatesImpl.registrationMail(user,"Alpha@2022");

	        Map<String, String> response = new HashMap<>();
	        response.put("message", "SUCCESS");
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	}

	/**
	 * Validates the token and returns the employee associated with the booking.
	 * 
	 * @param token
	 * @return employee associated with the booking or null if booking is not found.
	 * @throws Exception
	 */

	public Employee validateToken(String token) throws Exception {
		LOGGER.debug("Validating token: {}", token);

		try {
			LocalDate currentDate = LocalDate.now();
			BookingDetails bookingDetails = bookingDetailsRepo.findByToken(token);

			if (bookingDetails.getToken().equals(token)) {
				userDetailDao.updateBookingDetails(LocalTime.now(), "LOGGED-IN", bookingDetails.getBookingId());
				LOGGER.debug("Booking details updated for booking id: {}", bookingDetails.getBookingId());
				return userDetailDao.getEmployeeDetailsAfterValidationSuccess(token);
			}
		} catch (NullPointerException e) {
			LOGGER.error("Error occurred during validation: {}", e.getMessage());
			return null;
		}

		LOGGER.debug("Token validation failed for token: {}", token);
		return null;
	}

	/**
	 * 
	 * Adds a new holiday to the system, if it doesn't already exist.
	 * 
	 * @param holidayDetails An object containing details about the holiday to be
	 *                       added.
	 * 
	 * @return A String indicating whether the holiday was successfully added or if
	 *         it already existed.
	 */
	public String addHolidays(HolidayDetails holidayDetails) {
		LOGGER.debug("Adding holiday: {}", holidayDetails.getHolidayDate());

		try {
			// Check if the holiday already exists in the database
			HolidayDetails existingHoliday = holidayDetailsRepo.findByHolidayDate(holidayDetails.getHolidayDate());
			if (existingHoliday != null) {
				LOGGER.debug("Holiday already exists: {}", holidayDetails.getHolidayDate());
				return "Holiday already added";
			}
		} catch (NullPointerException e) {
			LOGGER.error("Error occurred during holiday addition: {}", e.getMessage());
			// If there was an error, save the holiday anyway and return a success message
			holidayDetailsRepo.save(holidayDetails);
			LOGGER.debug("Holiday added: {}", holidayDetails.getHolidayDate());
			return "Holiday added";
		}

		// If the holiday already exists, return an appropriate message
		LOGGER.debug("Holiday already exists: {}", holidayDetails.getHolidayDate());
		return "Holiday already added";
	}

	public List<Employee> listOfEmployeeNotRegistered() {

		return userDetailDao.getemployee();

	}

}