package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityInvite;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.OpportunityInviteFacade;
import com.letstagon.facade.converter.PartyModelConverter;
import com.letstagon.facade.dto.InviteOpportunitySuggestionListDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityInviteDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.service.MessageService;
import com.letstagon.service.OpportunityInviteService;
import com.letstagon.service.PartyService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityInviteFacadeImpl.
 */
@Component
public class OpportunityInviteFacadeImpl implements OpportunityInviteFacade {

	/** The opportunity invite service. */
	@Autowired
	private OpportunityInviteService opportunityInviteService;

	/** The opp invite converter. */
	@Autowired
	private Converter<OpportunityInvite, OpportunityInviteDTO> oppInviteConverter;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The messaging service. */
	@Autowired
	private MessageService messagingService;
	
	/** The party model converter. */
	@Autowired
	private PartyModelConverter partyModelConverter;
	
	/** The party service. */
	@Autowired
	private PartyService partyService;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityInviteFacade#inviteToOpportunity(com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.OpportunityDTO)
	 */
	@Override
	public OpportunityInviteDTO inviteToOpportunity(PartyDTO invitingParty, PartyDTO inviteParty,
			OpportunityDTO opportunity) {
		Party invitingPartyModel = mapper.map(invitingParty, Party.class);
		Party invitePartyModel = mapper.map(inviteParty, Party.class);
		Opportunity opportunityModel = mapper.map(opportunity, Opportunity.class);

		OpportunityInvite invite = opportunityInviteService.inviteToOpportunity(invitingPartyModel, invitePartyModel,
				opportunityModel);
		return this.oppInviteConverter.convert(invite);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityInviteFacade#markInvitationAsAccepted(com.letstagon.facade.dto.OpportunityInviteDTO)
	 */
	@Override
	public OpportunityInviteDTO markInvitationAsAccepted(OpportunityInviteDTO invite) {
		OpportunityInvite model = mapper.map(invite, OpportunityInvite.class);

		model = opportunityInviteService.markInvitationAsAccepted(model);
		return this.oppInviteConverter.convert(model);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityInviteFacade#markInvitationAsRejected(com.letstagon.facade.dto.OpportunityInviteDTO)
	 */
	@Override
	public OpportunityInviteDTO markInvitationAsRejected(OpportunityInviteDTO invite) {
		OpportunityInvite model = mapper.map(invite, OpportunityInvite.class);

		model = opportunityInviteService.markInvitationAsRejected(model);
		return this.oppInviteConverter.convert(model);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityInviteFacade#findAllByInvitedParty(com.letstagon.facade.dto.PartyDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByInvitedParty(PartyDTO invitedParty, Pageable page) {
		Party partyModel = mapper.map(invitedParty, Party.class);
		PaginatedSearchResponseModel result = this.opportunityInviteService.findAllByInvitedParty(partyModel, page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityInviteFacade#findAllByInvitedPartyAndAccepted(com.letstagon.facade.dto.PartyDTO, java.lang.Boolean, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByInvitedPartyAndAccepted(PartyDTO invitedParty, Boolean accepted,
			Pageable page) {
		Party partyModel = mapper.map(invitedParty, Party.class);
		PaginatedSearchResponseModel result = this.opportunityInviteService.findAllByInvitedPartyAndAccepted(partyModel,
				accepted, page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityInviteFacade#findAllByInvitedByParty(com.letstagon.facade.dto.PartyDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByInvitedByParty(PartyDTO invitedByParty, Pageable page) {
		Party partyModel = mapper.map(invitedByParty, Party.class);
		PaginatedSearchResponseModel result = this.opportunityInviteService.findAllByInvitedByParty(partyModel, page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/**
	 * Convert to DTO list.
	 *
	 * @param searchResult the search result
	 * @return the list
	 */
	private List<OpportunityInviteDTO> convertToDTOList(List<? extends Object> searchResult) {

		if (CollectionUtils.isEmpty(searchResult)) {
			return Collections.emptyList();
		}

		List<OpportunityInviteDTO> dtoList = new ArrayList<OpportunityInviteDTO>();

		for (Object model : searchResult) {
			dtoList.add(this.oppInviteConverter.convert((OpportunityInvite) model));
		}

		return dtoList;
	}

	/**
	 * Convert object list to party list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	public List<Party> convertObjectListToPartyList(List<? extends Object> modelList) {
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		ArrayList<Party> dtoList = new ArrayList<Party>();
		for (Object model : modelList) {
			Party party = (Party) model;
			dtoList.add(party);
		}

		return dtoList;

	}
	
	/**
	 * Convert party list to DTO list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	public List<PartyDTO> convertPartyListToDTOList(List<? extends Object> modelList) {
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		ArrayList<PartyDTO> dtoList = new ArrayList<PartyDTO>();
		for (Object model : modelList) {
			Party party = (Party) model;
			dtoList.add(this.partyModelConverter.convert(party));
		}

		return dtoList;

	}
	
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityInviteFacade#getVolunteerSuggestionList(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.PartyDTO, java.lang.String, int, int)
	 */
	@Override
	public InviteOpportunitySuggestionListDTO getVolunteerSuggestionList(
			OpportunityDTO opportunityDTO, PartyDTO invitedByParty,String name,int page,int size) throws InvalidPreferenceException {
		//party list
		//fetch all invite to opp-opp,invitedPartyList
		//create new dto by extending old one
		//set invited opp result set
		Pageable pageReq = new PageRequest(page, size);
		Opportunity opp = mapper.map(opportunityDTO, Opportunity.class);
		PaginatedSearchResponseModel result = partyService.findPartyListByName(name,page,size);	
		//List<Party> partyList = convertObjectListToPartyList(result.getSearchResult());
		PaginatedSearchResponseModel searchResult = opportunityInviteService.getInvitedVolunteerListForOpportunity(opp, (List<Party>) result.getSearchResult(), name, pageReq);
		return new InviteOpportunitySuggestionListDTO(pageReq.getPageNumber(), pageReq.getPageSize(), result.getTotalCount(),
				this.convertPartyListToDTOList(result.getSearchResult()),this.convertToDTOList(searchResult.getSearchResult()));
		
	}

}
