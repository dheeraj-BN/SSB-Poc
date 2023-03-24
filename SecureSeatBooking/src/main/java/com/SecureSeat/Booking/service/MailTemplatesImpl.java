package com.SecureSeat.Booking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;

public class MailTemplatesImpl {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public String registrationMail(UserDeatils userInfo) {
		Employee emp=userInfo.getEmployee();
		String subject="Secure Seat Booking Registed";
		String body= "Hello "+emp.getEmployeeName()+",     Welcome To Secure Seat Booking(SSB) Valtech"
				+ "you username"+emp.getEmployeeEmail()+ "      password   :"+userInfo.getPassword();
		return body;
	}

}
