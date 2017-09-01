package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.model.UserTypeAttributeXref;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserTypeAttribMappingRepository.
 */
public interface UserTypeAttribMappingRepository extends PagingAndSortingRepository<UserTypeAttributeXref, Long> {
	
	/**
	 * Find by additional profile attribute and user type bean.
	 *
	 * @param additionalProfileAttribute the additional profile attribute
	 * @param userTypeBean the user type bean
	 * @return the user type attribute xref
	 */
	public UserTypeAttributeXref findByAdditionalProfileAttributeAndUserTypeBean(@Param("additionalProfileAttribute") AdditionalProfileAttribute additionalProfileAttribute,@Param("userTypeBean") UserType userTypeBean);
}
