package com.letstagon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.SocialAppType;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserSocialConnect;
import com.letstagon.dao.repository.UserSocialConnectRepository;
import com.letstagon.service.UserSocialConnectService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserSocialConnectServiceImpl.
 */
@Component
public class UserSocialConnectServiceImpl implements UserSocialConnectService {

	/** The repository. */
	@Autowired
	private UserSocialConnectRepository repository;

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserSocialConnectService#findOneByUserAndSocialAppType(com.letstagon.dao.model.User, com.letstagon.dao.model.SocialAppType)
	 */
	@Override
	public Optional<UserSocialConnect> findOneByUserAndSocialAppType(User user, SocialAppType socialAppType) {
		return Optional.ofNullable(repository.findOneByUserAndSocialAppType(user, socialAppType));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserSocialConnectService#findAllByUser(com.letstagon.dao.model.User)
	 */
	@Override
	public Optional<List<UserSocialConnect>> findAllByUser(User user) {
		return Optional.ofNullable(repository.findAllByUser(user));
	}

}
