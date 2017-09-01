package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.ProfileCompletionStatusModel;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.model.UserTypeXref;
import com.letstagon.facade.UserPersonalInformationFacade;
import com.letstagon.facade.converter.AdditionProfileAttributeModalConverter;
import com.letstagon.facade.converter.UserAdditionalProfileAttributeModalConverter;
import com.letstagon.facade.converter.UserTypeModalConverter;
import com.letstagon.facade.dto.AdditionalProfileAttributeDTO;
import com.letstagon.facade.dto.ProfileCompletionStatusDTO;
import com.letstagon.facade.dto.UserAdditionalProfileAttributeDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserTypeDTO;
import com.letstagon.facade.dto.UserTypeXrefDTO;
import com.letstagon.service.UserInterestsService;
import com.letstagon.service.UserPersonalInformationService;
import com.letstagon.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserPersonalInformationFacadeImpl.
 */
@Component
public class UserPersonalInformationFacadeImpl implements UserPersonalInformationFacade {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The information service. */
	@Autowired
	private UserPersonalInformationService informationService;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The user modal converter. */
	@Autowired
	private Converter<User, UserDTO> userModalConverter;

	/** The user interests service. */
	@Autowired
	private UserInterestsService userInterestsService;

	/** The user type modal converter. */
	@Autowired
	private UserTypeModalConverter userTypeModalConverter;

	/** The user additional profile attribute modal converter. */
	@Autowired
	private UserAdditionalProfileAttributeModalConverter userAdditionalProfileAttributeModalConverter;

	/** The addition profile attribute modal converter. */
	@Autowired
	private AdditionProfileAttributeModalConverter additionProfileAttributeModalConverter;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserPersonalInformationFacade#saveOrUpdateProfileForm(com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public UserDTO saveOrUpdateProfileForm(UserDTO userDetails) throws Exception {

		User user = mapper.map(userDetails, User.class);
		user = informationService.saveOrUpdateProfileForm(user);
		UserDTO userDTO = userModalConverter.convert(user);
		return userDTO;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserPersonalInformationFacade#getUserDetails(long)
	 */
	@Override
	public HashMap<String, Object> getUserDetails(long userId) {
		/*
		 * Method with details of user needed to pre fill form data and userType
		 * and additional attributes question
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();

		Optional<User> user = userService.getUserById(userId);
		User userDetails = user.get();
		UserDTO userDto = userModalConverter.convert(userDetails);
		map.put("user", userDto);

		List<UserTypeDTO> userTypeMasterList = new ArrayList<UserTypeDTO>();
		List<UserTypeXrefDTO> userTypeList = new ArrayList<UserTypeXrefDTO>();
		List<UserType> userTypes = userInterestsService.getUserTypeMasterData(userId);
		if (userTypes != null) {
			for (UserType userType : userTypes) {
				UserTypeDTO dto = new UserTypeDTO();
				dto = userTypeModalConverter.convert(userType);
				userTypeMasterList.add(dto);
			}
		}
		map.put("userTypeMasterList", userTypeMasterList);

		List<UserTypeXref> userTypeXrefs = userInterestsService.getUserTypeList(userId);
		if (userTypeXrefs != null) {
			for (UserTypeXref userTypeXref : userTypeXrefs) {
				UserTypeXrefDTO dto = new UserTypeXrefDTO();
				dto.setId(userTypeXref.getId());

				UserType userTypeModel = userTypeXref.getUserType();
				UserTypeDTO userTypeDTO = new UserTypeDTO();

				userTypeDTO.setId(userTypeModel.getId());
				userTypeDTO.setName(userTypeModel.getName());
				userTypeDTO.setDescription(userTypeModel.getDescription());

				dto.setUserType(userTypeDTO);
				dto.setActive(userTypeXref.isActive());

				userTypeList.add(dto);
			}
		}
		map.put("userTypeList", userTypeList);

		List<AdditionalProfileAttribute> profileAttributes = userInterestsService
				.getAdditionalAttributesForUserType(userDetails);
		List<AdditionalProfileAttributeDTO> additionalProfileAttributeDTOs = new ArrayList<AdditionalProfileAttributeDTO>();
		if (profileAttributes != null) {
			for (AdditionalProfileAttribute additionalProfileAttribute : profileAttributes) {
				additionalProfileAttributeDTOs
						.add(additionProfileAttributeModalConverter.convert(additionalProfileAttribute));
			}
		}
		map.put("additionalAttribute", additionalProfileAttributeDTOs);
		UserTypeXref xref = new UserTypeXref();
		xref.setUser(userDetails);
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
		}
		map.put("userAdditionalAttribute", userAdditionalProfileAttributesDTOs);
		return map;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserPersonalInformationFacade#getProfileCompletionStatus(long)
	 */
	@Override
	public ProfileCompletionStatusDTO getProfileCompletionStatus(long userID) {
		ProfileCompletionStatusModel model = this.informationService.getProfileCompletionStatus(userID);
		return this.mapper.map(model, ProfileCompletionStatusDTO.class);
	}

}
