package com.letstagon.facade.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Organization;
import com.letstagon.facade.dto.OrganizationDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OrganizationModelConverter.
 */
@Component
public class OrganizationModelConverter implements Converter<Organization, OrganizationDTO> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OrganizationDTO convert(Organization model) {
		if (model == null)
			return null;

		OrganizationDTO dto = new OrganizationDTO();

		dto.setName(model.getName());
		dto.setId(model.getId());
		dto.setEmailAddress(model.getEmailAddress());
		dto.setOrgType(model.getOrgType());

		return dto;
	}

}
