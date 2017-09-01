package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.model.UserTypeXref;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserTypeXrefRepository.
 */
public interface UserTypeXrefRepository extends PagingAndSortingRepository<UserTypeXref, Long> {
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<UserTypeXref> findByUser(@Param("user") User user);
	
	/**
	 * Find by user and user type.
	 *
	 * @param user the user
	 * @param userType the user type
	 * @return the user type xref
	 */
	public UserTypeXref findByUserAndUserType(@Param("user")User user,@Param("userType")UserType userType);
}
