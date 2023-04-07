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
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private FloorService floorService;
	
	@Autowired
	private UserFirstTimeLoginService userFirstTimeLoginService;
	
	
	


	// This method handles GET requests for retrieving the last booking details of an employee by ID.
	@GetMapping("/api/employee/lastbookingdetails/{id}")
	public BookingDetails getbookingdetails(@PathVariable int id) {
		System.out.println(id);
		return employeeService.getbookingdetailsbyid(id);
	}
	
	
	// This method handles POST requests for saving the last booking details of an employee by ID.
	@PostMapping("/api/employee/savelastbooking/{id}")
    public String savelastbookingdetails(@PathVariable int id,@RequestParam("from") LocalDate from,@RequestParam("to") LocalDate to) {
		return employeeService.savelastbookingdetails(id, from, to);
		
	}
	
	// This method handles PUT requests for saving new password.
	@PutMapping("/api/new/password/{id}")
	public String forgotPassword(@PathVariable int id,@RequestParam("newPassword")String newPassword) {
		String message=	employeeService.forgotPasword( id,newPassword);
		return message;
		
	}
	


	// This method handles POST requests getting OTP
	@PostMapping("/api/forgot/password")
	public String generateOtp(@RequestParam String phoneNo) {
		employeeService.findUserIdByPhoneNo(phoneNo);
		return employeeService.generateOtp(phoneNo);
	}
	
	

	// This method handles PUT requests for saving new password.
	@PutMapping("/api/employee/change/password/{id}")
	public String changePassword(@PathVariable int id,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword)throws Exception {
		try {
		String message=employeeService.changePassword(id,oldPassword,newPassword);
    	mailService.passwordChangeConfirmMail(id);
		return message;
		
	}catch (Exception e) {
		 e.printStackTrace();
		    return "An error occurred " + e.getMessage();
		  }
	}
	
	

	// This method handles GET requests for retrieving the todays booked details of an employee by ID.
	@GetMapping("/api/employee/booked/details/{id}")
	public BookingDetails bookedInfo(@PathVariable int id)throws NullPointerException  {
		try {
			return employeeService.getEmpBookedInfo(id);
			
		}catch (NullPointerException e) {
			 throw new ResponseStatusException(HttpStatus.OK, "Employee not found", e);
		  } catch (Exception e) {
		    e.printStackTrace();
		    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
		  }
		
	}
	 
	
	// This method handles GET requests for retrieving the next booked details of an employee by ID.
	@GetMapping("/api/employee/next/booked/details/{id}")
	public List<BookingDetails> nextBookedInfo(@PathVariable int id){
		return employeeService.getEmpBookedInfoBookedNext(id);	
	}
	

	
	
	// This method handles GET requests for retrieving the employee details of an employee by ID.
	@GetMapping("/api/employee/employee/{id}")
	public Employee getEmployee(@PathVariable int id)throws NullPointerException {	
		try {
			return employeeService.getEmployee(id);
	}catch (NullPointerException e) {
		 throw new ResponseStatusException(HttpStatus.OK, "Employee not found", e);
	  } catch (Exception e) {
	    e.printStackTrace();
	    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
	  }
	}
	
	// This method handles GET requests for retrieving the list of employee details 
	@GetMapping("/api/employee/employeeList")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployee();
	}
	
	
	// This method handles PUT requests for changing one time password
	@PutMapping("/api/employee/change/FTChangepassword/{id}")
	public String changePassword(@PathVariable("id") int userId,@RequestParam("newPassword") String newPassword)throws Exception {
		try {
		
		String message=userFirstTimeLoginService.firstTimeChangeOfPassword( userId, newPassword);
    	
		return message;
		
	}catch (Exception e) {
		 e.printStackTrace();
		    return "An error occurred " + e.getMessage();
		  }
	}
	
	// This method handles GET requests retrieving the floor details 
	@GetMapping("/api/employee/floors")
    public List<FloorDetails> getFloors() {
        return employeeService.getAll();
    }
	
	// This method handles GET requests retrieving the list of floor details 
	@GetMapping("/api/employee/floors/{floorName}")
    public FloorDetails getFloorDetailsByFloorName(@PathVariable String floorName) {
        return floorService.getFloorDetailsByFloorName(floorName);
    }
	
}