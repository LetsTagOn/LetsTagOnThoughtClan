package com.letstagon.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityCauseXref;
import com.letstagon.dao.model.OpportunityJobType;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.exception.profile.InvalidPreferenceException;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityService.
 */
public interface OpportunityService {

	/**
	 * Creates the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity
	 */
	public Opportunity createOpportunity(Opportunity opportunity);

	/**
	 * Delete opportunity.
	 *
	 * @param opportunity the opportunity
	 */
	public void deleteOpportunity(Opportunity opportunity);
	
	/**
	 * Update modified date.
	 *
	 * @param oppID the opp ID
	 */
	public void updateModifiedDate(long oppID);

	/**
	 * Gets the all recent opportunities.
	 *
	 * @return the all recent opportunities
	 */
	public List<Opportunity> getAllRecentOpportunities();

	/**
	 * Gets the opportunity details.
	 *
	 * @param opportunityID the opportunity ID
	 * @return the opportunity details
	 */
	Opportunity getOpportunityDetails(long opportunityID);

	/**
	 * Edits the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity
	 */
	Opportunity editOpportunity(Opportunity opportunity);

	/**
	 * Sets the contact person.
	 *
	 * @param opportunity the opportunity
	 * @param contactperson the contactperson
	 * @return the opportunity
	 */
	Opportunity setContactPerson(Opportunity opportunity, User contactperson);

	/**
	 * Adds the event to program.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity
	 */
	Opportunity addEventToProgram(Opportunity opportunity);

	/**
	 * Map role to opportunity.
	 *
	 * @param opportunityJobType the opportunity job type
	 * @return the opportunity
	 */
	Opportunity mapRoleToOpportunity(OpportunityJobType opportunityJobType);

	/**
	 * Map cause to opportunity.
	 *
	 * @param convert the convert
	 * @return the opportunity
	 */
	Opportunity mapCauseToOpportunity(OpportunityCauseXref convert);

	/**
	 * Gets the opportunities created by party.
	 *
	 * @param party the party
	 * @return the opportunities created by party
	 */
	List<Opportunity> getOpportunitiesCreatedByParty(Party party);

	/**
	 * Change opportunity job status.
	 *
	 * @param opportunity the opportunity
	 * @param opportunityJobType the opportunity job type
	 * @return the opportunity
	 */
	Opportunity changeOpportunityJobStatus(Opportunity opportunity, OpportunityJobType opportunityJobType);

	/**
	 * Delete cause.
	 *
	 * @param opportunityID the opportunity ID
	 * @param causeId the cause id
	 */
	public void deleteCause(long opportunityID , long causeId);

	/**
	 * Save or update lat long for opportunity.
	 *
	 * @param oppId the opp id
	 * @param latLong the lat long
	 * @return the opportunity
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	Opportunity saveOrUpdateLatLongForOpportunity(long oppId, String latLong) throws InvalidPreferenceException;

	/**
	 * Find all by opportunities after date start.
	 *
	 * @param applyingPartyDTO the party id
	 * @param dateStart the date start
	 * @param pageRequest the page request
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByPartyBeanAndAfterDateStart(long applyingPartyDTO,
			Date dateStart, PageRequest pageRequest);

}
