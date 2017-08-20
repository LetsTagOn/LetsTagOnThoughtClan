package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityCauseXref;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityCauseRepository.
 */
public interface OpportunityCauseRepository extends PagingAndSortingRepository<OpportunityCauseXref, Long> {

	/**
	 * Find one by cause bean and opportunity bean.
	 *
	 * @param causeBean the cause bean
	 * @param opportunityBean the opportunity bean
	 * @return the opportunity cause xref
	 */
	OpportunityCauseXref findOneByCauseBeanAndOpportunityBean(@Param("causeBean") Cause causeBean,
			@Param("OpportunityBean") Opportunity opportunityBean);
}
