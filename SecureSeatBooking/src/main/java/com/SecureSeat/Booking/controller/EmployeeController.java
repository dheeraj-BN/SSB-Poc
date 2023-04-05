package com.SecureSeat.Booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.service.EmployeeService;
import com.SecureSeat.Booking.service.FloorService;
import com.SecureSeat.Booking.service.MailService;
import com.SecureSeat.Booking.service.UserFirstTimeLoginService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private FloorService floorService;
	
	@Autowired
	private UserFirstTimeLoginService userFirstTimeLoginService;
	
	
//	
	@GetMapping("/lastbookingdetails/{id}")
	public BookingDetails getbookingdetails(@PathVariable int id) {
		System.out.println(id);
		return employeeService.getbookingdetailsbyid(id);
	}
	
	@PostMapping("/savelastbooking/{id}")
    public String savelastbookingdetails(@PathVariable int id,@RequestParam("from") LocalDate from,@RequestParam("to") LocalDate to) {
		return employeeService.savelastbookingdetails(id, from, to);
		
	}
	
	
	@PostMapping("/new/password/{id}")
	public String forgotPassword(@PathVariable int id,@RequestParam("newPassword")String newPassword) {
		String message=	employeeService.forgotPasword( id,newPassword);
		return message;
		
	}
	
	

	
	@PostMapping("/forgot/password")
	public String generateOtp(@RequestParam String phoneNo) {
		System.out.println("hii");
		return employeeService.generateOtp();
	}
	
	
	
	@PutMapping("/change/password/{id}")
	public String changePassword(@PathVariable int id,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword)throws Exception {
		try {
//		System.out.println("controller "+id);
		String message=employeeService.changePassword(id,oldPassword,newPassword);
    	mailService.passwordChangeConfirmMail(id);
		return message;
		
	}catch (Exception e) {
		 e.printStackTrace();
		    return "An error occurred " + e.getMessage();
		  }
	}
	
	@GetMapping("/employee/booked/details/{id}")
	public BookingDetails bookedInfo(@PathVariable int id) {
		return employeeService.getEmpBookedInfo(id);
	}
	 
	@GetMapping("/employee/next/booked/details/{id}")
	public List<BookingDetails> nextBookedInfo(@PathVariable int id){
//		sySystem.out.println("hi");
		System.out.println("hi");
		return employeeService.getEmpBookedInfoBookedNext(id);	
	}
	
//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving booking details.");
//    }
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id)throws NullPointerException {	
		try {
			System.out.println("hii");
			return employeeService.getEmployee(id);
	}catch (NullPointerException e) {
		 throw new ResponseStatusException(HttpStatus.OK, "Employee not found", e);
	  } catch (Exception e) {
	    e.printStackTrace();
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
	  }
	}
	
	
	@GetMapping("/employeeList")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployee();
	}
	
	@PutMapping("/change/FTChangepassword/{id}")
	public String changePassword(@PathVariable("id") int userId,@RequestParam("newPassword") String newPassword)throws Exception {
		try {
		
		String message=userFirstTimeLoginService.firstTimeChangeOfPassword( userId, newPassword);
    	
		return message;
		
	}catch (Exception e) {
		 e.printStackTrace();
		    return "An error occurred " + e.getMessage();
		  }
	}
	
	@GetMapping("/floors")
    public List<FloorDetails> getFloors() {
        return employeeService.getAll();
    }
	
	@GetMapping("/floors/{floorName}")
    public FloorDetails getFloorDetailsByFloorName(@PathVariable String floorName) {
        return floorService.getFloorDetailsByFloorName(floorName);
    }
	
}