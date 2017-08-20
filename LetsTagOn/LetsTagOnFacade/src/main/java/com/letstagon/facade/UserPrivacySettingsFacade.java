package com.letstagon.facade;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.dto.PrivacySettingsDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserPrivacySettingsFacade.
 */
public interface UserPrivacySettingsFacade {
	
	/**
	 * Save or update user privacy.
	 *
	 * @param privacySettingsDTO the privacy settings DTO
	 * @return the privacy settings DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	PrivacySettingsDTO saveOrUpdateUserPrivacy(PrivacySettingsDTO privacySettingsDTO) throws InvalidPreferenceException;
	
	/**
	 * Gets the user privacy settings.
	 *
	 * @param userId the user id
	 * @return the user privacy settings
	 */
	PrivacySettingsDTO getUserPrivacySettings(long userId);
}
