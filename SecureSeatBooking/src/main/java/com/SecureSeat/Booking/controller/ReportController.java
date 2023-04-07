package com.SecureSeat.Booking.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.SecureSeat.Booking.dao.ReportDao;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.ReportService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/admin")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	


	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;
	
	
	@Autowired
	private UserDetailsRepo detailsRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	
	
	//specific date jdbbc
	  @GetMapping("/bookingdetails/date/{bookingDateStr}")
	    public ResponseEntity<List<BookingDetails>> getBookingDetailsByDate(@PathVariable("bookingDateStr") String bookingDateStr) {
	        LocalDate bookingDate = LocalDate.parse(bookingDateStr); // convert the string to LocalDate object
	        List<BookingDetails> bookingDetails = reportService.getBookingDetailsByDate(bookingDate);
	        return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
	    }
	
	  
	  //date with status jdbc
	  @GetMapping("/bookings/{bookingDate}/{bookingStatus}")
	  public ResponseEntity<List<BookingDetails>> getBookingsByDateAndStatus(
	      @PathVariable("bookingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
	      @PathVariable("bookingStatus") String bookingStatus) {

	      List<BookingDetails> bookings = reportService.getBookingsByDateAndStatus(bookingDate, bookingStatus);

	      if (bookings.isEmpty()) {
	          return ResponseEntity.noContent().build();
	      } else {
	          return ResponseEntity.ok(bookings);
	      }
	  }

	 ////////////////

	  
	   @GetMapping("/date/status/{date}/{status}")
	    public List<BookingDetails> getBookingByDateAndStatus(
	            @PathVariable("date") String dateString,
	            @PathVariable("status") String status) {

	        LocalDate date = LocalDate.parse(dateString);

	        return bookingDetailsRepo.findByBookedDateAndBookingStatus(date, status);
	    }
	   
	   

	   
	   //date and booked date
	  
	   @GetMapping("/date/{bookedDate}")
		public List<BookingDetails> getBookingByDate(@PathVariable String bookedDate) {
			LocalDate date = LocalDate.parse(bookedDate);
			return bookingDetailsRepo.findByBookedDate(date);
		}
	  
	   

	   
	   @GetMapping("/bookings/month/{year}/{month}")
		public List<BookingDetails> getBookingBySpecificMonth(
				@PathVariable int year, 
				@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) Month month) {
			
			LocalDate startDate = LocalDate.of(year, month, 1);
			LocalDate endDate = startDate.plusMonths(1).minusDays(1);
			
			return bookingDetailsRepo.findByBookedDateBetween(startDate, endDate);
		}
	   
	   
	   
	   
	   @GetMapping("/bookings/{week}")
	   public List<BookingDetails> getBookingBySpecificWeek(@PathVariable("week") int week) {
	       LocalDate currentDate = LocalDate.now();
	       LocalDate startDateOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).plusWeeks(week);
	       LocalDate endDateOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).plusWeeks(week);

	       List<BookingDetails> bookingDetailsList = bookingDetailsRepo.findByBookedDateBetween(startDateOfWeek, endDateOfWeek);

	       return bookingDetailsList;
	   }
	   
	   


	   
	   

	   @GetMapping("/bookings/employee/{emp_id}")
	   public List<BookingDetails> getEmployeeBookings(@PathVariable int emp_id) {
		   
		   Employee employee = employeeRepo.findById(emp_id);
		   
		   UserDeatils userDetails = detailsRepo.findByEmployee(employee).get();
		   
		 List<BookingDetails> bookingDetails=  bookingDetailsRepo.findByUserDeatils(userDetails);
		   
		 return bookingDetails;
	   }


	   


	   @GetMapping("/bookings/employee/{emp_id}/{year}/{month}")
	   public List<BookingDetails> getEmployeeBookingsForMonth(@PathVariable int emp_id, @PathVariable int year, @PathVariable int month) {
	       Employee employee = employeeRepo.findById(emp_id);
	       UserDeatils userDetails = detailsRepo.findByEmployee(employee).orElseThrow(EntityNotFoundException::new);
	       LocalDate startDate = LocalDate.of(year, month, 1);
	       LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
	       List<BookingDetails> bookings = bookingDetailsRepo.findByUserDeatilsAndBookedDateBetween(userDetails, startDate, endDate);
	       return bookings;
	   }




    
	   
	   @GetMapping("/bookings/employee/{emp_id}/{year}/{month}/{week}")
	   public List<BookingDetails> getEmployeeBookingsForWeek(@PathVariable int emp_id, @PathVariable int year, @PathVariable int month, @PathVariable int week) {
	       Employee employee = employeeRepo.findById(emp_id);
	       UserDeatils userDetails = detailsRepo.findByEmployee(employee).orElseThrow(EntityNotFoundException::new);
	       LocalDate startDate = LocalDate.of(year, month, 1).with(TemporalAdjusters.firstDayOfMonth()).plusWeeks(week - 1);
	       LocalDate endDate = startDate.plusDays(6);
	       return bookingDetailsRepo.findByUserDeatilsAndBookedDateBetween(userDetails, startDate, endDate);
	   }
	   
	   
	   

		@GetMapping("/booking-count")
		public Long getTotalBookingCounts() {
			return bookingDetailsRepo.count();
		}
		
		

		
	
		
		@GetMapping("/bookings/count/{date}")
		public Long getBookingCountByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		    return bookingDetailsRepo.countByBookedDate(date);
		}
		
		
		@GetMapping("/employee-count/food/{date}/{foodStatus}")
		public long getEmployeeCount(@PathVariable String date, @PathVariable boolean foodStatus) {
		    LocalDate bookingDate = LocalDate.parse(date);
		    List<BookingDetails> bookingDetailsList = bookingDetailsRepo.findByBookedDate(bookingDate);
		    if (foodStatus) {
		        return bookingDetailsList.stream().filter(bookingDetails -> bookingDetails.isFoodStatus()).count();
		    } else {
		        return bookingDetailsList.stream().filter(bookingDetails -> !bookingDetails.isFoodStatus()).count();
		    }
		}



		

		
		@GetMapping("/employee-count/{date}/{booking_Status}")
		public long countBookingsByDateAndStatus(@PathVariable("date") String date, @PathVariable("booking_Status") String bookingStatus) {
		    LocalDate bookingDate = LocalDate.parse(date);
		    List<BookingDetails> bookingDetailsList = bookingDetailsRepo.findByBookedDateAndBookingStatus(bookingDate, bookingStatus);
		    return bookingDetailsList.size();
		}

	

}
