package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.User;
import com.letstagon.dao.model.VolunteerLocationPref;

// TODO: Auto-generated Javadoc
/**
 * The Interface VolunteerLocationPrefRepository.
 */
public interface VolunteerLocationPrefRepository extends PagingAndSortingRepository<VolunteerLocationPref, Long> {
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the volunteer location pref
	 */
	public VolunteerLocationPref findByUser(@Param("user") User user);
}
