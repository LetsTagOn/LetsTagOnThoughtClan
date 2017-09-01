package com.letstagon.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ForgotPasswordService.
 */
/* 
 * Service for handling forgotPassword 
 * @author ThoughtClan
 *
 */
public interface ForgotPasswordService {
	
	/**
	 * Forgot password.
	 *
	 * @param userName the user name
	 * @throws UsernameNotFoundException the username not found exception
	 */
	public void forgotPassword(String userName) throws UsernameNotFoundException;
}
