package com.letstagon.web.controller.rest;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.dao.model.User;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.OpportunityManagementFacade;
import com.letstagon.facade.PartyFacade;
import com.letstagon.facade.PartyParticipationFacade;
import com.letstagon.facade.UserInterestFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.OpportunityCauseXrefDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityJobTypeCauseDTO;
import com.letstagon.facade.dto.OpportunityJobTypeDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PartyParticipationDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.service.OpportunityService;
import com.letstagon.web.controller.ControllerConstants;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityManagementRestController.
 */
@RestController
public class OpportunityManagementRestController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OpportunityManagementRestController.class);

	/** The opportunity management facade. */
	@Autowired
	private OpportunityManagementFacade opportunityManagementFacade;

	/** The participation facade. */
	@Autowired
	private PartyParticipationFacade participationFacade;

	/** The party facade. */
	@Autowired
	private PartyFacade partyFacade;

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/** The user interest facade. */
	@Autowired
	private UserInterestFacade userInterestFacade;

	/** The opportunity service. */
	@Autowired
	private OpportunityService opportunityService;

	/**
	 * Gets the opportunity details.
	 *
	 * @param opportunityID the opportunity ID
	 * @return the opportunity details
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}", method = RequestMethod.GET)
	public AjaxResponseDTO getOpportunityDetails(@PathVariable(value = "opportunityID") long opportunityID) {

		if (opportunityID == 0) {
			throw new InvalidParameterException("Not a valid opportunity ID");
		}

		LOG.trace("Getting details of opp with id : " + opportunityID);

		return new AjaxResponseDTO(opportunityManagementFacade.getOpportunityDetails(opportunityID));
	}

	/**
	 * Gets the opportunity additional details.
	 *
	 * @param opportunityID the opportunity ID
	 * @return the opportunity additional details
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/userDetails", method = RequestMethod.GET)
	public AjaxResponseDTO getOpportunityAdditionalDetails(@PathVariable(value = "opportunityID") long opportunityID) {

		if (opportunityID == 0) {
			throw new InvalidParameterException("Not a valid opportunity ID");
		}

		// TODO-show allowed parties
		PartyDTO loggedInParty = this.findLoggedInParty(0);
		List<PartyParticipationDTO> participationsOfLoggedInUser = participationFacade
				.findPartyParticipationDTO(new OpportunityDTO(opportunityID), loggedInParty);

		if (loggedInParty == null) {
			return new AjaxResponseDTO(Collections.singletonMap("anonymousUser", Boolean.TRUE));
		}

		Map<String, Object> additionalDetails = new HashMap<String, Object>();
		OpportunityDTO opp = opportunityManagementFacade.getOpportunityDetails(opportunityID);

		additionalDetails.put("partyParticipations", participationsOfLoggedInUser);

		// TODO-check allowed parties
		// check if admin
		additionalDetails.put("isAdmin", opp.getCreatedBy().equals(loggedInParty));

		LOG.trace("Getting details of opp with id : " + opportunityID);

		return new AjaxResponseDTO(additionalDetails);
	}

	/**
	 * Gets the opportunities by user.
	 *
	 * @return the opportunities by user
	 */
	@RequestMapping(value = "/user/opportunity", method = RequestMethod.GET)
	public AjaxResponseDTO getOpportunitiesByUser() {

		LOG.trace("Getting All opportunities of logged in user.");

		// TODO-allow other parties in future
		PartyDTO loggedInParty = this.findLoggedInParty(0);
		if (loggedInParty == null) {
			throw new IllegalStateException("Cannot get opportunities, user needs to login.");
		}

		AjaxResponseDTO resp = new AjaxResponseDTO();
		resp.setData(opportunityManagementFacade.getOpportunitiesCreatedByParty(loggedInParty));

		return resp;
	}

	/**
	 * Creates the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity", method = RequestMethod.POST)
	public AjaxResponseDTO createOpportunity(@RequestBody OpportunityDTO opportunity) {

		LOG.trace("Creating opp : " + opportunity);

		if (opportunity.getCreatedBy() == null) {
			opportunity.setCreatedBy(this.findLoggedInParty(0));
			if (this.ltoSessionService.getLoggedInUser() != null) {
				opportunity.setContactPerson(new UserDTO(this.ltoSessionService.getLoggedInUser().getId()));
			}
		} else {
			// TODO-validate that the party passed is valid-one of the
			// organizations the user has access to. To be done in future.
		}

		return new AjaxResponseDTO(this.opportunityManagementFacade.createOpportunity(opportunity));
	}

	/**
	 * Edits the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity", method = RequestMethod.PUT)
	public AjaxResponseDTO editOpportunity(@RequestBody OpportunityDTO opportunity) {

		LOG.trace("Editing opp : " + opportunity);
		/*
		 * if( opportunity.getOpportunityJobTypes() != null){
		 * List<OpportunityJobTypeDTO> listOpportunityJobTypeDTO =
		 * opportunity.getOpportunityJobTypes(); for (OpportunityJobTypeDTO
		 * opportunityJobTypeDTO : listOpportunityJobTypeDTO) {
		 * this.opportunityManagementFacade .mapRoleToOpportunity(new
		 * OpportunityDTO(opportunity.getId()), opportunityJobTypeDTO); } }
		 * 
		 * 
		 * 
		 */return new AjaxResponseDTO(this.opportunityManagementFacade.editOpportunity(opportunity));
	}

	/**
	 * Sets the contact person.
	 *
	 * @param opportunityID the opportunity ID
	 * @param userID the user ID
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/contactPerson/{userID}", method = RequestMethod.POST)
	public AjaxResponseDTO setContactPerson(@PathVariable(value = "opportunityID") long opportunityID,
			@PathVariable(value = "userID") long userID) {

		LOG.trace("Setting contact person for opp : " + opportunityID + " , user ID " + userID);

		return new AjaxResponseDTO(this.opportunityManagementFacade.setContactPerson(new OpportunityDTO(opportunityID),
				new UserDTO(userID)));
	}

	/**
	 * Adds the event to program.
	 *
	 * @param programID the program ID
	 * @param opportunity the opportunity
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/program/{programID}/event", method = RequestMethod.POST)
	public AjaxResponseDTO addEventToProgram(@PathVariable(value = "programID") long programID,
			@RequestBody OpportunityDTO opportunity) {

		opportunity.setParentProgram(new OpportunityDTO(programID));

		LOG.trace("Setting parent program : " + opportunity);

		return new AjaxResponseDTO(this.opportunityManagementFacade.addEventToProgram(opportunity));
	}

	/**
	 * Map role to opportunity.
	 *
	 * @param opportunityID the opportunity ID
	 * @param opportunityJobTypeDTO the opportunity job type DTO
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/jobType", method = RequestMethod.POST)
	public AjaxResponseDTO mapRoleToOpportunity(@PathVariable(value = "opportunityID") long opportunityID,
			@RequestBody OpportunityJobTypeDTO opportunityJobTypeDTO) {

		LOG.trace("Mapping role to opportunity, op id : " + opportunityID + " , Opp job type :  "
				+ opportunityJobTypeDTO);

		return new AjaxResponseDTO(this.opportunityManagementFacade
				.mapRoleToOpportunity(new OpportunityDTO(opportunityID), opportunityJobTypeDTO));
	}

	/**
	 * Map cause to opportunity.
	 *
	 * @param opportunityID the opportunity ID
	 * @param opportunityCauseXrefDTO the opportunity cause xref DTO
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/cause", method = RequestMethod.POST)
	public AjaxResponseDTO mapCauseToOpportunity(@PathVariable(value = "opportunityID") long opportunityID,
			@RequestBody OpportunityCauseXrefDTO opportunityCauseXrefDTO) {

		LOG.trace("Mapping role to opportunity, op id : " + opportunityID + " , Opp cause type :  "
				+ opportunityCauseXrefDTO);

		return new AjaxResponseDTO(this.opportunityManagementFacade
				.mapCauseToOpportunity(new OpportunityDTO(opportunityID), opportunityCauseXrefDTO));
	}

	/**
	 * Datlete cause with opportunity.
	 *
	 * @param opportunityID the opportunity ID
	 * @param causeId the cause id
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/cause/{causeId}", method = RequestMethod.DELETE)
	public void datleteCauseWithOpportunity(@PathVariable(value = "opportunityID") long opportunityID,
			@PathVariable(value = "causeId") long causeId) {

		LOG.trace("Deleting cause with  id : " + causeId + " opportunityID " + opportunityID);
		opportunityService.deleteCause(opportunityID, causeId);
	}

	/**
	 * Change opportunity status.
	 *
	 * @param opportunityID the opportunity ID
	 * @param opportunityJobTypeDTO the opportunity job type DTO
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/jobType", method = RequestMethod.DELETE)
	public AjaxResponseDTO changeOpportunityStatus(@PathVariable(value = "opportunityID") long opportunityID,
			@RequestBody OpportunityJobTypeDTO opportunityJobTypeDTO) {

		LOG.trace("Changeing job type status : " + opportunityID + "  ");
		return new AjaxResponseDTO(this.opportunityManagementFacade
				.changeOpportunityJobStatus(new OpportunityDTO(opportunityID), opportunityJobTypeDTO));
	}

	/**
	 * Gets the complete opportunity details.
	 *
	 * @param opportunityID the opportunity ID
	 * @return the complete opportunity details
	 */
	@RequestMapping(value = "/opportunity/cause/edit/{opportunityID}", method = RequestMethod.GET)
	public AjaxResponseDTO getCompleteOpportunityDetails(@PathVariable(value = "opportunityID") long opportunityID) {

		LOG.trace("Editing opp : " + opportunityID);
		OpportunityDTO opportunityDTO = opportunityManagementFacade.getOpportunityDetails(opportunityID);
		List<CauseDTO> causeDTO = userInterestFacade.getMasterCauseList();
		List<JobTypeDTO> jobTypeDTO = userInterestFacade.getMasterSkillList();

		OpportunityJobTypeCauseDTO opportunityJobTypeCauseDTO = new OpportunityJobTypeCauseDTO();
		opportunityJobTypeCauseDTO.setOpportunityDTO(opportunityDTO);
		opportunityJobTypeCauseDTO.setCauseDTO(causeDTO);
		opportunityJobTypeCauseDTO.setJobTypeDTO(jobTypeDTO);

		return new AjaxResponseDTO(opportunityJobTypeCauseDTO);
	}

	/**
	 * Find logged in party.
	 *
	 * @param applyingPartyID the applying party ID
	 * @return the party DTO
	 */
	private PartyDTO findLoggedInParty(long applyingPartyID) {
		PartyDTO applyingPartyDTO = new PartyDTO(applyingPartyID);
		if (applyingPartyID == 0) {
			User loggedInUser = ltoSessionService.getLoggedInUser();
			if (loggedInUser == null) {
				throw new IllegalStateException("User not logged in, illegal state.");
			}
			applyingPartyDTO.setUserBean(new UserDTO(loggedInUser.getId()));
			applyingPartyDTO = this.partyFacade.findPartyDTO(applyingPartyDTO);

		}
		return applyingPartyDTO;
	}
	
	/**
	 * Save or update lat long for opportunity.
	 *
	 * @param opportunityId the opportunity id
	 * @param latLong the lat long
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{{opportunityId}}/save/latLong/{{latLong}}", method = RequestMethod.POST)
	public AjaxResponseDTO saveOrUpdateLatLongForOpportunity(@PathVariable long opportunityId,@PathVariable String latLong) {
		AjaxResponseDTO response = new AjaxResponseDTO();
		try {
			response.setData(opportunityManagementFacade.saveOrUpdateLatLongForOpportunity(opportunityId, latLong));
		} catch (InvalidPreferenceException e) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.INTERNAL_ERROR);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.SAVE_ENTITY_ERROR);
			response.setError(errorDTO);
			LOG.info("getUserCauseSelectedDetails exception:" + e.getLocalizedMessage());
		}
		return response;

		

	}

}
