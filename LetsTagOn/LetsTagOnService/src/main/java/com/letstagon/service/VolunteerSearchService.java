package com.letstagon.service;

import com.letstagon.dao.model.VolunteerSearchModel;
import com.letstagon.dao.model.PaginatedSearchResponseModel;

// TODO: Auto-generated Javadoc
/**
 * Service layer class for searching volunteers.
 *
 * @author Thoughtclan
 */
public interface VolunteerSearchService {

	/**
	 * Search dynamic by criteria.
	 *
	 * @param searchCriteria the search criteria
	 * @param start the start
	 * @param limit the limit
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel searchDynamicByCriteria(VolunteerSearchModel searchCriteria, int start, int limit);

}
