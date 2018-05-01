package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityRepository.
 */
public interface OpportunityRepository extends PagingAndSortingRepository<Opportunity, Long> {

	/**
	 * Find all by created by party.
	 *
	 * @param createdByParty the created by party
	 * @return the list
	 */
	List<Opportunity> findAllByCreatedByParty(@Param("createdByParty") Party createdByParty);
	
	/**
	 * Find all by party bean and status and start date after.
	 *
	 * @param partyModel createdByParty
	 * @param dateStart the date start
	 * @param pageRequest the page request
	 * @return the page
	 */
	@Query("Select o From PartyParticipation p Join p.opportunityBean o"
			+ " where p.partyBean=:createdByParty And o.dateStart > :dateStart ")
	Page<Opportunity> findAllByPartyBeanAndStatusAndStartDateAfter(@Param("createdByParty") Party createdByParty,
		@Param("dateStart") Date dateStart, Pageable pageRequest);

}
