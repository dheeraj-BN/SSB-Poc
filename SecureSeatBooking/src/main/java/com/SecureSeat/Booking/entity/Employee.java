package com.SecureSeat.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	private int employeeId;
	private String employeeName;
	private String employeeEmail;
	private String employeeDesignation;
	private String employeePhoneNo;
	private String employeeGender;
	private String employeePersonalEmail;

	public Employee() {
	}

	public Employee(int employeeId, String employeeName, String employeeEmail, String employeeDesignation,
			String employeePhoneNo, String employeeGender, String employeePersonalEmail) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeeDesignation = employeeDesignation;
		this.employeePhoneNo = employeePhoneNo;
		this.employeeGender = employeeGender;
		this.employeePersonalEmail = employeePersonalEmail;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeePhoneNo() {
		return employeePhoneNo;
	}

	public void setEmployeePhoneNo(String employeePhoneNo) {
		this.employeePhoneNo = employeePhoneNo;
	}

	

	public String getEmployeePersonalEmail() {
		return employeePersonalEmail;
	}

	public void setEmployeePersonalEmail(String employeePersonalEmail) {
		this.employeePersonalEmail = employeePersonalEmail;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeDesignation=" + employeeDesignation + ", employeePhoneNo="
				+ employeePhoneNo + ", employeeGender=" + employeeGender + ", employeePersonalEmail="
				+ employeePersonalEmail + "]";
	}
	
	

	
}
