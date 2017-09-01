package com.letstagon.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.letstagon.dao.model.ProfileCompletionStatusModel;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;
import com.letstagon.exception.NonFatalException;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserPersonalInformationService.
 */
/* 
 * Service for handling user personal and additional information   
 * @author ThoughtClan
 *
 */
public interface UserPersonalInformationService {

	/**
	 * Save or update profile form.
	 *
	 * @param userDetails the user details
	 * @return the user
	 * @throws DataAccessException the data access exception
	 * @throws Exception the exception
	 */
	User saveOrUpdateProfileForm(User userDetails) throws DataAccessException, Exception;

	/**
	 * Save additional attributes values for user.
	 *
	 * @param user the user
	 * @return the list
	 * @throws NonFatalException the non fatal exception
	 */
	List<UserAdditionalProfileAttribute> saveAdditionalAttributesValuesForUser(User user) throws NonFatalException;

	/**
	 * Gets the profile completion status.
	 *
	 * @param userID the user ID
	 * @return the profile completion status
	 */
	ProfileCompletionStatusModel getProfileCompletionStatus(long userID);

}
