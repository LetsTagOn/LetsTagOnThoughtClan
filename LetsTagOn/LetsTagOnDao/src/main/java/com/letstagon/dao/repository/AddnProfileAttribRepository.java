package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.UserType;

// TODO: Auto-generated Javadoc
/**
 * The Interface AddnProfileAttribRepository.
 */
public interface AddnProfileAttribRepository extends PagingAndSortingRepository<AdditionalProfileAttribute, Long> {

	/**
	 * Find attibutes for types.
	 *
	 * @param userTypeList the user type list
	 * @return the list
	 */
	@Query("SELECT DISTINCT(addAttr) FROM UserTypeAttributeXref usrTypeAttrMap JOIN usrTypeAttrMap.additionalProfileAttribute addAttr WHERE usrTypeAttrMap.userTypeBean in :userTypeList")
	 List<AdditionalProfileAttribute> findAttibutesForTypes(@Param("userTypeList") List<UserType> userTypeList);

}
