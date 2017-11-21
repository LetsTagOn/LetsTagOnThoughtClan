package com.letstagon.service;

import org.springframework.data.domain.Pageable;

import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.UserPost;

public interface UserStatusService {

	public UserPost saveStatus(UserPost userPost);
	
	public PaginatedSearchResponseModel findAllConnectionStatus(Party party, Pageable pageable);
}
