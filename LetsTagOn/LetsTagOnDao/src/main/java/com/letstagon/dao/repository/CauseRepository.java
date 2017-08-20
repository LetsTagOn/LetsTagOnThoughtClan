package com.letstagon.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Interface CauseRepository.
 */
public interface CauseRepository extends PagingAndSortingRepository<Cause, Long> {

	/**
	 * Find all party for cause.
	 *
	 * @param cause the cause
	 * @param page the page
	 * @return the page
	 */
	@Query("select p.partyBean from PartyCauseXref p where p.causeBean=:cause")
	Page<Party> findAllPartyForCause(@Param("cause") Cause cause, Pageable page);

}
