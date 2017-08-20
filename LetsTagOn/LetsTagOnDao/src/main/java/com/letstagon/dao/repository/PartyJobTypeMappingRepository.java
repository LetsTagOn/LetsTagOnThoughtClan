package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyJobTypeXref;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyJobTypeMappingRepository.
 */
public interface PartyJobTypeMappingRepository extends PagingAndSortingRepository<PartyJobTypeXref, Long> {
	
	/**
	 * Find by party bean.
	 *
	 * @param partyBean the party bean
	 * @return the list
	 */
	public List<PartyJobTypeXref> findByPartyBean(@Param("partyBean") Party partyBean);
	
	/**
	 * Find by party bean and job type bean.
	 *
	 * @param partyBean the party bean
	 * @param jobType the job type
	 * @return the party job type xref
	 */
	public PartyJobTypeXref findByPartyBeanAndJobTypeBean(@Param("partyBean") Party partyBean,@Param("jobTypeBean") JobType jobType);
}
