package com.letstagon.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Organization;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface PartyRepository.
 */
public interface PartyRepository extends PagingAndSortingRepository<Party, Long> {

	/**
	 * Find by user bean.
	 *
	 * @param userBean the user bean
	 * @return the party
	 */
	public Party findByUserBean(@Param("userBean") User userBean);

	/**
	 * Find by organization bean.
	 *
	 * @param organizationBean the organization bean
	 * @return the party
	 */
	public Party findByOrganizationBean(@Param("organizationBean") Organization organizationBean);

	/**
	 * List of user suggestions.
	 *
	 * @param userBean the user bean
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("select p from Party p where p.userBean != :userBean")
	Page<Party> listOfUserSuggestions(@Param("userBean") User userBean, Pageable pageable);
	
	/**
	 * Find all users by name.
	 *
	 * @param name the name
	 * @param pageReq the page req
	 * @return the page
	 */
	@Query("select p from Party p where LOWER(CONCAT(p.userBean.firstName,' ',p.userBean.lastName)) LIKE LOWER(CONCAT('%',:name, '%'))))")
	Page<Party> findAllUsersByName(@Param("name") String name, Pageable pageReq);
}
