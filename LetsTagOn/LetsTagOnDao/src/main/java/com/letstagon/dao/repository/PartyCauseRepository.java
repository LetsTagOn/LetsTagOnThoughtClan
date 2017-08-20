package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyCauseXref;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyCauseRepository.
 */
public interface PartyCauseRepository extends PagingAndSortingRepository<PartyCauseXref, Long> {
	
	/**
	 * Find by party bean.
	 *
	 * @param partyBean the party bean
	 * @return the list
	 */
	public List<PartyCauseXref> findByPartyBean(@Param("partyBean") Party partyBean);
	
	/**
	 * Find by party bean and cause bean.
	 *
	 * @param partyBean the party bean
	 * @param causeBean the cause bean
	 * @return the party cause xref
	 */
	public PartyCauseXref findByPartyBeanAndCauseBean(@Param("partyBean") Party partyBean,@Param("causeBean") Cause causeBean);
}
