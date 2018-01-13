package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.UserExperience;
import com.letstagon.enums.UserExperienceTypeEnum;
import com.letstagon.facade.UserExperienceFacade;
import com.letstagon.facade.converter.UserProfessionalExperienceDTOConverter;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserExperienceDTO;
import com.letstagon.facade.dto.linkedin.LinkedInProfessionalExperienceDTO;
import com.letstagon.facade.dto.linkedin.LinkedinPositionDTO;
import com.letstagon.facade.dto.linkedin.LinkedinUserExperienceDTO;
import com.letstagon.service.UserExperienceService;
// TODO: Auto-generated Javadoc

/**
 * The Class UserExperienceFacadeImpl.
 */
@Component
public class UserExperienceFacadeImpl implements UserExperienceFacade {
		
	/** The user experience service. */
	@Autowired
	private UserExperienceService userExperienceService;
	
	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;
	
	/** The professional experience DTO converter. */
	@Autowired
	private UserProfessionalExperienceDTOConverter professionalExperienceDTOConverter; 
	
	/** The user experience model converter. */
	@Autowired
	private Converter<UserExperience, UserExperienceDTO> userExperienceModelConverter;
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserExperienceFacade#getUserExperienceList(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getUserExperienceList(long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> hashMap = userExperienceService.getUserExperienceList(id);
		if (!hashMap.isEmpty()) {
			List<UserExperience> educationalDetails = (List<UserExperience>) hashMap.get("educationDetails");
			List<UserExperience> professionalDetails = (List<UserExperience>) hashMap.get("professionalDetails");
			List<UserExperience> volunteerDetails = (List<UserExperience>) hashMap.get("volunteeringDetails");
			List<UserExperienceDTO> educationalDetailList = new ArrayList<UserExperienceDTO>();
			List<UserExperienceDTO> professionalDetailslist = new ArrayList<UserExperienceDTO>();
			List<UserExperience> volunteeringDetailsList = new ArrayList<UserExperience>();
			if (professionalDetails != null) {
				for (UserExperience userExperience : professionalDetails) {
					UserExperienceDTO experience = userExperienceModelConverter.convert(userExperience);
					professionalDetailslist.add(experience);
				}
			}
			if (educationalDetails != null) {
				for (UserExperience userExperience : educationalDetails) {
					UserExperienceDTO experience = userExperienceModelConverter.convert(userExperience);
					educationalDetailList.add(experience);
				}
			}
			if (volunteerDetails != null) {
				for (UserExperience userExperience : volunteerDetails) {
					volunteeringDetailsList.add(userExperience);
				}
			}
			map.put("educationDetails", educationalDetailList);
			map.put("professionalDetails", professionalDetailslist);
			map.put("volunteeringDetails", volunteeringDetailsList);
		}

		return map;
	}
	

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserExperienceFacade#saveExperienceDetailsOfUser(com.letstagon.facade.dto.UserExperienceDTO)
	 */
	@Override
	public UserExperienceDTO saveExperienceDetailsOfUser(UserExperienceDTO userDetails) {
		UserExperience userExperience = mapper.map(userDetails, UserExperience.class);
		userExperience = userExperienceService.saveVolunteerExperienceOfUser(userExperience);
		UserExperienceDTO detailsDTO = userExperienceModelConverter.convert(userExperience);
		return detailsDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserExperienceFacade#saveProfessionalDetailsOfUser(com.letstagon.facade.dto.UserDTO, com.letstagon.facade.dto.linkedin.LinkedinPositionDTO)
	 */
	@Override
	public UserExperienceDTO saveProfessionalDetailsOfUser(UserDTO user,LinkedinPositionDTO position) {
		// TODO Auto-generated method stub

		UserExperienceDTO userDetails = new UserExperienceDTO();
		for (LinkedinUserExperienceDTO value : position.getValues())

		{
			userDetails.setUserBean(user);
			userDetails.setDescription(value.getSummary());
			/*userDetails.setEndDate(calendarToDate(value.getEndDate()));
			userDetails.setStartDate(value.getStartDate());*/
			userDetails.setTitle(value.getTitle());
			userDetails.setLocation(value.getLocation().getName());
			userDetails.setOrganizationName(value.getCompany().getName());
			userDetails.setType(UserExperienceTypeEnum.PROFESSIONAL.getExperienceType());
		}

		UserExperience userExperience = mapper.map(userDetails, UserExperience.class);
		userExperience = userExperienceService.saveVolunteerExperienceOfUser(userExperience);
		UserExperienceDTO experienceDTO = userExperienceModelConverter.convert(userExperience);
		return experienceDTO;

	}
	
	/**
	 * Calendar to date.
	 *
	 * @param date the date
	 * @return the date
	 */
	private Date calendarToDate(Date date){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		int month = calendar1.get(calendar1.MONTH);
		int year = calendar1.get(calendar1.YEAR);
		calendar1.set(year,month,Calendar.DATE);
		Date returningDate = calendar1.getTime();
		return returningDate;


	}


	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserExperienceFacade#getLinkedProfessionalDetails(com.letstagon.facade.dto.linkedin.LinkedInProfessionalExperienceDTO)
	 */
	@Override
	public List<UserExperienceDTO> getLinkedProfessionalDetails(
			LinkedInProfessionalExperienceDTO experienceDTO) {
		List<UserExperienceDTO> experienceList = new ArrayList<UserExperienceDTO>();
		if(experienceDTO.getPositions().getValues() != null){
			for (LinkedinUserExperienceDTO position : experienceDTO.getPositions().getValues()) {
				experienceList.add(professionalExperienceDTOConverter.convert(position));
			}
		}
		return experienceList;
	}


	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserExperienceFacade#saveProfessionalExperienceDetailsOfUser(java.util.List, com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public List<UserExperienceDTO> saveProfessionalExperienceDetailsOfUser(
			List<UserExperienceDTO> userList,UserDTO user) {
		List<UserExperienceDTO> experienceList = new ArrayList<UserExperienceDTO>();
		if(userList != null){
			for (UserExperienceDTO userExperienceDTO : userList) {
				userExperienceDTO.setUserBean(user);
				experienceList.add(this.saveExperienceDetailsOfUser(userExperienceDTO));
			}
			
		}
		return experienceList;
	}
}
