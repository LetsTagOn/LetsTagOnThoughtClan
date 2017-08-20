package com.letstagon.dao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.VolunteerSearchModel;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.repository.VolunteerSearchRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerSearchRepositoryImpl.
 */
@Component
public class VolunteerSearchRepositoryImpl implements VolunteerSearchRepository {

	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VolunteerSearchRepositoryImpl.class);

	/* (non-Javadoc)
	 * @see com.letstagon.dao.repository.VolunteerSearchRepository#searchDynamicByCriteria(com.letstagon.dao.model.VolunteerSearchModel, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel searchDynamicByCriteria(VolunteerSearchModel searchCriteria, int first,
			int limit) {

		String selectQuery = "SELECT DISTINCT(u) from User u ";

		String countQueryString = "SELECT count(distinct u) from User u ";

		String whereClause = "";

		String joinQuery = " join u.parties p ";

		// Joins & where clauses

		if (StringUtils.isNotBlank(searchCriteria.getName())) {
			whereClause = this.appendWhereClause(whereClause,
					" LOWER(CONCAT(u.firstName,' ',u.lastName)) LIKE LOWER(CONCAT('%',:name, '%')) ", " AND ");
		}

		if (searchCriteria.getCauses() != null && !searchCriteria.getCauses().isEmpty()) {
			joinQuery += " join p.partyCauseXrefs pcx   ";
			whereClause = this.appendWhereClause(whereClause, "  pcx.causeBean in :causes  ", " AND ");
		}
		if (searchCriteria.getSkills() != null && !searchCriteria.getSkills().isEmpty()) {
			joinQuery += " join p.partyJobTypeXrefs pjx ";
			whereClause = this.appendWhereClause(whereClause, "   pjx.jobTypeBean in :jobTypes   ", " AND ");
		}

		if (StringUtils.isNotBlank(searchCriteria.getLocation())) {
			joinQuery += " join u.addressBean addr ";
			whereClause = this.appendWhereClause(whereClause, "     LOWER(addr.city) like LOWER(CONCAT('%',:location, '%'))  ", " AND ");

		}

		String finalQuery;

		// append join query
		finalQuery = selectQuery + joinQuery;
		countQueryString += joinQuery;

		// append where clause
		finalQuery += whereClause;
		countQueryString += whereClause;

		Query query = this.entityManager.createQuery(finalQuery);
		Query countQuery = this.entityManager.createQuery(countQueryString);

		if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
			query.setParameter("name", searchCriteria.getName());
			countQuery.setParameter("name", searchCriteria.getName());
		}
		if (searchCriteria.getCauses() != null) {
			query.setParameter("causes", searchCriteria.getCauses());
			countQuery.setParameter("causes", searchCriteria.getCauses());
		}
		if (searchCriteria.getSkills() != null) {
			query.setParameter("jobTypes", searchCriteria.getSkills());
			countQuery.setParameter("jobTypes", searchCriteria.getSkills());
		}
		if (searchCriteria.getLocation() != null && !searchCriteria.getLocation().isEmpty()) {

			query.setParameter("location", searchCriteria.getLocation());
			countQuery.setParameter("location", searchCriteria.getLocation());

		}
		query.setFirstResult(first);
		query.setMaxResults(limit);

		List<User> userList = query.getResultList();

		Long totalCount = (Long) countQuery.getSingleResult();

		LOG.info("Total count of users found : " + totalCount);

		PaginatedSearchResponseModel response = new PaginatedSearchResponseModel(userList, first, limit, totalCount);

		return response;

	}

	/**
	 * TODO-use if required, test using facade layer if loads correctly.
	 *
	 * @param userList the user list
	 * @param searchDTO the search DTO
	 */
	private void loadLazyObjects(List<User> userList, VolunteerSearchModel searchDTO) {

		if (userList == null || userList.size() == 0) {
			return;
		}

		for (User user : userList) {

			// load causes
			if (searchDTO.getCauses() != null) {
				for (Party party : user.getParties()) {
					party.getPartyCauseXrefs();

				}
			}

			// load jobTypes
			if (searchDTO.getSkills() != null) {
				for (Party party : user.getParties()) {
					party.getPartyJobTypeXrefs();

				}
			}

		}

	}

	/**
	 * Append where clause.
	 *
	 * @param whereClause the where clause
	 * @param append the append
	 * @param condition the condition
	 * @return the string
	 */
	private String appendWhereClause(String whereClause, String append, String condition) {

		String finalClauseString = "";
		if (StringUtils.isBlank(whereClause)) {
			finalClauseString = " WHERE ";
		} else {
			finalClauseString = " " + condition + " ";
		}

		finalClauseString = finalClauseString.concat(append);

		return whereClause.concat(finalClauseString);

	}

}
