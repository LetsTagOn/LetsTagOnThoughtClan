package com.letstagon.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyParticipation;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyParticipationService.
 */
public interface PartyParticipationService {
	
	/**
	 * Apply for opportunity.
	 *
	 * @param opportunity the opportunity
	 * @param applyingParty the applying party
	 * @param jobType the job type
	 * @return the party participation
	 */
	PartyParticipation applyForOpportunity(Opportunity opportunity, Party applyingParty, JobType jobType);

	/**
	 * Removes the application for opportunity.
	 *
	 * @param opportunity the opportunity
	 * @param appliedParty the applied party
	 * @param jobType the job type
	 * @return the party participation
	 */
	PartyParticipation removeApplicationForOpportunity(Opportunity opportunity, Party appliedParty, JobType jobType);

	/**
	 * Modify application status.
	 *
	 * @param participation the participation
	 * @param accept the accept
	 * @return the party participation
	 */
	PartyParticipation modifyApplicationStatus(PartyParticipation participation, boolean accept);

	/**
	 * Modify attendance.
	 *
	 * @param participation the participation
	 * @param attended the attended
	 * @return the party participation
	 */
	PartyParticipation modifyAttendance(PartyParticipation participation, boolean attended);

	/**
	 * Modify feedback and rating.
	 *
	 * @param participation the participation
	 * @return the party participation
	 */
	PartyParticipation modifyFeedbackAndRating(PartyParticipation participation);

	/**
	 * Find party participation.
	 *
	 * @param opportunity the opportunity
	 * @param applyingParty the applying party
	 * @param jobType the job type
	 * @return the party participation
	 */
	PartyParticipation findPartyParticipation(Opportunity opportunity, Party applyingParty, JobType jobType);

	/**
	 * Find party participations.
	 *
	 * @param opportunityModel the opportunity model
	 * @param partyModel the party model
	 * @return the list
	 */
	List<PartyParticipation> findPartyParticipations(Opportunity opportunityModel, Party partyModel);

	/**
	 * Find all by opportunity bean.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param pageableData the pageable data
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByOpportunityBean(Opportunity opportunityBean, Pageable pageableData);

	/**
	 * Find all by opportunity bean and status.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param status the status
	 * @param pageableData the pageable data
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByOpportunityBeanAndStatus(Opportunity opportunityBean, Boolean status,
			Pageable pageableData);

	/**
	 * Find all by opportunity bean and attendance.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param attendance the attendance
	 * @param pageableData the pageable data
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByOpportunityBeanAndAttendance(Opportunity opportunityBean, Boolean attendance,
			Pageable pageableData);

	/**
	 * Find all by party bean and status.
	 *
	 * @param partyModel the party model
	 * @param status the status
	 * @param pageRequest the page request
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByPartyBeanAndStatus(Party partyModel, Boolean status, PageRequest pageRequest);

	/**
	 * Find all by party bean and status and after date start.
	 *
	 * @param partyModel the party model
	 * @param status the status
	 * @param dateStart the date start
	 * @param pageRequest the page request
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByPartyBeanAndStatusAndAfterDateStart(Party partyModel, Boolean status,
			Date dateStart, PageRequest pageRequest);
	

	/**
	 * Find opportunities list of party.
	 *
	 * @param partyParticipation the party participation
	 * @return the list
	 */
	List<PartyParticipation> findOpportunitiesListOfParty(
			PartyParticipation partyParticipation);
	
	/**
	 * Find by party bean.
	 *
	 * @param partyBean the party bean
	 * @return the party participation
	 */
	PartyParticipation findByPartyBean(Party partyBean);
}
