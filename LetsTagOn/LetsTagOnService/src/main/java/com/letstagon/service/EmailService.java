package com.letstagon.service;

import org.springframework.mail.javamail.MimeMessagePreparator;


// TODO: Auto-generated Javadoc
/**
 * The Interface EmailService.
 */
/* 
 * Service for handling email 
 * @author ThoughtClan
 *
 */
public interface EmailService {
	
	/**
	 * Send LTO notification.
	 *
	 * @param name the name
	 * @param email the email
	 */
	public void sendLTONotification(String name, String email);
	
	/**
	 * Send LTO notification.
	 *
	 * @param name the name
	 * @param email the email
	 */
	public void sendLTONotificationEmailVerfication(String name, String email, String token);
	
	/**
	 * Send Contact Us mail notification.
	 *
	 * @param name the name
	 * @param email the email
	 * @param message the message
	 */
	public void sendContactUsMail(String name, String email, String message);

	/**
	 * Send email.
	 *
	 * @param preparator the preparator
	 */
	void sendEmail(MimeMessagePreparator preparator);

}
