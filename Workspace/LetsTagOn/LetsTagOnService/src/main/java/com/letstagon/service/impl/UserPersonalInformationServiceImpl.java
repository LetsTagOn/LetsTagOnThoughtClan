package com.letstagon.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.ProfileCompletionStatusModel;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;
import com.letstagon.dao.model.UserExperience;
import com.letstagon.dao.repository.AddressRepository;
import com.letstagon.dao.repository.UserAddProfAttribRepository;
import com.letstagon.dao.repository.UserRepository;
import com.letstagon.dao.repository.UserTypeAttribMappingRepository;
import com.letstagon.enums.AdditionalAttributeEnum;
import com.letstagon.enums.UserExperienceTypeEnum;
import com.letstagon.exception.NonFatalException;
import com.letstagon.service.PartyService;
import com.letstagon.service.UserPersonalInformationService;
import com.letstagon.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserPersonalInformationServiceImpl.
 */
@Component
public class UserPersonalInformationServiceImpl implements UserPersonalInformationService {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserPersonalInformationServiceImpl.class);
	
	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;
	
	/** The user additional profile attribute. */
	@Autowired
	private UserAddProfAttribRepository userAdditionalProfileAttribute;
	
	/** The user type attrib mapping repository. */
	@Autowired
	private UserTypeAttribMappingRepository userTypeAttribMappingRepository;
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The party service. */
	@Autowired
	private PartyService partyService;

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserPersonalInformationService#saveOrUpdateProfileForm(com.letstagon.dao.model.User)
	 */
	@Override
	public User saveOrUpdateProfileForm(User userDetails) throws Exception {
		User userModal = userRepository.findOneByEmailAddress(userDetails.getEmailAddress());
		Address address = new Address();
		if(userModal != null){
			if(userModal.getAddressBean() != null){
				userModal.getAddressBean().setCity(userDetails.getAddressBean().getCity());
				userModal.getAddressBean().setState(userDetails.getAddressBean().getState());
				userModal.getAddressBean().setStreet(userDetails.getAddressBean().getStreet());
				userModal.getAddressBean().setCountry(userDetails.getAddressBean().getCountry());
				userModal.getAddressBean().setPostalCode(userDetails.getAddressBean().getPostalCode());
				userModal.getAddressBean().setFormattedAddress(userDetails.getAddressBean().getFormattedAddress());
			}else {
				userModal.setAddressBean(userDetails.getAddressBean());
			}
			address = addressRepository.save(userModal.getAddressBean());				
			userModal.setFirstName(userDetails.getFirstName());
			userModal.setLastName(userDetails.getLastName());
			userModal.setName(userDetails.getName());
			userModal.setDateOfBirth(userDetails.getDateOfBirth());
			userModal.setGender(userDetails.getGender());
			userModal.setPhoneNumber(userDetails.getPhoneNumber());
			userModal.setSummary(userDetails.getSummary());
			userModal.setAddressBean(address);
			
		}else {
			
			userModal = new User();
			userModal = userDetails;			
			userModal.setAddressBean(address);
			userModal.setModifiedDate(new Date());
		}
		
		User user = userRepository.save(userModal);

		return user;
	}
	

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserPersonalInformationService#saveAdditionalAttributesValuesForUser(com.letstagon.dao.model.User)
	 */
	@Override
	public List<UserAdditionalProfileAttribute> saveAdditionalAttributesValuesForUser(User user)
			throws NonFatalException {
		List<UserAdditionalProfileAttribute> list = new ArrayList<UserAdditionalProfileAttribute>();
		for (UserAdditionalProfileAttribute element : user.getUserAdditionalProfileAttributes()) {

			AdditionalAttributeEnum additionalattribute = AdditionalAttributeEnum
					.getDetails(element.getAdditionalProfileAttribute().getName());
			if (additionalattribute != null) {

				AdditionalProfileAttribute attribute = new AdditionalProfileAttribute();
				attribute.setId(additionalattribute.getId());
				UserAdditionalProfileAttribute attribute2 = userAdditionalProfileAttribute
						.findByAdditionalProfileAttributeAndUserBean(attribute, user);
				if (attribute2 != null) {
					attribute2.setValue(element.getValue());
				} else {
					attribute2 = new UserAdditionalProfileAttribute();
					attribute2.setAdditionalProfileAttribute(attribute);
					attribute2.setUserBean(user);
					attribute2.setValue(element.getValue());
				}
				UserAdditionalProfileAttribute attibute = userAdditionalProfileAttribute.save(attribute2);
				list.add(attibute);
			} else {
				LOG.error("Attribute not found for attribute name" + element.getAdditionalProfileAttribute().getName());
				throw new NonFatalException(
						"Attribute not found for attribute name" + element.getAdditionalProfileAttribute().getName());

			}
		}

		if (user != null) {
			userService.updateModeifiedDate(user.getId());
		}
		return list;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserPersonalInformationService#getProfileCompletionStatus(long)
	 */
	@Override
	public ProfileCompletionStatusModel getProfileCompletionStatus(long userID) {

		ProfileCompletionStatusModel status = new ProfileCompletionStatusModel();

		User user = this.userRepository.findOne(userID);

		if (user == null) {
			throw new InvalidParameterException("Invalid user ID.");
		}

		float completionPercent = 0.0F;
		// personal information-30%

		// name, dob
		if (StringUtils.isEmpty(user.getFirstName())) {
			status.getAdvice().add("Personal information : First name needs to be filled.");
		} else {
			completionPercent += .05;
		}

		if (StringUtils.isEmpty(user.getLastName())) {
			status.getAdvice().add("Personal information : Last name needs to be filled.");
		} else {
			completionPercent += .05;
		}

		// address
		if (user.getAddressBean() == null || StringUtils.isEmpty(user.getAddressBean().getCity())) {
			status.getAdvice().add("Personal information : City needs to be filled.");
		} else {
			completionPercent += .1;
		}

		// summary
		if (StringUtils.isEmpty(user.getSummary())) {
			status.getAdvice().add("Personal information : Summary needs to be filled.");
		} else {
			completionPercent += .1;
		}

		// type of commitment-5%
		// additional attributes
		if (CollectionUtils.isEmpty(user.getUserTypeXrefs())
				|| CollectionUtils.isEmpty(user.getUserAdditionalProfileAttributes())) {
			status.getAdvice().add("Personal information : Fill committments you are open for.");
		} else {
			completionPercent += .05;
		}

		// Experience-30%

		if (CollectionUtils.isEmpty(user.getUserExperiences())) {
			status.getAdvice().add("Experience information : Add your experiences and skills.");
		} else {

			boolean eduExp = false;
			boolean profExp = false;
			boolean volExp = false;
			for (UserExperience exp : user.getUserExperiences()) {

				if (exp.getType() != null
						&& exp.getType().equals(UserExperienceTypeEnum.EDUCATION.getExperienceType())) {
					eduExp = true;
				}
				if (exp.getType() != null
						&& exp.getType().equals(UserExperienceTypeEnum.PROFESSIONAL.getExperienceType())) {
					profExp = true;
				}
				if (exp.getType() != null
						&& exp.getType().equals(UserExperienceTypeEnum.VOLUNTEER.getExperienceType())) {
					volExp = true;
				}

			}

			// educational experience

			if (!eduExp) {
				status.getAdvice().add("Experience information : Add your educational skills.");
			}
			// professional experience
			if (!profExp) {
				status.getAdvice().add("Experience information : Add your professional experience.");
			}
			// volunteering experience
			if (!volExp) {
				status.getAdvice().add("Experience information : Add your volunteering experience.");
			}

			completionPercent += .3;

		}

		// Interests-30%

		// ------Location
		// ------Time

		if (CollectionUtils.isEmpty(user.getVolunteerPrefs())) {
			status.getAdvice().add("Interests : Please select your volunteering preference.");
		} else {
			completionPercent += .15;
		}

		Party partyModel = partyService.findByUserBean(user);
		if (partyModel == null) {

		} else {
			// ------Causes
			if (CollectionUtils.isEmpty(partyModel.getPartyCauseXrefs())) {
				status.getAdvice().add("Interests : Please select the causes you support.");
			} else {
				completionPercent += .10;
			}

			// ------Skills
			if (CollectionUtils.isEmpty(partyModel.getPartyJobTypeXrefs())) {
				status.getAdvice().add("Interests : Please select the skills you excell at.");
			} else {
				completionPercent += .10;
			}

		}
		status.setCompletion(completionPercent);

		return status;
	}
}
