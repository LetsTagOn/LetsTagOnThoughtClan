package com.letstagon.facade;

import org.springframework.data.domain.Pageable;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.dto.InviteOpportunitySuggestionListDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityInviteDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityInviteFacade.
 */
public interface OpportunityInviteFacade {

	/**
	 * Invite to opportunity.
	 *
	 * @param invitingParty the inviting party
	 * @param inviteParty the invite party
	 * @param opportunity the opportunity
	 * @return the opportunity invite DTO
	 */
	OpportunityInviteDTO inviteToOpportunity(PartyDTO invitingParty, PartyDTO inviteParty, OpportunityDTO opportunity);

	/**
	 * Mark invitation as accepted.
	 *
	 * @param invite the invite
	 * @return the opportunity invite DTO
	 */
	OpportunityInviteDTO markInvitationAsAccepted(OpportunityInviteDTO invite);

	/**
	 * Mark invitation as rejected.
	 *
	 * @param invite the invite
	 * @return the opportunity invite DTO
	 */
	OpportunityInviteDTO markInvitationAsRejected(OpportunityInviteDTO invite);

	/**
	 * Find all by invited party.
	 *
	 * @param invitedParty the invited party
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByInvitedParty(PartyDTO invitedParty, Pageable page);

	/**
	 * Find all by invited party and accepted.
	 *
	 * @param invitedParty the invited party
	 * @param accepted the accepted
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByInvitedPartyAndAccepted(PartyDTO invitedParty, Boolean accepted, Pageable page);

	/**
	 * Find all by invited by party.
	 *
	 * @param invitedByParty the invited by party
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByInvitedByParty(PartyDTO invitedByParty, Pageable page);

	/**
	 * Gets the volunteer suggestion list.
	 *
	 * @param opportunityDTO the opportunity DTO
	 * @param invitedByParty the invited by party
	 * @param name the name
	 * @param page the page
	 * @param size the size
	 * @return the volunteer suggestion list
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	InviteOpportunitySuggestionListDTO getVolunteerSuggestionList(
			OpportunityDTO opportunityDTO, PartyDTO invitedByParty,
			String name, int page, int size) throws InvalidPreferenceException;

}
