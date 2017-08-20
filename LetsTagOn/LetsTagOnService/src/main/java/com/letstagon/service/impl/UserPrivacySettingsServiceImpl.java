package com.letstagon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.PrivacySettingsRepository;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.service.UserPrivacySettingsService;
import com.letstagon.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserPrivacySettingsServiceImpl.
 */
@Component
public class UserPrivacySettingsServiceImpl implements UserPrivacySettingsService {
	
	/** The privacy settings repository. */
	@Autowired
	private PrivacySettingsRepository privacySettingsRepository;

	/** The user service. */
	@Autowired
	private UserService userService;

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserPrivacySettingsService#saveOrUpdateUserPrivacy(com.letstagon.dao.model.PrivacySettings)
	 */
	@Override
	public PrivacySettings saveOrUpdateUserPrivacy(PrivacySettings privacySettings) throws InvalidPreferenceException {
		if (privacySettings.getUser() == null) {
			throw new InvalidPreferenceException("User not found");
		}
		PrivacySettings privacy = privacySettingsRepository.findByUser(privacySettings.getUser());
		if (privacy != null) {
			privacySettings.setId(privacy.getId());
		}
		PrivacySettings settings = privacySettingsRepository.save(privacySettings);

		if (settings != null && settings.getUser() != null) {
			userService.updateModeifiedDate(settings.getUser().getId());
		}

		return settings;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserPrivacySettingsService#getUserPrivacySettings(long)
	 */
	@Override
	public PrivacySettings getUserPrivacySettings(long id) {
		User user = new User();
		user.setId(id);
		PrivacySettings privacySettings = privacySettingsRepository.findByUser(user);
		return privacySettings;
	}

}
