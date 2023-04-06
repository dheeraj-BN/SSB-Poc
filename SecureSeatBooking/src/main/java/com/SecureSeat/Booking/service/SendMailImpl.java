package com.SecureSeat.Booking.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.SecureSeat.Booking.entity.Configuration;
import com.SecureSeat.Booking.entity.MailDetails;
import com.SecureSeat.Booking.repo.ConfigurationRepo;
import com.SecureSeat.Booking.repo.MailDetailsRepo;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SendMailImpl implements SendMail {

	private static final Logger logger = LoggerFactory.getLogger(SendMailImpl.class);
	
	@Autowired
	private MailDetailsRepo mailDetailsRepo;
	
	@Autowired
	private ConfigurationRepo emailConfigurationRepo;

	@Override
	public void sendMail(String email, String subject, String body) {
		logger.info("Sending mail....");
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");
		Properties prop = new Properties();
		String fileName = "app.config";
		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop.load(fis);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			// FileNotFoundException catch is optional and can be collapsed
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				Configuration econfiguration=  emailConfigurationRepo.findById(1).get();
				logger.info("Getting Password Authenticated....");
				logger.debug("Successfully Authenticated Password!"); 
				String username = econfiguration.getEmail_Id();
				String password = econfiguration.getEamil_password();
//				String username = prop.getProperty("mail.username");
//				String password = prop.getProperty("mail.password");
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			String username = prop.getProperty("mail.username");
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			MailDetails mailDetails =new MailDetails(subject,body,email,true);
			mailDetailsRepo.save(mailDetails);
			logger.debug("Mail Sent Successfully!");
		} catch (MessagingException e) {
			MailDetails mailDetails =new MailDetails(subject,body,email,false);
			mailDetailsRepo.save(mailDetails);
			logger.error("Error Occurred while Sending Mail at Line 87 in SendMailImpl");
		}
	}
}
