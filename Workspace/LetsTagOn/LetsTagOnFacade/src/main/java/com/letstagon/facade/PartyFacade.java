package com.letstagon.facade;

import com.letstagon.facade.dto.OrganizationDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyFacade.
 */
public interface PartyFacade {

	/**
	 * Find by user bean.
	 *
	 * @param user the user
	 * @return the party DTO
	 */
	PartyDTO findByUserBean(UserDTO user);

	/**
	 * Find by organization bean.
	 *
	 * @param org the org
	 * @return the party DTO
	 */
	PartyDTO findByOrganizationBean(OrganizationDTO org);

	/**
	 * Find party DTO.
	 *
	 * @param party the party
	 * @return the party DTO
	 */
	PartyDTO findPartyDTO(PartyDTO party);

}