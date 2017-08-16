package com.letstagon.facade.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.facade.dto.AdditionalProfileAttributeDTO;
// TODO: Auto-generated Javadoc

/**
 * The Class AdditionProfileAttributeModalConverter.
 */
@Component
public class AdditionProfileAttributeModalConverter implements Converter<AdditionalProfileAttribute, AdditionalProfileAttributeDTO>{

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public AdditionalProfileAttributeDTO convert(
			AdditionalProfileAttribute source) {
		AdditionalProfileAttributeDTO attributeDTO = new AdditionalProfileAttributeDTO();
		attributeDTO
				.setDescription(source.getDescription());
		attributeDTO.setName(source.getName());
		attributeDTO.setId(source.getId());
		return attributeDTO;
	}

}
