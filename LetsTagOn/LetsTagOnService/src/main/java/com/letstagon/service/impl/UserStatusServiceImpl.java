package com.letstagon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Entityy;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.UserPost;
import com.letstagon.dao.repository.EntityyRepository;
import com.letstagon.dao.repository.UserPostRepository;
import com.letstagon.service.UserStatusService;

@Component
public class UserStatusServiceImpl implements UserStatusService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserStatusServiceImpl.class);
	
	@Autowired
	private UserPostRepository userPostRepository;
	
	@Autowired EntityyRepository entityRepository;
	
	@Override
	public UserPost saveStatus(UserPost userPost) {		
		userPostRepository.save(userPost);
		return userPost;
	}
	
	@Override
	public PaginatedSearchResponseModel findAllConnectionStatus(Party party, Pageable pageable){
		Page<Entityy> result = entityRepository.findAllForUser(party, pageable);
		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());
	}

}
