package com.letstagon.service;

import java.util.HashMap;

import com.letstagon.dao.model.UserExperience;
// TODO: Auto-generated Javadoc

/**
 * The Interface UserExperienceService.
 */
/* 
 * Service for handling user experience details 
 * @author ThoughtClan
 *
 */
public interface UserExperienceService {
	
	/**
	 * Save volunteer experience of user.
	 *
	 * @param user the user
	 * @return the user experience
	 */
	UserExperience saveVolunteerExperienceOfUser(UserExperience user);

	/**
	 * Gets the user experience list.
	 *
	 * @param id the id
	 * @return the user experience list
	 */
	HashMap<String, Object> getUserExperienceList(long id);
}
