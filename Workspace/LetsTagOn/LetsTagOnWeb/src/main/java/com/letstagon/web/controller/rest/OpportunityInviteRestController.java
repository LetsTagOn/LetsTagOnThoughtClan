package com.letstagon.web.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.OpportunityInviteFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityInviteDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.controller.ControllerConstants;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityInviteRestController.
 */
@RestController
public class OpportunityInviteRestController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OpportunityInviteRestController.class);

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/** The opportunity invite facade. */
	@Autowired
	private OpportunityInviteFacade opportunityInviteFacade;

	/**
	 * Invite to opportunity.
	 *
	 * @param invitingParty the inviting party
	 * @param inviteParty the invite party
	 * @param opportunity the opportunity
	 * @return the opportunity invite DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunity}/invitedBy/{invitingParty}/invite/{inviteParty}", method = RequestMethod.POST)
	public OpportunityInviteDTO inviteToOpportunity(@PathVariable(value = "invitingParty") long invitingParty,
			@PathVariable(value = "inviteParty") long inviteParty,
			@PathVariable(value = "opportunity") long opportunity) {

		LOG.trace("Inviting party : " + inviteParty + " , to opportunity : " + opportunity);
		OpportunityInviteDTO invite = opportunityInviteFacade.inviteToOpportunity(
				this.ltoSessionService.findLoggedInParty(0), new PartyDTO(inviteParty),
				new OpportunityDTO(opportunity));

		return invite;
	}

	/**
	 * Mark invitation as accepted.
	 *
	 * @param inviteID the invite ID
	 * @return the opportunity invite DTO
	 */
	@RequestMapping(value = "/opportunity/invitation/{inviteID}/markAccepted", method = RequestMethod.PUT)
	public OpportunityInviteDTO markInvitationAsAccepted(@PathVariable(value = "inviteID") long inviteID) {

		OpportunityInviteDTO invite = opportunityInviteFacade
				.markInvitationAsAccepted(new OpportunityInviteDTO(inviteID));
		return invite;
	}

	/**
	 * Mark invitation as rejected.
	 *
	 * @param inviteID the invite ID
	 * @return the opportunity invite DTO
	 */
	@RequestMapping(value = "/opportunity/invitation/{inviteID}/markAccepted", method = RequestMethod.DELETE)
	public OpportunityInviteDTO markInvitationAsRejected(@PathVariable(value = "inviteID") long inviteID) {

		OpportunityInviteDTO invite = opportunityInviteFacade
				.markInvitationAsRejected(new OpportunityInviteDTO(inviteID));
		return invite;
	}

	/**
	 * Find all by invited party.
	 *
	 * @param invitedParty the invited party
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/invitation/findAllByInvitedParty/{invitedParty}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByInvitedParty(@PathVariable(value = "invitedParty") long invitedParty,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PaginatedResponseDTO result = opportunityInviteFacade.findAllByInvitedParty(new PartyDTO(invitedParty),
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "invitedDate")));
		return result;
	}

	/**
	 * Find all by invited party and accepted.
	 *
	 * @param invitedParty the invited party
	 * @param accepted the accepted
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/invitation/findAllByInvitedPartyAndAccepted/{invitedParty}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByInvitedPartyAndAccepted(
			@PathVariable(value = "invitedParty") long invitedParty,
			@RequestParam(name = "accepted", required = false) Boolean accepted,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PaginatedResponseDTO result = opportunityInviteFacade.findAllByInvitedPartyAndAccepted(
				new PartyDTO(invitedParty), accepted,
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "invitedDate")));
		return result;
	}

	/**
	 * Find all by invited by party.
	 *
	 * @param invitedByParty the invited by party
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/invitation/findAllByInvitedParty/{invitedByParty}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByInvitedByParty(@PathVariable(value = "invitedByParty") long invitedByParty,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PaginatedResponseDTO result = opportunityInviteFacade.findAllByInvitedByParty(new PartyDTO(invitedByParty),
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "invitedDate")));
		return result;
	}
	
	/**
	 * Find party suggestion list.
	 *
	 * @param opportunityId the opportunity id
	 * @param name the name
	 * @param page the page
	 * @param size the size
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityId}/volunteerSuggesstionList", method = RequestMethod.GET)
	public AjaxResponseDTO findPartySuggestionList(@PathVariable long opportunityId,@RequestParam(name = "name", required = false) String name,@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {
		
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		PartyDTO fromParty = ltoSessionService.findLoggedInParty(0);
		OpportunityDTO opp = new OpportunityDTO(opportunityId);
		try {
			responseDTO.setData(opportunityInviteFacade.getVolunteerSuggestionList(opp,fromParty, name, page, size));
		} catch (InvalidPreferenceException e) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return responseDTO;

	}

}
