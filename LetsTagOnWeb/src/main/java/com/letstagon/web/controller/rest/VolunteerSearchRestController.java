package com.letstagon.web.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.facade.UserInterestFacade;
import com.letstagon.facade.VolunteerSearchFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.controller.ControllerConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerSearchRestController.
 */
@RestController(value = "/volunteerSearch")
public class VolunteerSearchRestController {

	/** The volunteer search facade. */
	@Autowired
	private VolunteerSearchFacade volunteerSearchFacade;
	
	/** The user interest facade. */
	@Autowired
	private UserInterestFacade userInterestFacade;

	/**
	 * Search.
	 *
	 * @param name the name
	 * @param location the location
	 * @param start the start
	 * @param skills the skills
	 * @param causes the causes
	 * @param limit the limit
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/volunteerSearch/search", method = RequestMethod.GET)
	public PaginatedResponseDTO search(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "location", required = false) String location,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start,
			@RequestParam(name = "skills", required = false) long[] skills,
			@RequestParam(name = "causes", required = false) long[] causes,
			@RequestParam(name = "limit", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int limit) {

		List<JobTypeDTO> skillsList = null;
		List<CauseDTO> causesList = null;

		// Add skills for search
		if (ArrayUtils.isNotEmpty(skills)) {
			skillsList = new ArrayList<JobTypeDTO>();
			for (long skillId : skills) {
				skillsList.add(new JobTypeDTO(skillId));
			}

		}

		// add causes for search
		if (ArrayUtils.isNotEmpty(causes)) {
			causesList = new ArrayList<CauseDTO>();
			for (long causeId : causes) {
				causesList.add(new CauseDTO(causeId));
			}

		}

		return volunteerSearchFacade.searchVolunteers(name, causesList, skillsList, location, start, limit);

	}
	
	/**
	 * Gets the master details.
	 *
	 * @return the master details
	 */
	@RequestMapping(value = "/master/data", method = RequestMethod.GET)
	public AjaxResponseDTO getMasterDetails() {

		AjaxResponseDTO ajaxResponseDTO = new AjaxResponseDTO();
			HashMap<String, Object> map = volunteerSearchFacade.getMasterDetails();
			ajaxResponseDTO.setData(map);
			
		return ajaxResponseDTO;
	}

}
