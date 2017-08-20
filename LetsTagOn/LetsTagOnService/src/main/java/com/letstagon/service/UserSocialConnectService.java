package com.letstagon.service;

import java.util.List;
import java.util.Optional;

import com.letstagon.dao.model.SocialAppType;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserSocialConnect;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserSocialConnectService.
 */
public interface UserSocialConnectService {

	/**
	 * Find one by user and social app type.
	 *
	 * @param user the user
	 * @param socialAppType the social app type
	 * @return the optional
	 */
	Optional<UserSocialConnect> findOneByUserAndSocialAppType(User user, SocialAppType socialAppType);

	/**
	 * Find all by user.
	 *
	 * @param user the user
	 * @return the optional
	 */
	Optional<List<UserSocialConnect>> findAllByUser(User user);
	
	
}
