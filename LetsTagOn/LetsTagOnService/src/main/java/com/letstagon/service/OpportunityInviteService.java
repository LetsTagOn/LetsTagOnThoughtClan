package com.letstagon.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityInvite;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityInviteService.
 */
public interface OpportunityInviteService {

	/**
	 * Invite to opportunity.
	 *
	 * @param invitingParty the inviting party
	 * @param inviteParty the invite party
	 * @param opportunity the opportunity
	 * @return the opportunity invite
	 */
	OpportunityInvite inviteToOpportunity(Party invitingParty, Party inviteParty, Opportunity opportunity);

	/**
	 * Mark invitation as accepted.
	 *
	 * @param invite the invite
	 * @return the opportunity invite
	 */
	OpportunityInvite markInvitationAsAccepted(OpportunityInvite invite);

	/**
	 * Mark invitation as rejected.
	 *
	 * @param invite the invite
	 * @return the opportunity invite
	 */
	OpportunityInvite markInvitationAsRejected(OpportunityInvite invite);

	/**
	 * Find all by invited party.
	 *
	 * @param invitedParty the invited party
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByInvitedParty(Party invitedParty, Pageable page);

	/**
	 * Find all by invited party and accepted.
	 *
	 * @param invitedParty the invited party
	 * @param accepted the accepted
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByInvitedPartyAndAccepted(Party invitedParty, Boolean accepted, Pageable page);

	/**
	 * Find all by invited by party.
	 *
	 * @param invitedByParty the invited by party
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByInvitedByParty(Party invitedByParty, Pageable page);

	/**
	 * Gets the invited volunteer list for opportunity.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param invitedParties the invited parties
	 * @param name the name
	 * @param page the page
	 * @return the invited volunteer list for opportunity
	 */
	PaginatedSearchResponseModel getInvitedVolunteerListForOpportunity(
			Opportunity opportunityBean, List<Party> invitedParties,
			String name, Pageable page);

}
