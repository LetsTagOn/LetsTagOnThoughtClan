package com.letstagon.facade;

import java.util.HashMap;
import java.util.List;

import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserExperienceDTO;
import com.letstagon.facade.dto.linkedin.LinkedInProfessionalExperienceDTO;
// TODO: Auto-generated Javadoc
import com.letstagon.facade.dto.linkedin.LinkedinPositionDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserExperienceFacade.
 */
/* 
 * Facade for user experience details
 * @author ThoughtClan 
 *
 */
public interface UserExperienceFacade {
	
	/**
	 * Gets the user experience list.
	 *
	 * @param id the id
	 * @return the user experience list
	 */
	public HashMap<String, Object> getUserExperienceList(long id);

	/**
	 * Save experience details of user.
	 *
	 * @param user the user
	 * @return the user experience DTO
	 */
	UserExperienceDTO saveExperienceDetailsOfUser(UserExperienceDTO user);
	
	/**
	 * Save professional details of user.
	 *
	 * @param user the user
	 * @param position the position
	 * @return the user experience DTO
	 */
	UserExperienceDTO saveProfessionalDetailsOfUser(UserDTO user, LinkedinPositionDTO position);
	
	/**
	 * Gets the linked professional details.
	 *
	 * @param experienceDTO the experience DTO
	 * @return the linked professional details
	 */
	List<UserExperienceDTO> getLinkedProfessionalDetails(LinkedInProfessionalExperienceDTO experienceDTO);

	/**
	 * Save professional experience details of user.
	 *
	 * @param userList the user list
	 * @param user the user
	 * @return the list
	 */
	List<UserExperienceDTO> saveProfessionalExperienceDetailsOfUser(
			List<UserExperienceDTO> userList, UserDTO user);

}
