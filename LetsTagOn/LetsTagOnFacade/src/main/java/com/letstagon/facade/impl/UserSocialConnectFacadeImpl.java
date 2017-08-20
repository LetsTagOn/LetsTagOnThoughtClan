package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.SocialAppType;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserSocialConnect;
import com.letstagon.facade.UserSocialConnectFacade;
import com.letstagon.facade.dto.SocialAppTypeDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserSocialConnectDTO;
import com.letstagon.service.UserSocialConnectService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserSocialConnectFacadeImpl.
 */
@Component
public class UserSocialConnectFacadeImpl implements UserSocialConnectFacade {

	/** The user social connect service. */
	@Autowired
	private UserSocialConnectService userSocialConnectService;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserSocialConnectFacade#findOneByUserAndSocialAppType(com.letstagon.facade.dto.UserDTO, com.letstagon.facade.dto.SocialAppTypeDTO)
	 */
	@Override
	public UserSocialConnectDTO findOneByUserAndSocialAppType(UserDTO user, SocialAppTypeDTO socialAppType) {

		User userModel = mapper.map(user, User.class);
		SocialAppType appModel = mapper.map(socialAppType, SocialAppType.class);
		Optional<UserSocialConnect> connectModel = userSocialConnectService.findOneByUserAndSocialAppType(userModel,
				appModel);

		if (!connectModel.isPresent()) {
			return null;
		}

		return wrapUserSocialConnectDTO(connectModel.get());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.UserSocialConnectFacade#findAllByUser(com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public List<UserSocialConnectDTO> findAllByUser(UserDTO user) {

		User userModel = mapper.map(user, User.class);
		Optional<List<UserSocialConnect>> connects = userSocialConnectService.findAllByUser(userModel);
		if (!connects.isPresent()) {
			return null;
		}
		List<UserSocialConnectDTO> listDto = new ArrayList<UserSocialConnectDTO>();
		for (UserSocialConnect connect : connects.get()) {
			listDto.add(this.wrapUserSocialConnectDTO(connect));
		}

		return listDto;
	}

	/**
	 * Wrap user social connect DTO.
	 *
	 * @param model the model
	 * @return the user social connect DTO
	 */
	private UserSocialConnectDTO wrapUserSocialConnectDTO(UserSocialConnect model) {

		if (model == null)
			return null;

		UserSocialConnectDTO dto = new UserSocialConnectDTO();

		dto.setId(model.getId());
		UserDTO user = new UserDTO();
		user.setId(dto.getUser().getId());
		user.setEmailAddress(dto.getUser().getEmailAddress());
		dto.setUser(user);
		dto.setSocialAppID(model.getSocialAppID());
		SocialAppTypeDTO socialAppType = new SocialAppTypeDTO();
		socialAppType.setId(model.getSocialAppType().getId());
		socialAppType.setName(model.getSocialAppType().getName());
		dto.setSocialAppType(socialAppType);

		return dto;

	}

}
