package com.letstagon.web.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.UserInterestFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.VolunteerLocationPrefDTO;
import com.letstagon.facade.dto.VolunteerPrefDTO;
import com.letstagon.web.controller.ControllerConstants;

// TODO: Auto-generated Javadoc
/**
 * Rest Controller to support operations pertaining to volunteer preference from
 * Availability perspective.
 *
 * @author Thoughtclan
 */
@RestController(value = "/profile/availability")
public class AvailabilityRestController {

	/** The volunteer preference facade. */
	@Autowired
	private UserInterestFacade volunteerPreferenceFacade;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AvailabilityRestController.class);

	/**
	 * Save or update user availability.
	 *
	 * @param userId the user id
	 * @param preference the preference
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/availability/user/{userId}/add", method = RequestMethod.POST)
	public AjaxResponseDTO saveOrUpdateUserAvailability(@PathVariable(value = "userId") long userId,
			@RequestBody VolunteerPrefDTO preference) {
		LOG.info("in save or update user availablity details:-" + userId);
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			VolunteerPrefDTO volunteerPrefDTO = volunteerPreferenceFacade.addAvailabilityEntry(userId, preference);
			responseDTO.setData(volunteerPrefDTO);
		} catch (InvalidPreferenceException e) {
			LOG.error("InvalidPreferenceException : Some issue in save or update user availablity details:-" + userId);
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.INTERNAL_ERROR);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.SAVE_ENTITY_ERROR);
			responseDTO.setError(errorDTO);
			LOG.info("getUserCauseSelectedDetails exception:" + e.getLocalizedMessage());
		}
		return responseDTO;
	}

	/**
	 * Edits the availability entry.
	 *
	 * @param preference the preference
	 * @return the volunteer pref DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	@RequestMapping(value = "/profile/availability/edit", method = RequestMethod.POST)
	public VolunteerPrefDTO editAvailabilityEntry(VolunteerPrefDTO preference) throws InvalidPreferenceException {
		return volunteerPreferenceFacade.editAvailabilityEntry(preference);
	}

	/**
	 * Removes the availability entry.
	 *
	 * @param prefId the pref id
	 * @return true, if successful
	 */
	@RequestMapping(value = "/profile/availability/{prefId}", method = RequestMethod.DELETE)
	public boolean removeAvailabilityEntry(@PathVariable long prefId) {

		VolunteerPrefDTO preference = new VolunteerPrefDTO();
		preference.setId(prefId);
		return volunteerPreferenceFacade.removeAvailabilityEntry(preference);

	}

	/**
	 * Find by user.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@RequestMapping(value = "/profile/availability/user/{userId}", method = RequestMethod.GET)
	public List<VolunteerPrefDTO> findByUser(@PathVariable(value = "userId") long userId) {

		UserDTO user = new UserDTO();
		user.setId(userId);

		return volunteerPreferenceFacade.findByUser(user);
	}
	
	/**
	 * Save or update volunteer location pref.
	 *
	 * @param userId the user id
	 * @param locationPrefDTO the location pref DTO
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/availability/location/user/{userId}/preferences", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO saveOrUpdateVolunteerLocationPref(@PathVariable(value="userId") long userId,@RequestBody VolunteerLocationPrefDTO locationPrefDTO) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			VolunteerLocationPrefDTO dto = volunteerPreferenceFacade.saveOrUpdateVolunteerLocationPref(userId,locationPrefDTO);
			responseDTO.setData(dto);
		} catch (InvalidPreferenceException e) {
			LOG.error("InvalidPreferenceException : Some issue in save or update user loacation details:-");
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.INTERNAL_ERROR);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.SAVE_ENTITY_ERROR);
			responseDTO.setError(errorDTO);
			LOG.error("getUserCauseSelectedDetails exception:" + e.getLocalizedMessage());
		}
		return responseDTO;
	}

}
