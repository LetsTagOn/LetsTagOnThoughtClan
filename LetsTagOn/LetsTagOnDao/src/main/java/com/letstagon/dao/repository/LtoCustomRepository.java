package com.letstagon.dao.repository;

import java.util.List;

import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserType;

// TODO: Auto-generated Javadoc
/**
 * The Interface LtoCustomRepository.
 */
public interface LtoCustomRepository {

	/**
	 * Find user type by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<UserType> findUserTypeByUser(User user);

}
