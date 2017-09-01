package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.PrivacySettingsDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class PrivacySettingsModalConverter.
 */
@Component
public class PrivacySettingsModalConverter implements Converter<PrivacySettings, PrivacySettingsDTO> {

	/** The user model converter. */
	@Autowired
	private Converter<User, UserDTO> userModelConverter;
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public PrivacySettingsDTO convert(PrivacySettings source) {
		if(source == null){
			return null;
		}
		PrivacySettingsDTO privacySettingsDTO = new PrivacySettingsDTO();
		privacySettingsDTO.setEmailAlertsOn(source.getEmailAlertsOn());
		privacySettingsDTO.setEmailNotificationFrequency(source.getEmailNotificationFrequency());
		privacySettingsDTO.setId(source.getId());
		privacySettingsDTO.setMobileNumberVisibility(source.getMobileNumberVisibility());
		privacySettingsDTO.setProfileDetailsVisibility(source.getProfileDetailsVisibility());
		privacySettingsDTO.setUser(userModelConverter.convert(source.getUser()));
		return privacySettingsDTO;
	}

}
