package com.letstagon.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Entityy;
import com.letstagon.dao.model.LikedEntity;

public interface LikedEntityRepository extends PagingAndSortingRepository<LikedEntity, Long> {

	Page<LikedEntity> findAllByEntity(@Param("entity") Entityy entity, Pageable pageable);
	
}
