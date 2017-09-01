package com.letstagon.service;

import com.letstagon.exception.InvalidTokenException;
import com.letstagon.exception.LinkExpiredException;
// TODO: Auto-generated Javadoc

/**
 * The Interface ResetPasswordService.
 */
/* 
 * Service for handling resetPassword 
 * @author ThoughtClan
 *
 */
public interface ResetPasswordService {

/**
 * Reset password.
 *
 * @param user the user
 * @param token the token
 * @param password the password
 * @throws LinkExpiredException the link expired exception
 * @throws InvalidTokenException the invalid token exception
 */
public void resetPassword(String user,String token,String password)throws LinkExpiredException,InvalidTokenException;
}
