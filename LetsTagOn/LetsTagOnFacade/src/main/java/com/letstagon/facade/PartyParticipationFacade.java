package com.letstagon.facade;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PartyParticipationDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyParticipationFacade.
 */
public interface PartyParticipationFacade {
	
	/**
	 * Apply for opportunity.
	 *
	 * @param opportunity the opportunity
	 * @param applyingPartyDTO the applying party DTO
	 * @param jobType the job type
	 * @return the party participation DTO
	 */
	PartyParticipationDTO applyForOpportunity(OpportunityDTO opportunity, PartyDTO applyingPartyDTO,
			JobTypeDTO jobType);

	/**
	 * Removes the application for opportunity.
	 *
	 * @param opportunity the opportunity
	 * @param appliedPartyDTO the applied party DTO
	 * @param jobType the job type
	 * @return the party participation DTO
	 */
	PartyParticipationDTO removeApplicationForOpportunity(OpportunityDTO opportunity, PartyDTO appliedPartyDTO,
			JobTypeDTO jobType);

	/**
	 * Modify application status.
	 *
	 * @param participation the participation
	 * @param accept the accept
	 * @return the party participation DTO
	 */
	PartyParticipationDTO modifyApplicationStatus(PartyParticipationDTO participation, boolean accept);

	/**
	 * Modify attendance.
	 *
	 * @param participation the participation
	 * @param attended the attended
	 * @return the party participation DTO
	 */
	PartyParticipationDTO modifyAttendance(PartyParticipationDTO participation, boolean attended);

	/**
	 * Modify feedback and rating.
	 *
	 * @param participation the participation
	 * @return the party participation DTO
	 */
	PartyParticipationDTO modifyFeedbackAndRating(PartyParticipationDTO participation);

	/**
	 * Find party participation DTO.
	 *
	 * @param opportunity the opportunity
	 * @param applyingPartyDTO the applying party DTO
	 * @param jobType the job type
	 * @return the party participation DTO
	 */
	PartyParticipationDTO findPartyParticipationDTO(OpportunityDTO opportunity, PartyDTO applyingPartyDTO,
			JobTypeDTO jobType);

	/**
	 * Find party participation DTO.
	 *
	 * @param opportunityDTO the opportunity DTO
	 * @param loggedInParty the logged in party
	 * @return the list
	 */
	List<PartyParticipationDTO> findPartyParticipationDTO(OpportunityDTO opportunityDTO, PartyDTO loggedInParty);

	/**
	 * Find all by opportunity bean.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param pageableData the pageable data
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByOpportunityBean(OpportunityDTO opportunityBean, Pageable pageableData);

	/**
	 * Find all by opportunity bean and status.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param status the status
	 * @param pageableData the pageable data
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByOpportunityBeanAndStatus(OpportunityDTO opportunityBean, Boolean status,
			Pageable pageableData);

	/**
	 * Find all by opportunity bean and attendance.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param attendance the attendance
	 * @param pageableData the pageable data
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByOpportunityBeanAndAttendance(OpportunityDTO opportunityBean, Boolean attendance,
			Pageable pageableData);

	/**
	 * Find all by party bean and status.
	 *
	 * @param applyingPartyDTO the applying party DTO
	 * @param status the status
	 * @param pageRequest the page request
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByPartyBeanAndStatus(PartyDTO applyingPartyDTO, Boolean status,
			PageRequest pageRequest);

	/**
	 * Find all by party bean and status and after date start.
	 *
	 * @param applyingPartyDTO the applying party DTO
	 * @param status the status
	 * @param dateStart the date start
	 * @param pageRequest the page request
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByPartyBeanAndStatusAndAfterDateStart(PartyDTO applyingPartyDTO, Boolean status,
			Date dateStart, PageRequest pageRequest);
}
