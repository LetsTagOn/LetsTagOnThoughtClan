package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.NonUniqueResultException;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyCauseXref;
import com.letstagon.dao.model.PartyJobTypeXref;
import com.letstagon.dao.model.PartyParticipation;
import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.dao.model.User;
import com.letstagon.exception.NonFatalException;
import com.letstagon.facade.UserExperienceFacade;
import com.letstagon.facade.UserFacade;
import com.letstagon.facade.UserPersonalInformationFacade;
import com.letstagon.facade.VolunteerConnectionsFacade;
import com.letstagon.facade.converter.PartyCauseXrefModalConverter;
import com.letstagon.facade.converter.PartyJobTypeXrefModalConverter;
import com.letstagon.facade.converter.PartyModelConverter;
import com.letstagon.facade.converter.PartyParticipationConverter;
import com.letstagon.facade.converter.PrivacySettingsModalConverter;
import com.letstagon.facade.converter.UserModelConverter;
import com.letstagon.facade.dto.ConnectionDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyCauseXrefDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PartyJobTypeXrefDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.service.PartyParticipationService;
import com.letstagon.service.PartyService;
import com.letstagon.service.UserInterestsService;
import com.letstagon.service.UserPersonalInformationService;
import com.letstagon.service.UserPrivacySettingsService;
import com.letstagon.service.UserService;
import com.letstagon.service.VolunteerConnectionsService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserFacadeImpl.
 */
