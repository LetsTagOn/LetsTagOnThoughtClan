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
	 * Send email.
	 *
	 * @param preparator the preparator
	 */
	void sendEmail(MimeMessagePreparator preparator);

}
