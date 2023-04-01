package com.SecureSeat.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MailDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mailId;
	private String subject;
	private String body;
	private String toMail;
//	@ManyToOne(targetEntity = UserDeatils.class, cascade = { CascadeType.MERGE,
//			CascadeType.PERSIST }, fetch = FetchType.EAGER)
//	@JoinColumn(name = "userId")
//	private UserDeatils userDeatils;
	
	private boolean status;
	
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public MailDetails() {
	}

//	public MailDetails(String subject, String body, UserDeatils userDeatils) {
//		super();
//		this.subject = subject;
//		Body = body;
//		this.userDeatils = userDeatils;
//	}
//
//	public MailDetails(int mailId, String subject, String body, UserDeatils userDeatils) {
//		super();
//		this.mailId = mailId;
//		this.subject = subject;
//		Body = body;
//		this.userDeatils = userDeatils;
//	}
	
	
	

	public int getMailId() {
		return mailId;
	}



	public MailDetails(int mailId, String subject, String body, String toMail, boolean status) {
	super();
	this.mailId = mailId;
	this.subject = subject;
	this.body = body;
	this.toMail = toMail;
	this.status = status;
}

	
	public MailDetails(String subject, String body, String toMail, boolean status) {
		super();
		this.subject = subject;
		this.body = body;
		this.toMail = toMail;
		this.status = status;
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
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	@Override
	public String toString() {
		return "MailDetails [mailId=" + mailId + ", subject=" + subject + ", body=" + body + ", toMail=" + toMail
				+ ", status=" + status + "]";
	}

	
	

}
