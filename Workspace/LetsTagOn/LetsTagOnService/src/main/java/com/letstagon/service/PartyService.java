package com.letstagon.service;

import com.letstagon.dao.model.Organization;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyService.
 */
public interface PartyService {

	/**
	 * Find by user bean.
	 *
	 * @param user the user
	 * @return the party
	 */
	Party findByUserBean(User user);

	/**
	 * Find by organization bean.
	 *
	 * @param org the org
	 * @return the party
	 */
	Party findByOrganizationBean(Organization org);

	/**
	 * Find party.
	 *
	 * @param party the party
	 * @return the party
	 */
	Party findParty(Party party);

	/**
	 * Find party list by name.
	 *
	 * @param name the name
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findPartyListByName(String name, int page,
			int size);

}
