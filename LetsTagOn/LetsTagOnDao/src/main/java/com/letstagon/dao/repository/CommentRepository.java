package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.letstagon.dao.model.Comment;

/**
 * The Interface CommentRepository.
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

}
