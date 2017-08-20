package com.letstagon.dao.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyParticipation;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyParticipationRepository.
 */
public interface PartyParticipationRepository extends PagingAndSortingRepository<PartyParticipation, Long> {

	/**
	 * Find one by job type bean and opportunity bean and party bean.
	 *
	 * @param jobTypeBean the job type bean
	 * @param opportunityBean the opportunity bean
	 * @param partyBean the party bean
	 * @return the party participation
	 */
	PartyParticipation findOneByJobTypeBeanAndOpportunityBeanAndPartyBean(@Param("jobTypeBean") JobType jobTypeBean,
			@Param("opportunityBean") Opportunity opportunityBean, @Param("partyBean") Party partyBean);

	/**
	 * Find all by opportunity bean and party bean.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param partyBean the party bean
	 * @return the list
	 */
	List<PartyParticipation> findAllByOpportunityBeanAndPartyBean(@Param("opportunityBean") Opportunity opportunityBean,
			@Param("partyBean") Party partyBean);

	/**
	 * Find all by opportunity bean.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param pageableData the pageable data
	 * @return the page
	 */
	Page<PartyParticipation> findAllByOpportunityBean(@Param("opportunityBean") Opportunity opportunityBean,
			Pageable pageableData);

	/**
	 * Find all by opportunity bean and status.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param status the status
	 * @param pageableData the pageable data
	 * @return the page
	 */
	Page<PartyParticipation> findAllByOpportunityBeanAndStatus(@Param("opportunityBean") Opportunity opportunityBean,
			@Param("status") Boolean status, Pageable pageableData);

	/**
	 * Find all by opportunity bean and attendance.
	 *
	 * @param opportunityBean the opportunity bean
	 * @param attendance the attendance
	 * @param pageableData the pageable data
	 * @return the page
	 */
	Page<PartyParticipation> findAllByOpportunityBeanAndAttendance(
			@Param("opportunityBean") Opportunity opportunityBean, @Param("attendance") Boolean attendance,
			Pageable pageableData);

	/**
	 * Find all by party bean and status.
	 *
	 * @param partyModel the party model
	 * @param status the status
	 * @param pageRequest the page request
	 * @return the page
	 */
	Page<PartyParticipation> findAllByPartyBeanAndStatus(@Param("partyBean") Party partyModel,
			@Param("status") Boolean status, Pageable pageRequest);

	/**
	 * Find all by party bean and status and start date after.
	 *
	 * @param partyModel the party model
	 * @param status the status
	 * @param dateStart the date start
	 * @param pageRequest the page request
	 * @return the page
	 */
	@Query("Select p From PartyParticipation p  Join p.opportunityBean o "
			+ " where o.dateStart > :dateStart And p.status=:status And p.partyBean=:partyBean")
	Page<PartyParticipation> findAllByPartyBeanAndStatusAndStartDateAfter(@Param("partyBean") Party partyModel,
			@Param("status") Boolean status, @Param("dateStart") Date dateStart, Pageable pageRequest);

	/**
	 * Find by party bean and status and attendance.
	 *
	 * @param partyModel the party model
	 * @param status the status
	 * @param attendance the attendance
	 * @return the list
	 */
	List<PartyParticipation> findByPartyBeanAndStatusAndAttendance(@Param("partyBean") Party partyModel,
			@Param("status") Boolean status, @Param("attendance") Boolean attendance);

	/**
	 * Sets the aggregate rating of party.
	 *
	 * @param partyBean the new aggregate rating of party
	 */
	@Transactional
	@Modifying
	@Query("update Party p SET p.rating=(Select avg(pp.rating) From PartyParticipation pp"
			+ " where  pp.partyBean=:partyBean) where p=:partyBean")
	void setAggregateRatingOfParty(@Param("partyBean") Party partyBean);
	
	
	/**
	 * Find by party bean.
	 *
	 * @param partyBean the party bean
	 * @return the party participation
	 */
	PartyParticipation findByPartyBean(@Param("partyBean")Party partyBean);

}
