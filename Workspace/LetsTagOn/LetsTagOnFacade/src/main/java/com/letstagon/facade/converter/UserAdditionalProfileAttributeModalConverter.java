package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;
import com.letstagon.facade.dto.AdditionalProfileAttributeDTO;
import com.letstagon.facade.dto.UserAdditionalProfileAttributeDTO;
// TODO: Auto-generated Javadoc

/**
 * The Class UserAdditionalProfileAttributeModalConverter.
 */
@Component
public class UserAdditionalProfileAttributeModalConverter implements Converter<UserAdditionalProfileAttribute, UserAdditionalProfileAttributeDTO>{
	
	/** The additional profile attribute converter. */
	@Autowired
	private Converter<AdditionalProfileAttribute, AdditionalProfileAttributeDTO> additionalProfileAttributeConverter;
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public UserAdditionalProfileAttributeDTO convert(
			UserAdditionalProfileAttribute source) {
		UserAdditionalProfileAttributeDTO additionalProfileAttributeDTO = new UserAdditionalProfileAttributeDTO();
		additionalProfileAttributeDTO.setId(source.getId());
		additionalProfileAttributeDTO.setValue(source.getValue());
		AdditionalProfileAttributeDTO attributeDTO = additionalProfileAttributeConverter.convert(source.getAdditionalProfileAttribute());
		additionalProfileAttributeDTO.setAdditionalProfileAttribute(attributeDTO);
		return additionalProfileAttributeDTO;
	}

}
