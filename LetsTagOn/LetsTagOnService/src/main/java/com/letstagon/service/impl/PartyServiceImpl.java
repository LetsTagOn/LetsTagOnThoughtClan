package com.letstagon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Organization;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.service.PartyService;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyServiceImpl.
 */
@Component
public class PartyServiceImpl implements PartyService {

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyService#findByUserBean(com.letstagon.dao.model.User)
	 */
	@Override
	public Party findByUserBean(User user) {
		return partyRepository.findByUserBean(user);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyService#findByOrganizationBean(com.letstagon.dao.model.Organization)
	 */
	@Override
	public Party findByOrganizationBean(Organization org) {
		return partyRepository.findByOrganizationBean(org);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyService#findParty(com.letstagon.dao.model.Party)
	 */
	@Override
	public Party findParty(Party party) {

		if (party == null) {
			return null;
		}

		if (party.getUserBean() != null && party.getUserBean().getId() >= 1) {
			return this.findByUserBean(party.getUserBean());
		} else if (party.getOrganizationBean() != null && party.getOrganizationBean().getId() >= 1) {
			return this.findByOrganizationBean(party.getOrganizationBean());
		}

		return null;

	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyService#findPartyListByName(java.lang.String, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel findPartyListByName(String name,int page,int size){
		Pageable pageReq = new PageRequest(page, size);
		Page<Party> resultList = partyRepository.findAllUsersByName(name, pageReq);
		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}

}
