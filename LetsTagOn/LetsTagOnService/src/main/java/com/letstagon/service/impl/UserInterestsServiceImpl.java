package com.letstagon.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.letstagon.dao.repository.AddnProfileAttribRepository;
import com.letstagon.dao.repository.CauseRepository;
import com.letstagon.dao.repository.JobTypeRepository;
import com.letstagon.dao.repository.LtoCustomRepository;
import com.letstagon.dao.repository.PartyCauseRepository;
import com.letstagon.dao.repository.PartyJobTypeMappingRepository;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.dao.repository.UserAddProfAttribRepository;
import com.letstagon.dao.repository.UserTypeRepository;
import com.letstagon.dao.repository.UserTypeXrefRepository;
import com.letstagon.dao.repository.VolunteerLocationPrefRepository;
import com.letstagon.dao.repository.VolunteerPrefRepository;
import com.letstagon.exception.NonFatalException;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.service.UserInterestsService;
import com.letstagon.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserInterestsServiceImpl.
 */
@Component
public class UserInterestsServiceImpl implements UserInterestsService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserInterestsServiceImpl.class);

	/** The volunteer pref repository. */
	@Autowired
	private VolunteerPrefRepository volunteerPrefRepository;

	/** The volunteer location pref repository. */
	@Autowired
	private VolunteerLocationPrefRepository volunteerLocationPrefRepository;

	/** The cause repository. */
	@Autowired
	private CauseRepository causeRepository;

	/** The party cause repository. */
	@Autowired
	private PartyCauseRepository partyCauseRepository;

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;

	/** The job type repository. */
	@Autowired
	private JobTypeRepository jobTypeRepository;
	
	/** The partyjob type xref. */
	@Autowired
	private PartyJobTypeMappingRepository partyjobTypeXref;

	/** The user type repository. */
	@Autowired
	private UserTypeRepository userTypeRepository;

	/** The user type xref repository. */
	@Autowired
	private UserTypeXrefRepository userTypeXrefRepository;

	/** The profile attrib repository. */
	@Autowired
	private AddnProfileAttribRepository profileAttribRepository;

	/** The add prof attrib repository. */
	@Autowired
	private UserAddProfAttribRepository addProfAttribRepository;

	/** The lto custom repository. */
	@Autowired
	private LtoCustomRepository ltoCustomRepository;

	/** The user service. */
	@Autowired
	private UserService userService;

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#addAvailabilityEntry(com.letstagon.dao.model.VolunteerPref)
	 */
	@Override
	public VolunteerPref addAvailabilityEntry(VolunteerPref preference) throws InvalidPreferenceException {
		if (preference.getUser() == null) {
			LOG.warn("User object to link preference with not provided");
			throw new InvalidPreferenceException("User to whom preference to be set not provided!");
		}

		if (!isPrefValid(preference)) {
			LOG.warn("Preference provided is invalid");
			throw new InvalidPreferenceException("Invalid preference");
		}
		VolunteerPref volunteerPref = volunteerPrefRepository.findByUserAndDayAndStartTimeAndEndTime(
				preference.getUser(), preference.getDay(), preference.getStartTime(), preference.getEndTime());
		if (volunteerPref != null) {
			preference.setId(volunteerPref.getId());
		}
		volunteerPrefRepository.save(preference);

		if (preference != null && preference.getUser() != null) {
			userService.updateModeifiedDate(preference.getUser().getId());
		}

		return preference;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#editAvailabilityEntry(com.letstagon.dao.model.VolunteerPref)
	 */
	@Override
	public VolunteerPref editAvailabilityEntry(VolunteerPref preference) throws InvalidPreferenceException {
		if (!isPrefValid(preference)) {
			LOG.warn("Preference provided is invalid");
			throw new InvalidPreferenceException("Invalid preference");
		}
		volunteerPrefRepository.save(preference);

		if (preference != null && preference.getUser() != null) {
			userService.updateModeifiedDate(preference.getUser().getId());
		}
		return preference;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#removeAvailabilityEntry(com.letstagon.dao.model.VolunteerPref)
	 */
	@Override
	public boolean removeAvailabilityEntry(VolunteerPref preference) {

		volunteerPrefRepository.delete(preference);

		return true;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#findByUser(com.letstagon.dao.model.User)
	 */
	@Override
	public List<VolunteerPref> findByUser(User user) {
		return volunteerPrefRepository.findByUser(user);
	}

	/**
	 * Checks if is pref valid.
	 *
	 * @param preference the preference
	 * @return true, if is pref valid
	 */
	public boolean isPrefValid(VolunteerPref preference) {
		if (preference == null) {
			return false;
		}

		if (preference.getDay() == null || preference.getStartTime() == null || preference.getEndTime() == null) {
			return false;
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#saveOrUpdateVolunteerLocationPref(com.letstagon.dao.model.VolunteerLocationPref)
	 */
	@Override
	public VolunteerLocationPref saveOrUpdateVolunteerLocationPref(VolunteerLocationPref pref)
			throws InvalidPreferenceException {
		// Method to save locationPref for particularVolunteer

		// TO check if isUser exisit
		if (pref.getUser() == null) {
			LOG.error("USer for whom location preferences to be saved is not found");
			throw new InvalidPreferenceException("User not found");
		}
		VolunteerLocationPref locationPref = volunteerLocationPrefRepository.findByUser(pref.getUser());
		if (locationPref != null) {
			pref.setId(locationPref.getId());
			pref.setInsertedDate(locationPref.getInsertedDate());
		} else {
			pref.setInsertedDate(new Date());
		}
		pref.setModifiedDate(new Date());

		if (pref != null && pref.getUser() != null) {
			userService.updateModeifiedDate(pref.getUser().getId());
		}

		return volunteerLocationPrefRepository.save(pref);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getVolunteerPreferredLocationDetails(long)
	 */
	@Override
	public VolunteerLocationPref getVolunteerPreferredLocationDetails(long id) {
		User user = new User();
		user.setId(id);
		VolunteerLocationPref locationPref = volunteerLocationPrefRepository.findByUser(user);
		return locationPref;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getCauseList(long)
	 */
	@Override
	public List<PartyCauseXref> getCauseList(long id) {
		// TODO Auto-generated method stub
		// TO get all cause
		List<PartyCauseXref> causeListOfUser = null;
		// TO get cause of the logged in user
		User userBean = new User();
		userBean.setId(id);
		Party partyDetails = partyRepository.findByUserBean(userBean);
		if (partyDetails != null) {
			// Check for entry in party jobtypexref
			causeListOfUser = partyCauseRepository.findByPartyBean(partyDetails);
		}
		return causeListOfUser;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#saveUserCauseDetails(com.letstagon.dao.model.PartyCauseXref)
	 */
	@Override
	public PartyCauseXref saveUserCauseDetails(PartyCauseXref causeXref) {
		if(causeXref==null){
			throw new InvalidParameterException("Invalid cause object.");
		}
		PartyCauseXref xref = partyCauseRepository.findByPartyBeanAndCauseBean(causeXref.getPartyBean(),
				causeXref.getCauseBean());
		if (xref == null) {
			xref = partyCauseRepository.save(causeXref);
		} else {
			xref.setStatus(causeXref.isStatus());
			xref = partyCauseRepository.save(xref);
		}

		if (causeXref != null && causeXref.getPartyBean() != null && causeXref.getPartyBean().getUserBean() != null) {
			userService.updateModeifiedDate(causeXref.getPartyBean().getUserBean().getId());
		}

		return xref;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getSkillList(long)
	 */
	@Override
	public List<PartyJobTypeXref> getSkillList(long id) {
		// TODO Auto-generated method stub
		// TO get all skills
		List<PartyJobTypeXref> skillsListOfUser = null;
		// TO get skills of the logged in user
		User user = new User();
		user.setId(id);
		Party partyDetails = partyRepository.findByUserBean(user);
		if (partyDetails != null) {
			// Check for entry in party jobtypexref
			skillsListOfUser = partyjobTypeXref.findByPartyBean(partyDetails);
		}
		return skillsListOfUser;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#saveUserSkill(com.letstagon.dao.model.PartyJobTypeXref)
	 */
	@Override
	public PartyJobTypeXref saveUserSkill(PartyJobTypeXref jobTypeXref) {
		PartyJobTypeXref xref = partyjobTypeXref.findByPartyBeanAndJobTypeBean(jobTypeXref.getPartyBean(),
				jobTypeXref.getJobTypeBean());
		if (xref == null) {
			xref = partyjobTypeXref.save(jobTypeXref);
		} else {
			xref.setStatus(jobTypeXref.getStatus());
			xref = partyjobTypeXref.save(xref);
		}

		if (jobTypeXref != null && jobTypeXref.getPartyBean() != null
				&& jobTypeXref.getPartyBean().getUserBean() != null) {
			userService.updateModeifiedDate(jobTypeXref.getPartyBean().getUserBean().getId());
		}

		return xref;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getUserTypeMasterData(long)
	 */
	@Override
	public List<UserType> getUserTypeMasterData(long id) {
		List<UserType> userTypelist = (List<UserType>) userTypeRepository.findAll();
		return userTypelist;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getUserTypeList(long)
	 */
	@Override
	public List<UserTypeXref> getUserTypeList(long id) {
		User user = new User();
		user.setId(id);
		List<UserTypeXref> list = userTypeXrefRepository.findByUser(user);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#saveOrUpdateUserType(com.letstagon.dao.model.UserTypeXref)
	 */
	@Override
	public UserTypeXref saveOrUpdateUserType(UserTypeXref userTypeXref) throws NonFatalException {
		if (userTypeXref.getUser() == null) {
			throw new NonFatalException("property user not found");
		}
		if (userTypeXref.getUserType() == null) {
			throw new NonFatalException(
					"property userType not found for user with id=" + userTypeXref.getUser().getId());
		}
		UserTypeXref typeXref = userTypeXrefRepository.findByUserAndUserType(userTypeXref.getUser(),
				userTypeXref.getUserType());
		if (typeXref != null) {
			userTypeXref.setId(typeXref.getId());
		}

		UserTypeXref xref = userTypeXrefRepository.save(userTypeXref);

		if (userTypeXref != null && userTypeXref.getUser() != null) {
			userService.updateModeifiedDate(userTypeXref.getUser().getId());
		}

		return xref;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getAdditionalAttributesForUserType(com.letstagon.dao.model.User)
	 */
	/*
	 * public List<AdditionalProfileAttribute>
	 * getAdditionalAttributesForUserType(long userId){ User user = new
	 * User(userId); List<AdditionalProfileAttribute> profileAttributes = new
	 * ArrayList<AdditionalProfileAttribute>(); List<UserTypeXref> xrefs =
	 * userTypeXrefRepository.findByUser(user); for (UserTypeXref userTypeXref :
	 * xrefs) { UserTypeAttributeMapEnum attributeMapEnum =
	 * UserTypeAttributeMapEnum.getDetails(userTypeXref.getUserType().getId());
	 * String[] additionalAttributes =
	 * attributeMapEnum.getAdditionalAttribute(); for (String attributeId :
	 * additionalAttributes) { AdditionalAttributeEnum additionalAttributeEnum =
	 * AdditionalAttributeEnum.getDetailsById(Integer.parseInt(attributeId));
	 * AdditionalProfileAttribute additionalProfileAttribute = new
	 * AdditionalProfileAttribute();
	 * additionalProfileAttribute.setDescription(additionalAttributeEnum.
	 * getDescription());
	 * additionalProfileAttribute.setId(additionalAttributeEnum.getId());
	 * additionalProfileAttribute.setName(additionalAttributeEnum.getName());
	 * profileAttributes.add(additionalProfileAttribute); } } return
	 * profileAttributes;
	 * 
	 * }
	 */
	@Override
	public List<AdditionalProfileAttribute> getAdditionalAttributesForUserType(User user) {
		List<AdditionalProfileAttribute> profileAttributes = new ArrayList<AdditionalProfileAttribute>();
		List<UserType> userTypes = ltoCustomRepository.findUserTypeByUser(user);
		if (userTypes.size() > 0) {
			profileAttributes = profileAttribRepository.findAttibutesForTypes(userTypes);
			return profileAttributes;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getMasterSkillList()
	 */
	@Override
	public Iterable<JobType> getMasterSkillList() {
		return jobTypeRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getMasterCauseList()
	 */
	@Override
	public Iterable<Cause> getMasterCauseList() {
		return causeRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserInterestsService#getUserAdditionalAttributesForUserType(com.letstagon.dao.model.UserTypeXref)
	 */
	@Override
	public List<UserAdditionalProfileAttribute> getUserAdditionalAttributesForUserType(UserTypeXref userTypeXref) {
		List<UserAdditionalProfileAttribute> userAdditionalProfileAttributes = new ArrayList<UserAdditionalProfileAttribute>();
		List<AdditionalProfileAttribute> profileAttributes = new ArrayList<AdditionalProfileAttribute>();
		List<UserType> userTypes = ltoCustomRepository.findUserTypeByUser(userTypeXref.getUser());
		if (userTypes.size() > 0) {
			profileAttributes = profileAttribRepository.findAttibutesForTypes(userTypes);
			if (profileAttributes.size() > 0) {
				for (AdditionalProfileAttribute additionalProfileAttribute2 : profileAttributes) {
					UserAdditionalProfileAttribute additionalProfileAttribute = addProfAttribRepository
							.findByAdditionalProfileAttributeAndUserBean(additionalProfileAttribute2,
									userTypeXref.getUser());
					if (additionalProfileAttribute != null) {
						userAdditionalProfileAttributes.add(additionalProfileAttribute);
					}
				}
				return userAdditionalProfileAttributes;
			}
		}
		return null;
	}

}
