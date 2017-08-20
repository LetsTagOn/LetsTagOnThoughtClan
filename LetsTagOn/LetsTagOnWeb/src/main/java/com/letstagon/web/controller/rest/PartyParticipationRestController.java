package com.letstagon.web.controller.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.dao.model.User;
import com.letstagon.facade.PartyFacade;
import com.letstagon.facade.PartyParticipationFacade;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PartyParticipationDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyParticipationRestController.
 */
@RestController
public class PartyParticipationRestController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PartyParticipationRestController.class);

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/** The participation facade. */
	@Autowired
	private PartyParticipationFacade participationFacade;

	/** The party facade. */
	@Autowired
	private PartyFacade partyFacade;

	/**
	 * Apply for opportunity.
	 *
	 * @param opportunityID the opportunity ID
	 * @param applyingPartyID the applying party ID
	 * @param jobTypeID the job type ID
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/party/{applyingPartyID}/jobType/{jobTypeID}", method = RequestMethod.POST)
	public AjaxResponseDTO applyForOpportunity(@PathVariable(value = "opportunityID") long opportunityID,
			@PathVariable(value = "applyingPartyID") long applyingPartyID,
			@PathVariable(value = "jobTypeID") long jobTypeID) {

		LOG.trace(
				"Applying for opp : " + opportunityID + " , party : " + applyingPartyID + " , job type : " + jobTypeID);

		// TODO-find allowed parties
		PartyDTO applyingPartyDTO = findLoggedInParty(applyingPartyID);

		JobTypeDTO jobType = new JobTypeDTO(jobTypeID);
		OpportunityDTO opportunity = new OpportunityDTO(opportunityID);

		PartyParticipationDTO participation = participationFacade.applyForOpportunity(opportunity, applyingPartyDTO,
				jobType);

		return new AjaxResponseDTO(participation);
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
	 * Removes the application for opportunity.
	 *
	 * @param opportunityID the opportunity ID
	 * @param applyingPartyID the applying party ID
	 * @param jobTypeID the job type ID
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/party/{applyingPartyID}/jobType/{jobTypeID}", method = RequestMethod.DELETE)
	public AjaxResponseDTO removeApplicationForOpportunity(@PathVariable(value = "opportunityID") long opportunityID,
			@PathVariable(value = "applyingPartyID") long applyingPartyID,
			@PathVariable(value = "jobTypeID") long jobTypeID) {

		LOG.trace("Removing application for opp : " + opportunityID + " , party : " + applyingPartyID + " , job type : "
				+ jobTypeID);

		PartyDTO applyingPartyDTO = findLoggedInParty(applyingPartyID);

		JobTypeDTO jobType = new JobTypeDTO(jobTypeID);
		OpportunityDTO opportunity = new OpportunityDTO(opportunityID);

		PartyParticipationDTO participation = participationFacade.removeApplicationForOpportunity(opportunity,
				applyingPartyDTO, jobType);

		return new AjaxResponseDTO(participation);
	}

	/**
	 * Modify application status.
	 *
	 * @param participationID the participation ID
	 * @param accept the accept
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/participation/{participationID}/status", method = RequestMethod.POST)
	public AjaxResponseDTO modifyApplicationStatus(@PathVariable(value = "participationID") long participationID,
			@RequestParam(name = "accept", defaultValue = "true") boolean accept) {

		LOG.trace("Changing application status for : " + participationID + " , status : " + accept);

		PartyParticipationDTO participation = new PartyParticipationDTO(participationID);
		participation = this.participationFacade.modifyApplicationStatus(participation, accept);

		return new AjaxResponseDTO(participation);
	}

	/**
	 * Modify attendance.
	 *
	 * @param participationID the participation ID
	 * @param attended the attended
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/participation/{participationID}/attendance", method = RequestMethod.POST)
	public AjaxResponseDTO modifyAttendance(@PathVariable(value = "participationID") long participationID,
			@RequestParam(name = "attended", defaultValue = "true") boolean attended) {

		LOG.trace("Changing attendance for : " + participationID + " , attended : " + attended);
		PartyParticipationDTO participation = new PartyParticipationDTO(participationID);
		participation = this.participationFacade.modifyAttendance(participation, attended);

		return new AjaxResponseDTO(participation);
	}

	/**
	 * Modify feedback.
	 *
	 * @param participation the participation
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/participation/feedBack", method = RequestMethod.POST)
	public AjaxResponseDTO modifyFeedback(@RequestBody PartyParticipationDTO participation) {

		LOG.trace("Feedback, particiaption : " + participation.getId() + " , feedback : " + participation.getReview()
				+ " , rating : " + participation.getRating());

		return new AjaxResponseDTO(this.participationFacade.modifyFeedbackAndRating(participation));
	}

	/**
	 * Modify rating.
	 *
	 * @param participation the participation
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/opportunity/participation/rating", method = RequestMethod.POST)
	public AjaxResponseDTO modifyRating(@RequestBody PartyParticipationDTO participation) {

		LOG.trace("Feedback, particiaption : " + participation.getId() + " , feedback : " + participation.getReview()
				+ " , rating : " + participation.getRating());

		return new AjaxResponseDTO(this.participationFacade.modifyFeedbackAndRating(participation));
	}

	/**
	 * Find all by opportunity bean.
	 *
	 * @param opportunityID the opportunity ID
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/participantsList", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByOpportunityBean(@PathVariable(value = "opportunityID") long opportunityID,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		return this.participationFacade.findAllByOpportunityBean(new OpportunityDTO(opportunityID),
				new PageRequest(page, size));
	}

	/**
	 * Find all by opportunity bean and status.
	 *
	 * @param opportunityID the opportunity ID
	 * @param status the status
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/participantsByStatus", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByOpportunityBeanAndStatus(
			@PathVariable(value = "opportunityID") long opportunityID,
			@RequestParam(name = "status", required = false) Boolean status,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		return this.participationFacade.findAllByOpportunityBeanAndStatus(new OpportunityDTO(opportunityID), status,
				new PageRequest(page, size));
	}

	/**
	 * Find all by opportunity bean and attendance.
	 *
	 * @param opportunityID the opportunity ID
	 * @param attendance the attendance
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/{opportunityID}/participantsByAttendance", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByOpportunityBeanAndAttendance(
			@PathVariable(value = "opportunityID") long opportunityID,
			@RequestParam(name = "attendance", required = false) Boolean attendance,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		return this.participationFacade.findAllByOpportunityBeanAndAttendance(new OpportunityDTO(opportunityID),
				attendance, new PageRequest(page, size));
	}

	/**
	 * Find upcoming opportunities for party.
	 *
	 * @param status the status
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/partyParticipation", method = RequestMethod.GET)
	public PaginatedResponseDTO findUpcomingOpportunitiesForParty(
			@RequestParam(name = "status", required = false) Boolean status,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		// TODO-find allowed parties
		PartyDTO applyingPartyDTO = findLoggedInParty(0);
		return this.participationFacade.findAllByPartyBeanAndStatus(applyingPartyDTO, status,
				new PageRequest(page, size));
	}

	/**
	 * Find upcoming opportunities for party after current date.
	 *
	 * @param status the status
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/opportunity/partyParticipation/upcoming", method = RequestMethod.GET)
	public PaginatedResponseDTO findUpcomingOpportunitiesForPartyAfterCurrentDate(
			@RequestParam(name = "status", required = false) Boolean status,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		// TODO-find allowed parties
		PartyDTO applyingPartyDTO = findLoggedInParty(0);
		return this.participationFacade.findAllByPartyBeanAndStatusAndAfterDateStart(applyingPartyDTO, status,
				new Date(), new PageRequest(page, size));
	}

}
