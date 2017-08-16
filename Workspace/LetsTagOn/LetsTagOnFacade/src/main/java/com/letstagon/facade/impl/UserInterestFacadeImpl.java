package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyCauseXref;
import com.letstagon.dao.model.PartyJobTypeXref;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.model.UserTypeXref;
import com.letstagon.dao.model.VolunteerLocationPref;
import com.letstagon.dao.model.VolunteerPref;
import com.letstagon.exception.NonFatalException;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.UserInterestFacade;
import com.letstagon.facade.converter.AdditionProfileAttributeModalConverter;
import com.letstagon.facade.converter.CauseModalConverter;
import com.letstagon.facade.converter.JobTypeModelConverter;
import com.letstagon.facade.converter.PartyCauseXrefModalConverter;
import com.letstagon.facade.converter.PartyJobTypeXrefModalConverter;
import com.letstagon.facade.converter.UserAdditionalProfileAttributeModalConverter;
import com.letstagon.facade.converter.UserTypeModalConverter;
import com.letstagon.facade.dto.AdditionalProfileAttributeDTO;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.PartyCauseXrefDTO;
import com.letstagon.facade.dto.PartyJobTypeXrefDTO;
import com.letstagon.facade.dto.UserAdditionalProfileAttributeDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserTypeDTO;
import com.letstagon.facade.dto.UserTypeXrefDTO;
import com.letstagon.facade.dto.VolunteerLocationPrefDTO;
import com.letstagon.facade.dto.VolunteerPrefDTO;
import com.letstagon.service.UserInterestsService;
import com.letstagon.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserInterestFacadeImpl.
 */
