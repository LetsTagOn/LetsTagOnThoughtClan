package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.letstagon.dao.model.UserType;

/**
 * The Interface UserTypeRepository.
 */
public interface UserTypeRepository extends PagingAndSortingRepository<UserType, Long> {

}
