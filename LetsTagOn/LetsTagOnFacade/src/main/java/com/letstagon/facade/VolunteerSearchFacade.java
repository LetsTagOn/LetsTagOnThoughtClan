package com.letstagon.facade;

import java.util.HashMap;
import java.util.List;

import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;

// TODO: Auto-generated Javadoc
/**
 * Facade layer class for Volunteer search.
 * 
 * @author Thoughtclan
 *
 */
public interface VolunteerSearchFacade {

	/**
	 * Search volunteers.
	 *
	 * @param name the name
	 * @param causes the causes
	 * @param skills the skills
	 * @param location the location
	 * @param start the start
	 * @param limit the limit
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO searchVolunteers(String name, List<CauseDTO> causes, List<JobTypeDTO> skills, String location,
			int start, int limit);

	/**
	 * Gets the master details.
	 *
	 * @return the master details
	 */
	public HashMap<String, Object> getMasterDetails();
}
