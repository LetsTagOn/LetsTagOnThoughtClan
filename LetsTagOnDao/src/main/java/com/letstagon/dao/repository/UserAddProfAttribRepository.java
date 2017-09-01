package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.AdditionalProfileAttribute;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserAdditionalProfileAttribute;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserAddProfAttribRepository.
 */
public interface UserAddProfAttribRepository extends PagingAndSortingRepository<UserAdditionalProfileAttribute, Long> {
	
	/**
	 * Find by additional profile attribute and user bean.
	 *
	 * @param additionalProfileAttribute the additional profile attribute
	 * @param userBean the user bean
	 * @return the user additional profile attribute
	 */
	public UserAdditionalProfileAttribute findByAdditionalProfileAttributeAndUserBean(@Param("additionalProfileAttribute") AdditionalProfileAttribute additionalProfileAttribute,@Param("userBean") User userBean);
}
