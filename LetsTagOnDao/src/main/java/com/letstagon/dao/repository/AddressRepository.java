package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.letstagon.dao.model.Address;

/**
 * The Interface AddressRepository.
 */
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

}
