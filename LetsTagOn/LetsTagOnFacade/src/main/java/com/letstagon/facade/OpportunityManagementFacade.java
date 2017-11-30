package com.letstagon.facade;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.dto.OpportunityCauseXrefDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityJobTypeDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * Facade layer class for managing opportunities.
 *
 * @author Thoughtclan
 */
public interface OpportunityManagementFacade {

	/**
	 * Gets the opportunity details.
	 *
	 * @param opportunityID the opportunity ID
	 * @return the opportunity details
	 */
	OpportunityDTO getOpportunityDetails(long opportunityID);

	/**
	 * Creates the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity DTO
	 */
	OpportunityDTO createOpportunity(OpportunityDTO opportunity);

	/**
	 * Edits the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity DTO
	 */
	OpportunityDTO editOpportunity(OpportunityDTO opportunity);

	/**
	 * Sets the contact person.
	 *
	 * @param opportunity the opportunity
	 * @param contactperson the contactperson
	 * @return the opportunity DTO
	 */
	OpportunityDTO setContactPerson(OpportunityDTO opportunity, UserDTO contactperson);

	/**
	 * Adds the event to program.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity DTO
	 */
	OpportunityDTO addEventToProgram(OpportunityDTO opportunity);

	/**
	 * Map role to opportunity.
	 *
	 * @param opportunityDTO the opportunity DTO
	 * @param opportunityJobTypeDTO the opportunity job type DTO
	 * @return the opportunity DTO
	 */
	OpportunityDTO mapRoleToOpportunity(OpportunityDTO opportunityDTO, OpportunityJobTypeDTO opportunityJobTypeDTO);

	/**
	 * Map cause to opportunity.
	 *
	 * @param opportunityDTO the opportunity DTO
	 * @param opportunityCauseXrefDTO the opportunity cause xref DTO
	 * @return the opportunity DTO
	 */
	OpportunityDTO mapCauseToOpportunity(OpportunityDTO opportunityDTO,
			OpportunityCauseXrefDTO opportunityCauseXrefDTO);

	/**
	 * Gets the opportunities created by party.
	 *
	 * @param partyDTO the party DTO
	 * @return the opportunities created by party
	 */
	List<OpportunityDTO> getOpportunitiesCreatedByParty(PartyDTO partyDTO);

	/**
	 * Change opportunity job status.
	 *
	 * @param opportunityDTO the opportunity DTO
	 * @param opportunityJobTypeDTO the opportunity job type DTO
	 * @return the opportunity DTO
	 */
	OpportunityDTO changeOpportunityJobStatus(OpportunityDTO opportunityDTO,
			OpportunityJobTypeDTO opportunityJobTypeDTO);

	/**
	 * Save or update lat long for opportunity.
	 *
	 * @param oppId the opp id
	 * @param latLong the lat long
	 * @return the opportunity DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	OpportunityDTO saveOrUpdateLatLongForOpportunity(long oppId, String latLong) throws InvalidPreferenceException;
	
	/**
	 * Find all opportunities after date start.
	 *
	 * @param applyingPartyDTO the applying party DTO
	 * @param status the status
	 * @param dateStart the date start
	 * @param pageRequest the page request
	 * @return the paginated response DTO
	 */
	public PaginatedResponseDTO findAllByPartyBeanAndStatusAndAfterDateStart(PartyDTO applyingPartyDTO,
			Date dateStart, PageRequest pageRequest);

}
