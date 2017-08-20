package com.letstagon.service;

import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.exception.profile.InvalidPreferenceException;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserPrivacySettingsService.
 */
public interface UserPrivacySettingsService {
	
	/**
	 * Save or update user privacy.
	 *
	 * @param privacySettings the privacy settings
	 * @return the privacy settings
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	PrivacySettings saveOrUpdateUserPrivacy(PrivacySettings privacySettings) throws InvalidPreferenceException;

	/**
	 * Gets the user privacy settings.
	 *
	 * @param id the id
	 * @return the user privacy settings
	 */
	PrivacySettings getUserPrivacySettings(long id);
}
