package com.letstagon.facade;

import java.util.HashMap;

import com.letstagon.exception.NonFatalException;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserFacade.
 */
/* 
 * Facade for user details
 * @author ThoughtClan 
 *
 */
public interface UserFacade {

	/**
	 * Creates the.
	 *
	 * @param form the form
	 * @return the user DTO
	 */
	UserDTO create(UserDTO form);

	/**
	 * Update password.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the user DTO
	 */
	UserDTO updatePassword(String userName, String password);

	/**
	 * Gets the user details by email.
	 *
	 * @param emailAddress the email address
	 * @return the user details by email
	 */
	UserDTO getUserDetailsByEmail(String emailAddress);

	/**
	 * Gets the user details.
	 *
	 * @param userId the user id
	 * @return the user details
	 */
	UserDTO getUserDetails(long userId);

	/**
	 * Gets the user profile details.
	 *
	 * @param id the id
	 * @return the user profile details
	 */
	HashMap<String, Object> getUserProfileDetails(long id);
	
	/**
	 * Gets the user list.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the user list
	 */
	PaginatedResponseDTO getUserList(UserDTO volunteer, int page, int size);
	
	/**
	 * Gets the user party details.
	 *
	 * @param user the user
	 * @return the user party details
	 */
	PartyDTO getUserPartyDetails(UserDTO user);

	/**
	 * Save additional attributes values for user.
	 *
	 * @param user the user
	 * @throws NonFatalException the non fatal exception
	 */
	void saveAdditionalAttributesValuesForUser(
			UserDTO user) throws NonFatalException;
}
