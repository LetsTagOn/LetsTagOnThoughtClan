package com.letstagon.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Entityy;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;

public interface EntityyRepository extends PagingAndSortingRepository<Entityy, Long> {
	
	Page<Entityy> findAllByUser(@Param("user") User user, Pageable pageable);
	
	@Query(" select e from Entityy e, Connection c WHERE ((e.user = c.party1.userBean AND c.party1 = :party) "
			+ " OR (e.user = c.party2.userBean AND c.party2 = :party)) "
			+ " AND c.connected = true ")
	Page<Entityy> findAllForUser(@Param("party") Party party, Pageable pageable);
	
}
