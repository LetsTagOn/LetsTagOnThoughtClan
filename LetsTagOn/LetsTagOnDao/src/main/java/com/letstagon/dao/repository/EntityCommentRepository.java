package com.letstagon.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.EntityComment;
import com.letstagon.dao.model.Entityy;

public interface EntityCommentRepository extends PagingAndSortingRepository<EntityComment, Long> {
	
	Page<EntityComment> findAllByEntity(@Param("entity") Entityy entity, Pageable page);

}
