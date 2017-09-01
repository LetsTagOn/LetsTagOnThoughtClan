package com.letstagon.facade.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;
import com.letstagon.facade.dto.AddressDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserAdditionalProfileAttributeDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserTypeDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class UserModelConverter.
 */
@Component
public class UserModelConverter implements Converter<User, UserDTO> {

	/** The address modal converter. */
	@Autowired
	private Converter<Address, AddressDTO> addressModalConverter;

	/** The user additional profile attribute converter. */
	@Autowired
	private Converter<UserAdditionalProfileAttribute, UserAdditionalProfileAttributeDTO> userAdditionalProfileAttributeConverter;

	/** The party modal converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyModalConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public UserDTO convert(User user) {
		if (user == null)
			return null;

		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserName(user.getUserName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmailAddress(user.getEmailAddress());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setGender(user.getGender());
		userDTO.setProfilePicture(user.getProfilePicture());
		userDTO.setName(user.getName());
		if (user.getUserTypeBean() != null) {
			UserTypeDTO userTypeDTO = new UserTypeDTO();
			userTypeDTO.setId(user.getUserTypeBean().getId());
			userDTO.setUserTypeBean(userTypeDTO);
		}
		userDTO.setPhoneNumber(user.getPhoneNumber());
		userDTO.setSummary(user.getSummary());
		if(user.getAddressBean() != null){
			AddressDTO address = addressModalConverter.convert(user.getAddressBean());
			userDTO.setAddressBean(address);
		}
				
		List<UserAdditionalProfileAttribute> list = user.getUserAdditionalProfileAttributes();
		List<UserAdditionalProfileAttributeDTO> attributeDTOs = new ArrayList<UserAdditionalProfileAttributeDTO>();
		if(list != null){
			for (UserAdditionalProfileAttribute userAdditionalProfileAttribute : list) {
				UserAdditionalProfileAttributeDTO additionalProfileAttributeDTO = userAdditionalProfileAttributeConverter.convert(userAdditionalProfileAttribute);
				attributeDTOs.add(additionalProfileAttributeDTO);
			}
			userDTO.setUserAdditionalProfileAttributes(attributeDTOs);
		}

		return userDTO;
	}

}
