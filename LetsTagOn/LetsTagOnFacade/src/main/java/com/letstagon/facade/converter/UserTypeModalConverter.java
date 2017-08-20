package com.letstagon.facade.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.UserType;
import com.letstagon.facade.dto.UserTypeDTO;
// TODO: Auto-generated Javadoc

/**
 * The Class UserTypeModalConverter.
 */
@Component
public class UserTypeModalConverter implements Converter<UserType, UserTypeDTO> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public UserTypeDTO convert(UserType source) {
		UserTypeDTO typeDTO = new UserTypeDTO();
		typeDTO.setId(source.getId());
		typeDTO.setDescription(source.getDescription());
		typeDTO.setName(source.getName());
		return typeDTO;
	}

}
