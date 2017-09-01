package com.letstagon.facade.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.UserPrivacySettingsFacade;
import com.letstagon.facade.converter.PrivacySettingsModalConverter;
import com.letstagon.facade.dto.PrivacySettingsDTO;
import com.letstagon.service.UserPrivacySettingsService;
// TODO: Auto-generated Javadoc

/**
 * The Class UserPrivacySettingsFacadeImpl.
 */
@Component
public class UserPrivacySettingsFacadeImpl implements UserPrivacySettingsFacade {

	/** The user privacy settings service. */
	@Autowired
	private UserPrivacySettingsService userPrivacySettingsService;
	
	/** The mapper. */
	@Autowired DozerBeanMapper mapper;
	
	
	/** The privacy modal converter. */
	@Autowired
	private PrivacySettingsModalConverter privacyModalConverter;
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserPrivacySettingsFacade#saveOrUpdateUserPrivacy(com.letstagon.facade.dto.PrivacySettingsDTO)
	 */
	@Override
	public PrivacySettingsDTO saveOrUpdateUserPrivacy(PrivacySettingsDTO privacySettingsDTO) throws InvalidPreferenceException {
		PrivacySettings settings = mapper.map(privacySettingsDTO, PrivacySettings.class);
		settings = userPrivacySettingsService.saveOrUpdateUserPrivacy(settings);
		PrivacySettingsDTO dto = mapper.map(settings, PrivacySettingsDTO.class);
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserPrivacySettingsFacade#getUserPrivacySettings(long)
	 */
	@Override
	public PrivacySettingsDTO getUserPrivacySettings(long id) {
		PrivacySettings settings = userPrivacySettingsService.getUserPrivacySettings(id);
		if(settings != null){
			PrivacySettingsDTO settingsDTO = privacyModalConverter.convert(settings);
			return settingsDTO;
		}
		return null;
	}

}
