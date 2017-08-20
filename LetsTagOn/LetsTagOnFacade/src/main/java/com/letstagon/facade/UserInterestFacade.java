package com.letstagon.facade;

import java.util.HashMap;
import java.util.List;

import com.letstagon.exception.NonFatalException;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.PartyCauseXrefDTO;
import com.letstagon.facade.dto.PartyJobTypeXrefDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserTypeXrefDTO;
import com.letstagon.facade.dto.VolunteerLocationPrefDTO;
import com.letstagon.facade.dto.VolunteerPrefDTO;

// TODO: Auto-generated Javadoc
/**
 * Facade layer class to manage volunteer preferences of a Volunteer.
 * 
 * @author ThoughtClan
 *
 */
public interface UserInterestFacade {

	/**
	 * Adds the availability entry.
	 *
	 * @param userId the user id
	 * @param preference the preference
	 * @return the volunteer pref DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public VolunteerPrefDTO addAvailabilityEntry(long userId, VolunteerPrefDTO preference)
			throws InvalidPreferenceException;

	/**
	 * Edits the availability entry.
	 *
	 * @param preference the preference
	 * @return the volunteer pref DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public VolunteerPrefDTO editAvailabilityEntry(VolunteerPrefDTO preference) throws InvalidPreferenceException;

	/**
	 * Removes the availability entry.
	 *
	 * @param preference the preference
	 * @return true, if successful
	 */
	public boolean removeAvailabilityEntry(VolunteerPrefDTO preference);

	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<VolunteerPrefDTO> findByUser(UserDTO user);

	/**
	 * Save or update volunteer location pref.
	 *
	 * @param userId the user id
	 * @param prefDTO the pref DTO
	 * @return the volunteer location pref DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	VolunteerLocationPrefDTO saveOrUpdateVolunteerLocationPref(long userId, VolunteerLocationPrefDTO prefDTO)
			throws InvalidPreferenceException;

	/**
	 * Gets the volunteer interest details.
	 *
	 * @param id the id
	 * @return the volunteer interest details
	 */
	public HashMap<String, Object> getVolunteerInterestDetails(long id);

	/**
	 * Gets the cause list.
	 *
	 * @param id the id
	 * @return the cause list
	 */
	HashMap<String, Object> getCauseList(long id);

	/**
	 * Save causes of user.
	 *
	 * @param causeXrefDTOs the cause xref DT os
	 * @return the list
	 */
	List<CauseDTO> saveCausesOfUser(List<PartyCauseXrefDTO> causeXrefDTOs);

	/**
	 * Save skills of user.
	 *
	 * @param jobTypeXrefs the job type xrefs
	 * @return the list
	 */
	List<JobTypeDTO> saveSkillsOfUser(List<PartyJobTypeXrefDTO> jobTypeXrefs);

	/**
	 * Gets the skills list.
	 *
	 * @param id the id
	 * @return the skills list
	 */
	public HashMap<String, Object> getSkillsList(long id);
	
	/**
	 * Save or update user type.
	 *
	 * @param user the user
	 * @param userId the user id
	 * @return the hash map
	 * @throws NonFatalException the non fatal exception
	 */
	public HashMap<String, Object> saveOrUpdateUserType(UserTypeXrefDTO user, long userId) throws NonFatalException;

	/**
	 * Gets the master cause list.
	 *
	 * @return the master cause list
	 */
	List<CauseDTO> getMasterCauseList();

	/**
	 * Gets the master skill list.
	 *
	 * @return the master skill list
	 */
	List<JobTypeDTO> getMasterSkillList();
}
