package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	/**
	 * Find one by user name.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	public User findOneByUserName(@Param("userName") String userName);

	/**
	 * Find one by email address.
	 *
	 * @param emailAddress the email address
	 * @return the user
	 */
	public User findOneByEmailAddress(@Param("emailAddress") String emailAddress);

	/**
	 * Find user.
	 *
	 * @param emailAddress the email address
	 * @param userName the user name
	 * @return the list
	 */
	@Query("select u.id from User u where u.emailAddress = :emailAddress or u.userName = :userName")
	List<User> findUser(@Param("emailAddress") String emailAddress, @Param("userName") String userName);
	
	/**
	 * Find all users by name.
	 *
	 * @param searchName the search name
	 * @param pageReq the page req
	 * @return the page
	 */
	@Query("select u from User u where LOWER(CONCAT(u.firstName,' ',u.lastName)) LIKE LOWER(CONCAT('%',:searchName, '%'))))")
	Page<User> findAllUsersByName(@Param("searchName") String searchName, Pageable pageReq);

}
