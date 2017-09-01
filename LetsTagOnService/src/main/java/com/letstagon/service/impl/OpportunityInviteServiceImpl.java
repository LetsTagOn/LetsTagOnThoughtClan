package com.letstagon.service.impl;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityInvite;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.repository.OpportunityInviteRepository;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.service.OpportunityInviteService;
import com.letstagon.service.event.ConnectionAcceptedEvent;
import com.letstagon.service.event.InviteToOppEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityInviteServiceImpl.
 */
@Component
public class OpportunityInviteServiceImpl implements OpportunityInviteService {

	/** The opportunity invite repository. */
	@Autowired
	private OpportunityInviteRepository opportunityInviteRepository;
	
	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;
	
	/** The publisher. */
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityInviteService#inviteToOpportunity(com.letstagon.dao.model.Party, com.letstagon.dao.model.Party, com.letstagon.dao.model.Opportunity)
	 */
	@Override
	public OpportunityInvite inviteToOpportunity(Party invitingParty, Party inviteParty, Opportunity opportunity) {

		OpportunityInvite invite = this.opportunityInviteRepository
				.findOneByInvitedByPartyAndInvitedPartyAndOpportunityBean(invitingParty, inviteParty, opportunity);

		if (invite == null) {
			invite = new OpportunityInvite(inviteParty, invitingParty, opportunity);
		}

		invite =  this.opportunityInviteRepository.save(invite);
		
		if(inviteParty.getUserBean() ==  null){
			inviteParty = partyRepository.findOne(inviteParty.getId());
		}
		if(invitingParty.getUserBean() == null){
			invitingParty = partyRepository.findOne(invitingParty.getId());
		}
		
		InviteToOppEvent inviteToOppEvent = new InviteToOppEvent(invite,opportunity,
				invitingParty, inviteParty, new Date());
		publisher.publishEvent(inviteToOppEvent);
		
		return invite;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityInviteService#markInvitationAsAccepted(com.letstagon.dao.model.OpportunityInvite)
	 */
	@Override
	public OpportunityInvite markInvitationAsAccepted(OpportunityInvite invite) {

		invite = this.opportunityInviteRepository.findOne(invite.getId());

		if (invite == null) {
			throw new InvalidParameterException("Invalid invite");
		}
		invite.setAccepted(Boolean.TRUE);
		invite.setInviteAcceptedDate(new Date());

		return this.opportunityInviteRepository.save(invite);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityInviteService#markInvitationAsRejected(com.letstagon.dao.model.OpportunityInvite)
	 */
	@Override
	public OpportunityInvite markInvitationAsRejected(OpportunityInvite invite) {

		invite = this.opportunityInviteRepository.findOne(invite.getId());

		if (invite == null) {
			throw new InvalidParameterException("Invalid invite");
		}
		invite.setAccepted(Boolean.FALSE);
		invite.setInviteAcceptedDate(new Date());

		return this.opportunityInviteRepository.save(invite);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityInviteService#findAllByInvitedParty(com.letstagon.dao.model.Party, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByInvitedParty(Party invitedParty, Pageable page) {

		Page<OpportunityInvite> result = this.opportunityInviteRepository.findAllByInvitedParty(invitedParty, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityInviteService#findAllByInvitedPartyAndAccepted(com.letstagon.dao.model.Party, java.lang.Boolean, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByInvitedPartyAndAccepted(Party invitedParty, Boolean accepted,
			Pageable page) {

		Page<OpportunityInvite> result = this.opportunityInviteRepository.findAllByInvitedPartyAndAccepted(invitedParty,
				accepted, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityInviteService#findAllByInvitedByParty(com.letstagon.dao.model.Party, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByInvitedByParty(Party invitedByParty, Pageable page) {

		Page<OpportunityInvite> result = this.opportunityInviteRepository.findAllByInvitedByParty(invitedByParty, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityInviteService#getInvitedVolunteerListForOpportunity(com.letstagon.dao.model.Opportunity, java.util.List, java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel getInvitedVolunteerListForOpportunity(
			Opportunity opportunityBean,List<Party> invitedParties, String name,Pageable page) {
		
		Page<OpportunityInvite> result = opportunityInviteRepository.findAllByOpportunityBeanAndInvitedPartyList(opportunityBean, invitedParties, page);
		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());
	}

}
