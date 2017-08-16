package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityJobType;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityJobTypeRepository.
 */
public interface OpportunityJobTypeRepository extends PagingAndSortingRepository<OpportunityJobType, Long> {

	/**
	 * Find one by job type bean and opportunity bean.
	 *
	 * @param jobTypeBean the job type bean
	 * @param opportunityBean the opportunity bean
	 * @return the opportunity job type
	 */
	OpportunityJobType findOneByJobTypeBeanAndOpportunityBean(@Param("jobTypeBean") JobType jobTypeBean,
			@Param("OpportunityBean") Opportunity opportunityBean);
	
	
	
}
