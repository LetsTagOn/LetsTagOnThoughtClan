package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserExperience;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserExperienceRepository.
 */
public interface UserExperienceRepository extends PagingAndSortingRepository<UserExperience, Long> {
	
	/**
	 * Find by user bean and type order by end date asc.
	 *
	 * @param userBean the user bean
	 * @param type the type
	 * @return the list
	 */
	@Query("select u from UserExperience u where u.userBean = :userBean and u.type IN :type order by endDate DESC")
	public List<UserExperience> findByUserBeanAndTypeOrderByEndDateAsc(@Param("userBean") User userBean,@Param("type") List<String> type);
}