@Component
public class UserFacadeImpl implements UserFacade {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserFacadeImpl.class);
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The user experience facade. */
	@Autowired
	private UserExperienceFacade userExperienceFacade;
	
	/** The user personal information facade. */
	@Autowired
	private UserPersonalInformationFacade userPersonalInformationFacade;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The user model converter. */
	@Autowired
	private UserModelConverter userModelConverter;
	
	/** The party model converter. */
	@Autowired
	private PartyModelConverter partyModelConverter;
	
	/** The job type xref modal converter. */
	@Autowired
	private PartyJobTypeXrefModalConverter jobTypeXrefModalConverter;
	
	/** The party cause xref modal converter. */
	@Autowired
	private PartyCauseXrefModalConverter partyCauseXrefModalConverter;
	
	/** The user interests service. */
	@Autowired
	private UserInterestsService userInterestsService;
	
	/** The volunteer connection facade. */
	@Autowired
	private VolunteerConnectionsFacade volunteerConnectionFacade;
	
	/** The user personal information service. */
	@Autowired
	private UserPersonalInformationService userPersonalInformationService;
	
	/** The user modal converter. */
	@Autowired
	private Converter<User, UserDTO> userModalConverter;
	
	/** The volunteer connections service. */
	@Autowired
	private VolunteerConnectionsService volunteerConnectionsService;
	
	/** The participation service. */
	@Autowired
	private PartyParticipationService participationService;
	
	/** The party service. */
	@Autowired
	private PartyService partyService;
	
	/** The party participation converter. */
	@Autowired
	private PartyParticipationConverter partyParticipationConverter;
	
	/** The privacy settings modal converter. */
	@Autowired
	private PrivacySettingsModalConverter privacySettingsModalConverter;
	
	/** The user privacy settings service. */
	@Autowired
	private UserPrivacySettingsService userPrivacySettingsService;
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#create(com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public UserDTO create(UserDTO form) {
		/*
		 * TO check for user exsistance If user exist throw exception else
		 * create user into system
		 */
		if(form != null && form.getUserName() == null){
			form.setUserName(form.getEmailAddress());
		}
		List<User> exsistingUserDetails = userService.getUserByEmailOrUserName(form.getEmailAddress(),
				form.getUserName());
		User user = new User();
		if (exsistingUserDetails.isEmpty()) {			
			user = mapper.map(form, User.class);
			user = userService.create(user);
			LOG.info("User successfully created email:" + user.getEmailAddress() + " and user id:" + user.getId());
		} else {
			throw new NonUniqueResultException();
		}
		form = mapper.map(user, UserDTO.class);
		return form;
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#updatePassword(java.lang.String, java.lang.String)
	 */
	@Override
	
	public UserDTO updatePassword(String userName, String password) {
		User user = userService.updatePassword(userName, password);
		UserDTO userDto = mapper.map(user, UserDTO.class);
		return userDto;
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#getUserDetailsByEmail(java.lang.String)
	 */
	@Override
	public UserDTO getUserDetailsByEmail(String emailAddress) {
		Optional<User> user = userService.getUserByEmail(emailAddress);
		if(!user.isPresent()){
			return null;
		}
		User userDetails = user.get();
		UserDTO userDto = this.userModelConverter.convert(userDetails);
		return userDto;
	}
	
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#getUserDetails(long)
	 */
	@Override
	public UserDTO getUserDetails(long userId) {
		Optional<User> user = userService.getUserById(userId);
		User userDetails = user.get();
		UserDTO userDto = this.userModelConverter.convert(userDetails);
		return userDto;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#getUserProfileDetails(long)
	 */
	@Override
	public HashMap<String, Object> getUserProfileDetails(long id) {
		HashMap<String, Object> userProfileDetailsMap = new HashMap<String, Object>();
		Optional<User> user = userService.getUserById(id);
		User userDetails = user.get();
		UserDTO userDto = userModalConverter.convert(userDetails);		
		userProfileDetailsMap.put("userExperience", userExperienceFacade.getUserExperienceList(id));
		userProfileDetailsMap.put("userDetails",userDto);
		List<ConnectionDTO> connectionList = volunteerConnectionFacade.getConnectionListOfUser(id);
		userProfileDetailsMap.put("connections",volunteerConnectionFacade.getConnectionListOfUser(id));
		List<PartyCauseXref> causeXrefs = userInterestsService.getCauseList(id);
		if(causeXrefs != null){
			List<PartyCauseXrefDTO> causeXrefsDTOs = new ArrayList<PartyCauseXrefDTO>();
			for (PartyCauseXref partyCauseXref : causeXrefs) {
				causeXrefsDTOs.add(this.partyCauseXrefModalConverter.convert(partyCauseXref));
			}
			userProfileDetailsMap.put("causes",causeXrefsDTOs);
		}
		List<PartyJobTypeXref> jobTypeXrefs = userInterestsService.getSkillList(id);
		if(jobTypeXrefs != null){
			List<PartyJobTypeXrefDTO> jobTypeXrefDTOs = new ArrayList<PartyJobTypeXrefDTO>();
			for (PartyJobTypeXref jobTypeXref : jobTypeXrefs) {
				jobTypeXrefDTOs.add(this.jobTypeXrefModalConverter.convert(jobTypeXref));
			}
			userProfileDetailsMap.put("skills",jobTypeXrefDTOs);
		}
		//To get user connection list number
		userProfileDetailsMap.put("volunteerConnectionCount", connectionList.size());
		
		//To get user Attended Opportunity List
		PartyParticipation party = new PartyParticipation();
		party.setPartyBean(partyService.findByUserBean(userDetails));
		party.setStatus(true);
		party.setAttendance(true);
		List<PartyParticipation> userOpportunityList = participationService.findOpportunitiesListOfParty(party);
		userProfileDetailsMap.put("volunteerOpportunityCount", userOpportunityList.size());
		userProfileDetailsMap.put("partyParticipation",partyParticipationConverter.convert(party));
		
		//Knowing his privacy settings
		PrivacySettings privacy = userPrivacySettingsService.getUserPrivacySettings(id);
		userProfileDetailsMap.put("privacySettingData",privacySettingsModalConverter.convert(privacy));
		
		return userProfileDetailsMap;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#getUserList(com.letstagon.facade.dto.UserDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO getUserList(UserDTO volunteer, int page, int size) {
		User volunteerModel = mapper.map(volunteer, User.class);
		PaginatedSearchResponseModel result = userService.getConnectionSuggestionList(volunteerModel, page, size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}
	
	/**
	 * Convert to DTO list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	private List<PartyDTO> convertToDTOList(List<? extends Object> modelList) {
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}
		ArrayList<PartyDTO> dtoList = new ArrayList<PartyDTO>();
		for (Object model : modelList) {
			Party connection = (Party) model;
			dtoList.add(this.partyModelConverter.convert(connection));
		}
		return dtoList;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#getUserPartyDetails(com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public PartyDTO getUserPartyDetails(UserDTO user) {
		User volunteerModel = mapper.map(user, User.class);
		Party party = userService.getUserPartyDetails(volunteerModel);
		if(party != null){
			return mapper.map(party, PartyDTO.class);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserFacade#saveAdditionalAttributesValuesForUser(com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public void saveAdditionalAttributesValuesForUser(
			UserDTO user) throws NonFatalException {
		User volunteerModel = mapper.map(user, User.class);
		userPersonalInformationService.saveAdditionalAttributesValuesForUser(volunteerModel);
	}
	
}
