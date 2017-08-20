package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.SocialAppType;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserSocialConnect;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserSocialConnectRepository.
 */
public interface UserSocialConnectRepository extends PagingAndSortingRepository<UserSocialConnect, Long> {

	/**
	 * Find one by user and social app type.
	 *
	 * @param user the user
	 * @param socialAppType the social app type
	 * @return the user social connect
	 */
	UserSocialConnect findOneByUserAndSocialAppType(@Param("user") User user,
			@Param("socialAppType") SocialAppType socialAppType);

	/**
	 * Find all by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<UserSocialConnect> findAllByUser(@Param("user") User user);

}
