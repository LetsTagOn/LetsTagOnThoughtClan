package com.letstagon.facade;

import java.util.List;

import com.letstagon.facade.dto.SocialAppTypeDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserSocialConnectDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserSocialConnectFacade.
 */
public interface UserSocialConnectFacade {

	/**
	 * Find one by user and social app type.
	 *
	 * @param user the user
	 * @param socialAppType the social app type
	 * @return the user social connect DTO
	 */
	UserSocialConnectDTO findOneByUserAndSocialAppType(UserDTO user, SocialAppTypeDTO socialAppType);

	/**
	 * Find all by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<UserSocialConnectDTO> findAllByUser(UserDTO user);

}
