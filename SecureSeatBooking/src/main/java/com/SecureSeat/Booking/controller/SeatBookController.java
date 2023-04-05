package com.SecureSeat.Booking.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.dao.UserDetailDao;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.ShiftDetailsRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.SeatBook;

@RestController

public class SeatBookController {
	
	@Autowired
	private SeatBook seatBook;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo ;
	
	@Autowired
	private ShiftDetailsRepo shiftDetailsRepo;
	
	@Autowired
	private UserDetailDao userDetailDao;
	

	
	
	@GetMapping("api/employee/getuser")
	public List<UserDeatils> getuser() {
		return userDetailsRepo.findAll();
	}
	
	@GetMapping("api/employee/getshift")
	public List<ShiftDetails> getshift() {
		return shiftDetailsRepo.findAll();
	}
	
	
	@PostMapping("api/employee/seatbookdetails")
	public String saveBookedDetails(@RequestBody BookingDetails bookingDetails,@RequestParam("from") LocalDate from,@RequestParam("to") LocalDate to) {
		String message =seatBook.savebookeddetails(bookingDetails, from, to);
		return message;
	}
	
	@GetMapping("api/employee/seatnumber/{seatno}/{date1}")
	public int seatBookedOrNot(@PathVariable String seatno,@PathVariable LocalDate date1) {

	int message = seatBook.checkseatalreadybooked(seatno, date1);
	return message;
		
	}
	
	@GetMapping("api/employee/seatnumber/{date1}")
	public List<String> seatnumberbookedfordate(@PathVariable LocalDate date1){
		List<String> seatNos = seatBook.getSeatNoByDate(date1);
		return seatNos;
		}
	
	@GetMapping("api/employee/bookingdetails/{date1}")
	public List<BookingDetails> seatbookingdetailsfordate(@PathVariable LocalDate date1){
		List<BookingDetails> seatNos = seatBook.getbookingdetails(date1);
		return seatNos;
		}
	
	@PutMapping("api/employee/cancel/{token}")
	public void updatecancel(@PathVariable String token) {
		seatBook.updatecanceledetails(token);
	}
	
	@PutMapping("api/employee/updateseat/{token}")
	public void updateseat(@PathVariable String token,@RequestParam String seatno) {
		seatBook.updateseatno(token, seatno);
	}
	
	@PutMapping("api/employee/updatefoodstatus/{token}")
	public void updatefoodstatus(@PathVariable String token,@RequestParam Boolean foodstatus) {
		seatBook.updatefoodstatus(token, foodstatus);
	}
	
	@PutMapping("api/employee/updatebookingstatus/{token}")
	public void updateseatbooking(@PathVariable String token,@RequestParam Boolean foodstatus,@RequestParam String seatno) {
		seatBook.updateseatbooking(token, foodstatus, seatno);
	}
	
	@GetMapping("/api/employee/schedule")
	public void getschedule(){
		 seatBook.updatecancelforschedule();
	}
	
	@GetMapping("/api/employee/{id}")
	public BookingDetails getbyid(@PathVariable int id) {
		return seatBook.getlatestbookingdetailsofid(id);
	}

	

}
