package com.letstagon.dao.repository;

import java.util.List;

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
	

}
