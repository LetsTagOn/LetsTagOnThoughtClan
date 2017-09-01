package com.letstagon.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.NonUniqueResultException;

import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
// TODO: Auto-generated Javadoc

/**
 * The Interface UserService.
 */
/* 
 * Service for handling user functionality 
 * @author ThoughtClan
 *
 */
public interface UserService {

	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	Optional<User> getUserById(long id);

	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @return the user by email
	 */
	Optional<User> getUserByEmail(String email);

	/**
	 * Gets the user by user name.
	 *
	 * @param userName the user name
	 * @return the user by user name
	 */
	Optional<User> getUserByUserName(String userName);

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	Iterable<User> getAllUsers();

	/**
	 * Creates the.
	 *
	 * @param form the form
	 * @return the user
	 */
	User create(User form);

	/**
	 * Update password.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the user
	 */
	User updatePassword(String userName, String password);

	/**
	 * Gets the user by email or user name.
	 *
	 * @param email the email
	 * @param userName the user name
	 * @return the user by email or user name
	 * @throws NonUniqueResultException the non unique result exception
	 */
	List<User> getUserByEmailOrUserName(String email, String userName) throws NonUniqueResultException;

	/**
	 * Save or update user.
	 *
	 * @param party the party
	 * @return the party
	 */
	Party saveOrUpdateUser(Party party);
	
	/**
	 * Gets the connection suggestion list.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the connection suggestion list
	 */
	PaginatedSearchResponseModel getConnectionSuggestionList(User volunteer, int page, int size);
	
	/**
	 * Gets the user party details.
	 *
	 * @param user the user
	 * @return the user party details
	 */
	Party getUserPartyDetails(User user);

	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return the user
	 */
	User saveUser(User user);

	/**
	 * Update modeified date.
	 *
	 * @param userID the user ID
	 */
	void updateModeifiedDate(long userID);

	/**
	 * Find users by name.
	 *
	 * @param name the name
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findUsersByName(String name, int page, int size);


}
