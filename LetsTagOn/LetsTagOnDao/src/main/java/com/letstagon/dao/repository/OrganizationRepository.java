package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.letstagon.dao.model.Organization;

/**
 * The Interface OrganizationRepository.
 */
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {

}
