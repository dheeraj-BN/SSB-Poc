package com.SecureSeat.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Configuration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String ACCOUNT_SID;
	private String AUTH_TOKEN;
	private String SMS_NUMBER;
	private String Email_Id;
	private String Eamil_password;
	private int Seat_Cancelation_time;
	
	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Configuration(int id, String aCCOUNT_SID, String aUTH_TOKEN, String sMS_NUMBER) {
		super();
		Id = id;
		ACCOUNT_SID = aCCOUNT_SID;
		AUTH_TOKEN = aUTH_TOKEN;
		SMS_NUMBER = sMS_NUMBER;
	}
	public Configuration(String aCCOUNT_SID, String aUTH_TOKEN, String sMS_NUMBER) {
		super();
		ACCOUNT_SID = aCCOUNT_SID;
		AUTH_TOKEN = aUTH_TOKEN;
		SMS_NUMBER = sMS_NUMBER;
	}
	
	
	public Configuration(int id, String aCCOUNT_SID, String aUTH_TOKEN, String sMS_NUMBER, String email_Id,
			String eamil_password, int seat_Cancelation_time) {
		super();
		Id = id;
		ACCOUNT_SID = aCCOUNT_SID;
		AUTH_TOKEN = aUTH_TOKEN;
		SMS_NUMBER = sMS_NUMBER;
		Email_Id = email_Id;
		Eamil_password = eamil_password;
		Seat_Cancelation_time = seat_Cancelation_time;
	}
	public Configuration(String aCCOUNT_SID, String aUTH_TOKEN, String sMS_NUMBER, String email_Id,
			String eamil_password, int seat_Cancelation_time) {
		super();
		ACCOUNT_SID = aCCOUNT_SID;
		AUTH_TOKEN = aUTH_TOKEN;
		SMS_NUMBER = sMS_NUMBER;
		Email_Id = email_Id;
		Eamil_password = eamil_password;
		Seat_Cancelation_time = seat_Cancelation_time;
	}
	public String getEmail_Id() {
		return Email_Id;
	}
	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}
	public String getEamil_password() {
		return Eamil_password;
	}
	public void setEamil_password(String eamil_password) {
		Eamil_password = eamil_password;
	}
	public int getSeat_Cancelation_time() {
		return Seat_Cancelation_time;
	}
	public void setSeat_Cancelation_time(int seat_Cancelation_time) {
		Seat_Cancelation_time = seat_Cancelation_time;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getACCOUNT_SID() {
		return ACCOUNT_SID;
	}
	public void setACCOUNT_SID(String aCCOUNT_SID) {
		ACCOUNT_SID = aCCOUNT_SID;
	}
	public String getAUTH_TOKEN() {
		return AUTH_TOKEN;
	}
	public void setAUTH_TOKEN(String aUTH_TOKEN) {
		AUTH_TOKEN = aUTH_TOKEN;
	}
	public String getSMS_NUMBER() {
		return SMS_NUMBER;
	}
	public void setSMS_NUMBER(String sMS_NUMBER) {
		SMS_NUMBER = sMS_NUMBER;
	}
	@Override
	public String toString() {
		return "SMSconfiguration [Id=" + Id + ", ACCOUNT_SID=" + ACCOUNT_SID + ", AUTH_TOKEN=" + AUTH_TOKEN
				+ ", SMS_NUMBER=" + SMS_NUMBER + "]";
	}
	
	
	
	
	

}
