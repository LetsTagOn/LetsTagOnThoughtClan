package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.letstagon.dao.model.UserPost;

public interface UserPostRepository extends PagingAndSortingRepository<UserPost, Long> {

}
