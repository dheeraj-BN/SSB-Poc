package com.SecureSeat.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SMSconfiguration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String ACCOUNT_SID;
	private String AUTH_TOKEN;
	private String SMS_NUMBER;
	public SMSconfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SMSconfiguration(int id, String aCCOUNT_SID, String aUTH_TOKEN, String sMS_NUMBER) {
		super();
		Id = id;
		ACCOUNT_SID = aCCOUNT_SID;
		AUTH_TOKEN = aUTH_TOKEN;
		SMS_NUMBER = sMS_NUMBER;
	}
	public SMSconfiguration(String aCCOUNT_SID, String aUTH_TOKEN, String sMS_NUMBER) {
		super();
		ACCOUNT_SID = aCCOUNT_SID;
		AUTH_TOKEN = aUTH_TOKEN;
		SMS_NUMBER = sMS_NUMBER;
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