@Component
public class UserInterestFacadeImpl implements UserInterestFacade {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserInterestFacadeImpl.class);

	/** The user interests service. */
	@Autowired
	private UserInterestsService userInterestsService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The job type model converter. */
	@Autowired
	private JobTypeModelConverter jobTypeModelConverter;

	/** The cause modal converter. */
	@Autowired
	private CauseModalConverter causeModalConverter;

	/** The user type modal converter. */
	@Autowired
	private UserTypeModalConverter userTypeModalConverter;

	/** The user additional profile attribute modal converter. */
	@Autowired
	private UserAdditionalProfileAttributeModalConverter userAdditionalProfileAttributeModalConverter;

	/** The addition profile attribute modal converter. */
	@Autowired
	private AdditionProfileAttributeModalConverter additionProfileAttributeModalConverter;

	/** The party job type xref modal converter. */
	@Autowired
	private PartyJobTypeXrefModalConverter partyJobTypeXrefModalConverter;

	/** The party cause xref modal converter. */
	@Autowired
	private PartyCauseXrefModalConverter partyCauseXrefModalConverter;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#addAvailabilityEntry(long, com.letstagon.facade.dto.VolunteerPrefDTO)
	 */
	@Override
	public VolunteerPrefDTO addAvailabilityEntry(long id, VolunteerPrefDTO preference)
			throws InvalidPreferenceException {
		LOG.info("save or update of  availabilty for user with id:" + id);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		VolunteerPref prefModel = modelMapper.map(preference, VolunteerPref.class);
		User user = new User();
		user.setId(id);
		prefModel.setUser(user);
		prefModel = userInterestsService.addAvailabilityEntry(prefModel);
		return modelMapper.map(prefModel, VolunteerPrefDTO.class);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#editAvailabilityEntry(com.letstagon.facade.dto.VolunteerPrefDTO)
	 */
	@Override
	public VolunteerPrefDTO editAvailabilityEntry(VolunteerPrefDTO preference) throws InvalidPreferenceException {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		VolunteerPref prefModel = modelMapper.map(preference, VolunteerPref.class);

		prefModel = userInterestsService.editAvailabilityEntry(prefModel);

		return modelMapper.map(prefModel, VolunteerPrefDTO.class);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#removeAvailabilityEntry(com.letstagon.facade.dto.VolunteerPrefDTO)
	 */
	@Override
	public boolean removeAvailabilityEntry(VolunteerPrefDTO preference) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		VolunteerPref prefModel = modelMapper.map(preference, VolunteerPref.class);

		return userInterestsService.removeAvailabilityEntry(prefModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#findByUser(com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public List<VolunteerPrefDTO> findByUser(UserDTO user) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		User userModel = modelMapper.map(user, User.class);
		List<VolunteerPref> prefList = userInterestsService.findByUser(userModel);

		List<VolunteerPrefDTO> prefDtoList = new ArrayList<VolunteerPrefDTO>();

		for (VolunteerPref prefModel : prefList) {

			prefDtoList.add(modelMapper.map(prefModel, VolunteerPrefDTO.class));

		}

		return prefDtoList;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#saveOrUpdateVolunteerLocationPref(long, com.letstagon.facade.dto.VolunteerLocationPrefDTO)
	 */
	@Override
	public VolunteerLocationPrefDTO saveOrUpdateVolunteerLocationPref(long id, VolunteerLocationPrefDTO prefDTO)
			throws InvalidPreferenceException {
		VolunteerLocationPref locationPref = mapper.map(prefDTO, VolunteerLocationPref.class);
		User user = new User();
		user.setId(id);
		locationPref.setUser(user);
		locationPref = userInterestsService.saveOrUpdateVolunteerLocationPref(locationPref);
		VolunteerLocationPrefDTO locationPrefDTO = mapper.map(locationPref, VolunteerLocationPrefDTO.class);
		return locationPrefDTO;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#getVolunteerInterestDetails(long)
	 */
	@Override
	public HashMap<String, Object> getVolunteerInterestDetails(long id) {
		LOG.info("process to get user volunteer histroy page details for user in userFacade with id:" + id);

		/*
		 * Get volunteer type master list Get userType of current user Get
		 * skills of current user Get causes of current user volunteer history
		 * details Step2: getting volunteer preferred location details Step3:
		 * getting volunteer availabilty list
		 */

		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> skillsMap = this.getSkillsList(id);

		if (!skillsMap.isEmpty()) {
			map.put("skills", skillsMap);
		}
		HashMap<String, Object> causesMap = this.getCauseList(id);

		if (!causesMap.isEmpty()) {
			map.put("causes", causesMap);
		}

		VolunteerLocationPref locationPref = userInterestsService.getVolunteerPreferredLocationDetails(id);

		if (locationPref != null) {
			VolunteerLocationPrefDTO locationPrefDTO = mapper.map(locationPref, VolunteerLocationPrefDTO.class);
			map.put("locationPreference", locationPrefDTO);
		}

		User user = new User();
		user.setId(id);
		List<VolunteerPref> volunteerPrefs = userInterestsService.findByUser(user);
		if (volunteerPrefs != null) {
			List<VolunteerPrefDTO> prefDTOs = new ArrayList<VolunteerPrefDTO>();
			for (VolunteerPref volunteerPref : volunteerPrefs) {
				VolunteerPrefDTO dto = mapper.map(volunteerPref, VolunteerPrefDTO.class);
				prefDTOs.add(dto);
			}
			map.put("availabiltyList", prefDTOs);
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#getCauseList(long)
	 */
	@Override
	public HashMap<String, Object> getCauseList(long id) {
		// TO get all cause from master table
		// TO get cause selected by user , if not present then show all with
		// checked or if present show all along with the ones user slected
		List<PartyCauseXref> skillsListOfUser = userInterestsService.getCauseList(id);

		Iterable<CauseDTO> masterList = getMasterCauseList();
		List<PartyCauseXrefDTO> userCauseList = new ArrayList<PartyCauseXrefDTO>();
		HashMap<String, Object> response = new HashMap<String, Object>();

		if (skillsListOfUser != null) {
			for (PartyCauseXref causeXref : skillsListOfUser) {

				userCauseList.add(partyCauseXrefModalConverter.convert(causeXref));
			}
		}
		response.put("masterCauses", masterList);
		response.put("userCauses", userCauseList);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#saveCausesOfUser(java.util.List)
	 */
	@Override
	public List<CauseDTO> saveCausesOfUser(List<PartyCauseXrefDTO> causeXrefDTOs) {
		List<CauseDTO> userCauseDTOs = new ArrayList<CauseDTO>();
		for (PartyCauseXrefDTO partyCauseXrefDTO : causeXrefDTOs) {

			PartyCauseXref partyCauseXref = mapper.map(partyCauseXrefDTO, PartyCauseXref.class);
			Party partyBean = userService.saveOrUpdateUser(partyCauseXref.getPartyBean());
			partyCauseXref.setPartyBean(partyBean);
			partyCauseXref = userInterestsService.saveUserCauseDetails(partyCauseXref);
			CauseDTO skillsDTO = causeModalConverter.convert(partyCauseXref.getCauseBean());
			userCauseDTOs.add(skillsDTO);
		}
		return userCauseDTOs;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#getSkillsList(long)
	 */
	@Override
	public HashMap<String, Object> getSkillsList(long id) {
		// TO get all skills from master table
		// TO get skills selected by user , if not present then show all with
		// checked or if present show all along with the ones user slected
		List<PartyJobTypeXref> skillsListOfUser = userInterestsService.getSkillList(id);
		List<JobTypeDTO> masterSkills = getMasterSkillList();
		List<PartyJobTypeXrefDTO> userSkills = new ArrayList<PartyJobTypeXrefDTO>();
		HashMap<String, Object> response = new HashMap<String, Object>();

		if (skillsListOfUser != null) {
			for (PartyJobTypeXref jobTypeXref : skillsListOfUser) {
				userSkills.add(partyJobTypeXrefModalConverter.convert(jobTypeXref));
			}

		}
		response.put("userSkills", userSkills);
		response.put("masterSkills", masterSkills);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#saveSkillsOfUser(java.util.List)
	 */
	@Override
	public List<JobTypeDTO> saveSkillsOfUser(List<PartyJobTypeXrefDTO> jobTypeXrefs) {
		List<JobTypeDTO> userSkillsDTOs = new ArrayList<JobTypeDTO>();
		for (PartyJobTypeXrefDTO partyJobTypeXrefDTO : jobTypeXrefs) {

			PartyJobTypeXref partyJobTypeXref = mapper.map(partyJobTypeXrefDTO, PartyJobTypeXref.class);
			Party partyBean = userService.saveOrUpdateUser(partyJobTypeXref.getPartyBean());
			partyJobTypeXref.setPartyBean(partyBean);
			partyJobTypeXref = userInterestsService.saveUserSkill(partyJobTypeXref);
			JobTypeDTO skillsDTO = jobTypeModelConverter.convert(partyJobTypeXref.getJobTypeBean());
			userSkillsDTOs.add(skillsDTO);
		}
		return userSkillsDTOs;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#saveOrUpdateUserType(com.letstagon.facade.dto.UserTypeXrefDTO, long)
	 */
	@Override
	public HashMap<String, Object> saveOrUpdateUserType(UserTypeXrefDTO userType, long userId)
			throws NonFatalException {
		// Saves UserTyeps and Paints the addtional Attribute questions
		// everytime
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		UserTypeXref xref = mapper.map(userType, UserTypeXref.class);
		User user = new User();
		user.setId(userId);
		xref.setUser(user);
		xref = userInterestsService.saveOrUpdateUserType(xref);

		UserTypeXrefDTO dto = new UserTypeXrefDTO();
		dto.setId(xref.getId());

		dto.setActive(xref.isActive());
		UserType userTypeModel = xref.getUserType();
		UserTypeDTO userTypeDTO = new UserTypeDTO();

		userTypeDTO.setId(userTypeModel.getId());
		userTypeDTO.setName(userTypeModel.getName());
		userTypeDTO.setDescription(userTypeModel.getDescription());

		dto.setUserType(userTypeDTO);

		hashMap.put("userType", dto);
		List<AdditionalProfileAttribute> profileAttributes = userInterestsService
				.getAdditionalAttributesForUserType(user);
		List<AdditionalProfileAttributeDTO> additionalProfileAttributeDTOs = new ArrayList<AdditionalProfileAttributeDTO>();
		if (profileAttributes != null) {
			for (AdditionalProfileAttribute additionalProfileAttribute : profileAttributes) {
				additionalProfileAttributeDTOs
						.add(additionProfileAttributeModalConverter.convert(additionalProfileAttribute));
			}
			hashMap.put("additionalAttribute", additionalProfileAttributeDTOs);
		}

		List<UserAdditionalProfileAttributeDTO> userAdditionalProfileAttributesDTOs = new ArrayList<UserAdditionalProfileAttributeDTO>();
		List<UserAdditionalProfileAttribute> userAdditionalProfileAttributes = userInterestsService
				.getUserAdditionalAttributesForUserType(xref);
		if (userAdditionalProfileAttributes != null) {
			for (UserAdditionalProfileAttribute userAdditionalProfileAttribute : userAdditionalProfileAttributes) {

				AdditionalProfileAttribute addAttribModel = userAdditionalProfileAttribute
						.getAdditionalProfileAttribute();

				UserAdditionalProfileAttributeDTO addProfattDto = new UserAdditionalProfileAttributeDTO();
				AdditionalProfileAttributeDTO addAttribDto = new AdditionalProfileAttributeDTO();

				addAttribDto.setId(addAttribModel.getId());
				addAttribDto.setName(addAttribModel.getName());
				addAttribDto.setDescription(addAttribModel.getDescription());

				addProfattDto.setId(userAdditionalProfileAttribute.getId());
				addProfattDto.setValue(userAdditionalProfileAttribute.getValue());
				addProfattDto.setAdditionalProfileAttribute(addAttribDto);
				userAdditionalProfileAttributesDTOs.add(addProfattDto);

			}
			hashMap.put("userAdditionalAttribute", userAdditionalProfileAttributesDTOs);
		}
		return hashMap;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#getMasterCauseList()
	 */
	@Override
	public List<CauseDTO> getMasterCauseList() {
		List<CauseDTO> causeDTOs = new ArrayList<CauseDTO>();
		Iterable<Cause> iterable = userInterestsService.getMasterCauseList();
		for (Cause cause : iterable) {
			causeDTOs.add(causeModalConverter.convert(cause));
		}
		return causeDTOs;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserInterestFacade#getMasterSkillList()
	 */
	@Override
	public List<JobTypeDTO> getMasterSkillList() {
		List<JobTypeDTO> dtos = new ArrayList<JobTypeDTO>();
		Iterable<JobType> iterable = userInterestsService.getMasterSkillList();
		for (JobType jobType : iterable) {
			dtos.add(jobTypeModelConverter.convert(jobType));
		}
		return dtos;
	}

}
