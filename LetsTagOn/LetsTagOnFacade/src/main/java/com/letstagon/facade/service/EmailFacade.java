package com.letstagon.facade.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.letstagon.exception.InvalidTokenException;
import com.letstagon.exception.LinkExpiredException;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmailFacade.
 */
public interface EmailFacade {
	
	/**
	 * Send LTO notification.
	 *
	 * @param name the name
	 * @param email the email
	 * @throws Exception the exception
	 */
	public void sendLTONotification(String name, String email) throws Exception;
	
	/**
	 * Send Contact Us notification.
	 *
	 * @param name the name
	 * @param email the email
	 * @param message the message
	 * @throws Exception the exception
	 */
	public void sendContactUsMail(String name, String email, String message) throws Exception;

	/**
	 * Forgot password.
	 *
	 * @param userName the user name
	 * @throws Exception the exception
	 * @throws UsernameNotFoundException the username not found exception
	 */
	public void forgotPassword(String userName) throws Exception, UsernameNotFoundException;

	/**
	 * Reset password.
	 *
	 * @param user the user
	 * @param token the token
	 * @param password the password
	 * @throws LinkExpiredException the link expired exception
	 * @throws InvalidTokenException the invalid token exception
	 */
	public void resetPassword(String user, String token, String password)
			throws LinkExpiredException, InvalidTokenException;
}
