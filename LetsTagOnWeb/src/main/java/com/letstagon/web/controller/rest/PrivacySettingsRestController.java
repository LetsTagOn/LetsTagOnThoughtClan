package com.letstagon.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.UserPrivacySettingsFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.PrivacySettingsDTO;
import com.letstagon.web.controller.ControllerConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class PrivacySettingsRestController.
 */
@RestController(value = "/privacy")
public class PrivacySettingsRestController {
	
	/** The user privacy settings facade. */
	@Autowired
	private UserPrivacySettingsFacade userPrivacySettingsFacade;

	/**
	 * Capture user experience details.
	 *
	 * @param details the details
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/privacy/user/saveOrUpdate/settings", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO captureUserExperienceDetails(@RequestBody PrivacySettingsDTO details) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			PrivacySettingsDTO settingsDTO = userPrivacySettingsFacade.saveOrUpdateUserPrivacy(details);
			responseDTO.setData(settingsDTO);
		} catch (InvalidPreferenceException de) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.RESOURCE_NOT_FOUND);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return responseDTO;

	}

	/**
	 * Gets the volunteer interest details.
	 *
	 * @param userId the user id
	 * @return the volunteer interest details
	 */
	@RequestMapping(value = "/privacy/user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponseDTO getVolunteerInterestDetails(@PathVariable long userId) {

		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
			PrivacySettingsDTO settingsDTO = userPrivacySettingsFacade.getUserPrivacySettings(userId);
			responseDTO.setData(settingsDTO);
		
		return responseDTO;
	}
}
