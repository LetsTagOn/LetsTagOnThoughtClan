package com.letstagon.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;

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
	 * Find all by party bean and start date after.
	 *
	 * @param party createdByParty
	 * @param dateStart the date start
	 * @param pageRequest the page request
	 * @return the page
	 */
	@Query("Select p From Opportunity p"
			+ " where p.dateStart > :dateStart And p.createdByParty=:createdByParty")
	Page<Opportunity> findAllByPartyBeanAndStartDateAfter(@Param("createdByParty") Party createdByParty,
		@Param("dateStart") Date dateStart, Pageable pageRequest);

}
