package com.letstagon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.constant.LetsTagOnDaoConstants.DataRepositoryConstants;
import com.letstagon.dao.model.VolunteerSearchModel;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.repository.VolunteerSearchRepository;
import com.letstagon.service.VolunteerSearchService;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerSearchServiceImpl.
 */
@Component
public class VolunteerSearchServiceImpl implements VolunteerSearchService {

	/** The volunteer search repository. */
	@Autowired
	private VolunteerSearchRepository volunteerSearchRepository;

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerSearchService#searchDynamicByCriteria(com.letstagon.dao.model.VolunteerSearchModel, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel searchDynamicByCriteria(VolunteerSearchModel searchCriteria, int start, int limit) {

		if (limit == 0) {
			limit = DataRepositoryConstants.PAGE_SIZE_DEFAULT;
		}

		return volunteerSearchRepository.searchDynamicByCriteria(searchCriteria, start, limit);
	}

}
