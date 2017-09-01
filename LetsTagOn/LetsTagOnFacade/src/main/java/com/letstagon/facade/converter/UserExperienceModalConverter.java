package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserExperience;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserExperienceDTO;
// TODO: Auto-generated Javadoc

/**
 * The Class UserExperienceModalConverter.
 */
@Component
public class UserExperienceModalConverter implements Converter<UserExperience, UserExperienceDTO> {
	
	/** The user modal converter. */
	@Autowired
	private Converter<User, UserDTO> userModalConverter;
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public UserExperienceDTO convert(UserExperience source) {
		UserExperienceDTO userExperienceDTO = new UserExperienceDTO();
		userExperienceDTO.setId(source.getId());
		userExperienceDTO.setCause(source.getCause());
		userExperienceDTO.setCourse(source.getCourse());
		userExperienceDTO.setDegree(source.getDegree());
		userExperienceDTO.setDescription(source.getDescription());
		userExperienceDTO.setLocation(source.getLocation());
		userExperienceDTO.setStartDate(source.getStartDate());
		userExperienceDTO.setEndDate(source.getEndDate());
		userExperienceDTO.setOrganizationName(source.getOrganizationName());
		userExperienceDTO.setUserBean(userModalConverter.convert(source.getUserBean()));
		userExperienceDTO.setTitle(source.getTitle());
		userExperienceDTO.setType(source.getType());
		return userExperienceDTO;
	}

}
