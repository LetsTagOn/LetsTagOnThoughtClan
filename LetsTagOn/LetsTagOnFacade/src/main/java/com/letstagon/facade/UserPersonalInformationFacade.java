package com.letstagon.facade;

import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.letstagon.facade.dto.ProfileCompletionStatusDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserPersonalInformationFacade.
 */
/* 
 * Facade for user personal and additionalInformation
 * @author ThoughtClan 
 *
 */
public interface UserPersonalInformationFacade {

	/**
	 * Save or update profile form.
	 *
	 * @param userDetails the user details
	 * @return the user DTO
	 * @throws DataAccessException the data access exception
	 * @throws Exception the exception
	 */
	UserDTO saveOrUpdateProfileForm(UserDTO userDetails) throws DataAccessException, Exception;

	/**
	 * Gets the user details.
	 *
	 * @param userId the user id
	 * @return the user details
	 */
	HashMap<String, Object> getUserDetails(long userId);

	/**
	 * Gets the profile completion status.
	 *
	 * @param userID the user ID
	 * @return the profile completion status
	 */
	ProfileCompletionStatusDTO getProfileCompletionStatus(long userID);

}
