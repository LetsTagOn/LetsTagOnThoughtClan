package com.letstagon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserExperience;
import com.letstagon.dao.repository.UserExperienceRepository;
import com.letstagon.enums.UserExperienceTypeEnum;
import com.letstagon.service.UserExperienceService;
import com.letstagon.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserExperienceServiceImpl.
 */
@Component
public class UserExperienceServiceImpl implements UserExperienceService {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserExperienceServiceImpl.class);
	
	/** The user experience repository. */
	@Autowired
	private UserExperienceRepository userExperienceRepository;


	/** The user service. */
	@Autowired
	private UserService userService;

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserExperienceService#saveVolunteerExperienceOfUser(com.letstagon.dao.model.UserExperience)
	 */
	@Override
	public UserExperience saveVolunteerExperienceOfUser(UserExperience user) {
		user = userExperienceRepository.save(user);

		if (user != null && user.getUserBean() != null) {
			userService.updateModeifiedDate(user.getUserBean().getId());
		}

		return user;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserExperienceService#getUserExperienceList(long)
	 */
	@Override
	public HashMap<String, Object> getUserExperienceList(long id) {
		LOG.info("process to get user volunteer histroy page details for user in userService with id:" + id);
		// to get user educational experience list
		// TO get user professional experience list
		User user = new User();
		user.setId(id);
		List<String> typeList = new ArrayList<String>();
		typeList.add(UserExperienceTypeEnum.EDUCATION.getExperienceType());
		typeList.add(UserExperienceTypeEnum.PROFESSIONAL.getExperienceType());
		typeList.add(UserExperienceTypeEnum.VOLUNTEER.getExperienceType());
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<UserExperience> userExperienceList = userExperienceRepository.findByUserBeanAndTypeOrderByEndDateAsc(user,
				typeList);
		if (userExperienceList != null) {
			List<UserExperience> educationDetails = new ArrayList<UserExperience>();
			List<UserExperience> professionalDetails = new ArrayList<UserExperience>();
			List<UserExperience> volunteeringDetails = new ArrayList<UserExperience>();
			for (UserExperience userExperience : userExperienceList) {
				if (userExperience.getType().equals(UserExperienceTypeEnum.EDUCATION.getExperienceType())) {
					// Add to education list
					educationDetails.add(userExperience);
				} else if (userExperience.getType().equals(UserExperienceTypeEnum.PROFESSIONAL.getExperienceType())) {
					// Add to experience list
					professionalDetails.add(userExperience);
				} else if (userExperience.getType().equals(UserExperienceTypeEnum.VOLUNTEER.getExperienceType())) {
					// Add to experience list
					volunteeringDetails.add(userExperience);
				}

			}
			map.put("educationDetails", educationDetails);
			map.put("professionalDetails", professionalDetails);
			map.put("volunteeringDetails", volunteeringDetails);
		}

		return map;
	}

}
