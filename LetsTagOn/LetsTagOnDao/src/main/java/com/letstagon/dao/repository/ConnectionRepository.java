package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Connection;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConnectionRepository.
 */
public interface ConnectionRepository extends PagingAndSortingRepository<Connection, Long> {
	
	/**
	 * Find one by party 1 and party 2.
	 *
	 * @param volunteer the volunteer
	 * @param connectedParty the connected party
	 * @return the connection
	 */
	@Query("SELECT c FROM Connection c where ( c.party2=:party2  and c.party1=:party1 ) or ( c.party2=:party1  and c.party1=:party2 )")
	Connection findOneByParty1AndParty2(@Param("party1") Party volunteer, @Param("party2") Party connectedParty);

	/**
	 * List open connections.
	 *
	 * @param party the party
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT c FROM Connection c where c.party2=:party  and c.connected is null")
	Page<Connection> listOpenConnections(@Param("party") Party party, Pageable pageable);

	/**
	 * List connected volunteers.
	 *
	 * @param party the party
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT c FROM Connection c JOIN c.party2 p WHERE (c.party1=:party  OR c.party2=:party )"
			+ "AND c.connected= true AND p.userBean is not null")
	Page<Connection> listConnectedVolunteers(@Param("party") Party party, Pageable pageable);

	/**
	 * List connected organizations.
	 *
	 * @param volunteer the volunteer
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT c FROM Connection c JOIN c.party2 p where c.party1=:party AND p.organizationBean is not null"
			+ "  AND  c.connected= true")
	Page<Connection> listConnectedOrganizations(@Param("party") Party volunteer, Pageable pageable);
	
	/**
	 * Find by party 1.
	 *
	 * @param volunteer the volunteer
	 * @return the list
	 */
	@Query("SELECT c FROM Connection c where c.party2=:party or c.party1=:party")
	List<Connection> findByParty1(@Param("party") Party volunteer);

}
