package com.SecureSeat.Booking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MailDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mailId;
	private String subject;
	private String Body;
	@ManyToOne(targetEntity = UserDeatils.class, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private UserDeatils userDeatils;

	public MailDetails() {
	}

	public MailDetails(String subject, String body, UserDeatils userDeatils) {
		super();
		this.subject = subject;
		Body = body;
		this.userDeatils = userDeatils;
	}

	public MailDetails(int mailId, String subject, String body, UserDeatils userDeatils) {
		super();
		this.mailId = mailId;
		this.subject = subject;
		Body = body;
		this.userDeatils = userDeatils;
	}

	public int getMailId() {
		return mailId;
	}

	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public UserDeatils getUserDeatils() {
		return userDeatils;
	}

	public void setUserDeatils(UserDeatils userDeatils) {
		this.userDeatils = userDeatils;
	}

	@Override
	public String toString() {
		return "MailDetails [mailId=" + mailId + ", subject=" + subject + ", Body=" + Body + ", userDeatils="
				+ userDeatils + "]";
	}
	
	

}
