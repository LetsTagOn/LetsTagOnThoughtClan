package com.letstagon.dao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.repository.LtoCustomRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class LtoCustomRepositoryImpl.
 */
@Component
public  class LtoCustomRepositoryImpl implements LtoCustomRepository {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.letstagon.dao.repository.LtoCustomRepository#findUserTypeByUser(com.letstagon.dao.model.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserType> findUserTypeByUser(User user) {
		Query query = this.entityManager.createQuery("select x.userType from UserTypeXref x where x.user = :user and x.active = true");
		query.setParameter("user", user);
		List<UserType> userTypes =  query.getResultList();
		return userTypes;
	}

}
