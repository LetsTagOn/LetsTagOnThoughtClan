package com.letstagon.dao.repository;

import com.letstagon.dao.model.VolunteerSearchModel;
import com.letstagon.dao.model.PaginatedSearchResponseModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface VolunteerSearchRepository.
 */
public interface VolunteerSearchRepository {

	/**
	 * Search dynamic by criteria.
	 *
	 * @param searchCriteria the search criteria
	 * @param first the first
	 * @param limit the limit
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel searchDynamicByCriteria(VolunteerSearchModel searchCriteria, int first, int limit);

}
