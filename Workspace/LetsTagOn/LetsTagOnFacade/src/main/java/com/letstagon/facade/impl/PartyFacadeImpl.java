package com.letstagon.facade.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Organization;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.facade.PartyFacade;
import com.letstagon.facade.dto.OrganizationDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.service.PartyService;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyFacadeImpl.
 */
@Component
public class PartyFacadeImpl implements PartyFacade {

	/** The party service. */
	@Autowired
	private PartyService partyService;

	/** The party model converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyModelConverter;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyFacade#findByUserBean(com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public PartyDTO findByUserBean(UserDTO user) {

		User userModel = this.mapper.map(user, User.class);

		Party party = this.partyService.findByUserBean(userModel);

		return this.partyModelConverter.convert(party);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyFacade#findByOrganizationBean(com.letstagon.facade.dto.OrganizationDTO)
	 */
	@Override
	public PartyDTO findByOrganizationBean(OrganizationDTO org) {

		Organization orgModel = this.mapper.map(org, Organization.class);

		Party party = this.partyService.findByOrganizationBean(orgModel);

		return this.partyModelConverter.convert(party);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyFacade#findPartyDTO(com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public PartyDTO findPartyDTO(PartyDTO party) {

		Party partyModel = this.mapper.map(party, Party.class);

		partyModel = this.partyService.findParty(partyModel);

		return this.partyModelConverter.convert(partyModel);
	}

}
