package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Organization;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.OrganizationDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyModelConverter.
 */
@Component
public class PartyModelConverter implements Converter<Party, PartyDTO> {

	/** The user model converter. */
	@Autowired
	private Converter<User, UserDTO> userModelConverter;

	/** The org converter. */
	@Autowired
	private Converter<Organization, OrganizationDTO> orgConverter;;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public PartyDTO convert(Party party) {

		if (party == null) {
			return null;
		}

		PartyDTO dto = new PartyDTO();

		dto.setId(party.getId());
		dto.setUserBean(this.userModelConverter.convert(party.getUserBean()));
		dto.setOrganizationBean(this.orgConverter.convert(party.getOrganizationBean()));
		dto.setRating(party.getRating());
		return dto;

	}

}
