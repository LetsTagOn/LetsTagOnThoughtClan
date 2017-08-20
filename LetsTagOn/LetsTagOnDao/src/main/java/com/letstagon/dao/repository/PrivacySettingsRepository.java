package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface PrivacySettingsRepository.
 */
public interface PrivacySettingsRepository extends PagingAndSortingRepository<PrivacySettings, Long>{
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the privacy settings
	 */
	public PrivacySettings findByUser(@Param("user") User user);
}
