package com.letstagon.service;

import java.util.List;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.PartyCauseXref;
import com.letstagon.dao.model.PartyJobTypeXref;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.model.UserTypeXref;
import com.letstagon.dao.model.VolunteerLocationPref;
import com.letstagon.dao.model.VolunteerPref;
import com.letstagon.exception.NonFatalException;
import com.letstagon.exception.profile.InvalidPreferenceException;

// TODO: Auto-generated Javadoc
/**
 * Service class for holding business logic to manage Volunteer availability and location preference.
 *
 * @author Thoughtclan
 */
public interface UserInterestsService {

	/**
	 * Adds the availability entry.
	 *
	 * @param preference the preference
	 * @return the volunteer pref
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public VolunteerPref addAvailabilityEntry(VolunteerPref preference) throws InvalidPreferenceException;

	/**
	 * Edits the availability entry.
	 *
	 * @param preference the preference
	 * @return the volunteer pref
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public VolunteerPref editAvailabilityEntry(VolunteerPref preference) throws InvalidPreferenceException;

	/**
	 * Removes the availability entry.
	 *
	 * @param preference the preference
	 * @return true, if successful
	 */
	public boolean removeAvailabilityEntry(VolunteerPref preference);
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<VolunteerPref> findByUser(User user);
	
	/**
	 * Save or update volunteer location pref.
	 *
	 * @param prefDTO the pref DTO
	 * @return the volunteer location pref
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	VolunteerLocationPref saveOrUpdateVolunteerLocationPref(VolunteerLocationPref prefDTO)
			throws InvalidPreferenceException;

	/**
	 * Gets the volunteer preferred location details.
	 *
	 * @param id the id
	 * @return the volunteer preferred location details
	 */
	VolunteerLocationPref getVolunteerPreferredLocationDetails(long id);
	

	/**
	 * Gets the cause list.
	 *
	 * @param id the id
	 * @return the cause list
	 */
	List<PartyCauseXref> getCauseList(long id);

	/**
	 * Save user cause details.
	 *
	 * @param causeXref the cause xref
	 * @return the party cause xref
	 */
	PartyCauseXref saveUserCauseDetails(PartyCauseXref causeXref);
	
	
	/**
	 * Save user skill.
	 *
	 * @param jobTypeXref the job type xref
	 * @return the party job type xref
	 */
	PartyJobTypeXref saveUserSkill(PartyJobTypeXref jobTypeXref);

	/**
	 * Gets the skill list.
	 *
	 * @param id the id
	 * @return the skill list
	 */
	List<PartyJobTypeXref> getSkillList(long id);
	
	/**
	 * Gets the user type master data.
	 *
	 * @param id the id
	 * @return the user type master data
	 */
	List<UserType> getUserTypeMasterData(long id);
	
	/**
	 * Gets the user type list.
	 *
	 * @param id the id
	 * @return the user type list
	 */
	List<UserTypeXref> getUserTypeList(long id);
	
	/**
	 * Save or update user type.
	 *
	 * @param user the user
	 * @return the user type xref
	 * @throws NonFatalException the non fatal exception
	 */
	public UserTypeXref saveOrUpdateUserType(UserTypeXref user) throws NonFatalException;

	/**
	 * Gets the master skill list.
	 *
	 * @return the master skill list
	 */
	Iterable<JobType> getMasterSkillList();

	/**
	 * Gets the master cause list.
	 *
	 * @return the master cause list
	 */
	Iterable<Cause> getMasterCauseList();

	/**
	 * Gets the user additional attributes for user type.
	 *
	 * @param userTypeXref the user type xref
	 * @return the user additional attributes for user type
	 */
	List<UserAdditionalProfileAttribute> getUserAdditionalAttributesForUserType(
			UserTypeXref userTypeXref);

	/**
	 * Gets the additional attributes for user type.
	 *
	 * @param user the user
	 * @return the additional attributes for user type
	 */
	List<AdditionalProfileAttribute> getAdditionalAttributesForUserType(
			User user);

}
