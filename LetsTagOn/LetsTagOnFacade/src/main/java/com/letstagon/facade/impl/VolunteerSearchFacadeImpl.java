package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.VolunteerSearchModel;
import com.letstagon.facade.UserInterestFacade;
import com.letstagon.facade.VolunteerSearchFacade;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.service.VolunteerSearchService;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerSearchFacadeImpl.
 */
@Component
public class VolunteerSearchFacadeImpl implements VolunteerSearchFacade {

	/** The user model converter. */
	@Autowired
	private Converter<User, UserDTO> userModelConverter;

	/** The volunteer search service. */
	@Autowired
	private VolunteerSearchService volunteerSearchService;
	
	/** The user interest facade. */
	@Autowired
	private UserInterestFacade userInterestFacade;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerSearchFacade#searchVolunteers(java.lang.String, java.util.List, java.util.List, java.lang.String, int, int)
	 */
	@Override
	public PaginatedResponseDTO searchVolunteers(String name, List<CauseDTO> causes, List<JobTypeDTO> skills,
			String location, int start, int limit) {

		List<JobType> skillsModelList = null;
		List<Cause> causesModelList = null;

		if (skills != null && !skills.isEmpty()) {

			skillsModelList = new ArrayList<JobType>();

			for (JobTypeDTO skill : skills) {
				skillsModelList.add(this.mapper.map(skill, JobType.class));
			}
		}

		if (causes != null && !causes.isEmpty()) {

			causesModelList = new ArrayList<Cause>();
			for (CauseDTO cause : causes) {
				causesModelList.add(this.mapper.map(cause, Cause.class));
			}
		}

		VolunteerSearchModel searchDto = new VolunteerSearchModel(name, causesModelList, skillsModelList, location);

		PaginatedSearchResponseModel response = volunteerSearchService.searchDynamicByCriteria(searchDto, start, limit);

		List<User> users = (List<User>) response.getSearchResult();

		if (users == null || users.isEmpty())
			users = Collections.EMPTY_LIST;

		List<UserDTO> userListResponse = new ArrayList<UserDTO>();

		for (User user : users) {
			userListResponse.add(this.userModelConverter.convert(user));
		}

		PaginatedResponseDTO responseDto = new PaginatedResponseDTO();
		responseDto.setSearchResult(userListResponse);
		responseDto.setPage(start);
		responseDto.setSize(limit);
		responseDto.setTotalCount(response.getTotalCount());

		return responseDto;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerSearchFacade#getMasterDetails()
	 */
	@Override
	public HashMap<String, Object> getMasterDetails() {
		// TODO Auto-generated method stub
		Iterable<CauseDTO> causeList = userInterestFacade.getMasterCauseList();
		Iterable<JobTypeDTO> skillList = userInterestFacade.getMasterSkillList();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("causeList", causeList);
		hashMap.put("skillList", skillList);
		return hashMap;
	}

}
