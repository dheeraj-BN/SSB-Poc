package com.SecureSeat.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.SecureSeat.Booking.config.SecurityConfig;
import com.SecureSeat.Booking.dao.SeatBookDAO;
import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.EmployeeService;



@SpringBootTest
@ContextConfiguration()
public class EmployeeTest{
	
	 
	    @Autowired
	    private  EmployeeService employeeService;
	    
	    @Autowired
	    private BookingDetailsRepo bookingDetailsRepo;
	    
	
	    
	    @Autowired
		public UserDetailsRepo userDetailsRepo;
	    
	    @Autowired
		private SecurityConfig config;
	
//	@Test
//	
//	public void changePassword() {
//		UserDeatils user=userDetailsRepo.findByUserId(18).get();
//		System.out.println(user);
//		
//		String oldPassword="Alpha@2022";
//	      
//		String npass="Alpha@2001";
//		 
//		employeeService.changePassword(18,oldPassword,npass);
//		  
//	    user=userDetailsRepo.findByUserId(18).get();
//		 
//		 assertTrue(config.passwordEncoder().matches(npass,user.getPassword()));     
//	    }
		
//	@Test
//	public void todayBooked() {
//	 BookingDetails booked = employeeService.getEmpBookedInfo(2);
//     System.out.println(booked.getDate());
//		LocalDate date=LocalDate.now();
//		assertEquals(date,booked.getDate());
//	}
	
	 
	}


