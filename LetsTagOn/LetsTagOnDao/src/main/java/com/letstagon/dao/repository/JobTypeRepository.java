package com.letstagon.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Interface JobTypeRepository.
 */
public interface JobTypeRepository extends PagingAndSortingRepository<JobType, Long> {

	/**
	 * Find all party for job type.
	 *
	 * @param jobTypeBean the job type bean
	 * @param page the page
	 * @return the page
	 */
	@Query("select p.partyBean from PartyJobTypeXref p where p.jobTypeBean=:jobTypeBean")
	Page<Party> findAllPartyForJobType(@Param("jobTypeBean") JobType jobTypeBean, Pageable page);

}
