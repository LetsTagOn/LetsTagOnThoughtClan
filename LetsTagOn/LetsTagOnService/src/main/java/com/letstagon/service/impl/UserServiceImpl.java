package com.letstagon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.NonUniqueResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.Connection;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.repository.AddressRepository;
import com.letstagon.dao.repository.ConnectionRepository;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.dao.repository.UserRepository;
import com.letstagon.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Component
public class UserServiceImpl implements UserService {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;

	/** The connection repository. */
	@Autowired
	private ConnectionRepository connectionRepository;

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#getUserById(long)
	 */
	@Override
	public Optional<User> getUserById(long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#getUserByEmail(java.lang.String)
	 */
	@Override
	public Optional<User> getUserByEmail(String email) {
		return Optional.ofNullable(userRepository.findOneByEmailAddress(email));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#getUserByUserName(java.lang.String)
	 */
	@Override
	public Optional<User> getUserByUserName(String userName) {
		return Optional.ofNullable(userRepository.findOneByUserName(userName));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#getAllUsers()
	 */
	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#create(com.letstagon.dao.model.User)
	 */
	@Override
	public User create(User user) {

		if (user.getAddressBean() != null) {
			Address address = addressRepository.save(user.getAddressBean());
			user.setAddressBean(address);
		}

		if (user.getUserTypeBean() == null || user.getUserTypeBean().getId() == 0) {

			LOG.warn("User type not set, using default ID of 1 for Volunteer.");
			user.setUserTypeBean(UserType.getDefaultBean());

		}
		user.setModifiedDate(new Date());
		userRepository.save(user);
		
		
		//create party
		Party userPartyBean = partyRepository.findByUserBean(user);
		if (userPartyBean == null) {
			Party party = new Party();
			party.setUserBean(user);
			userPartyBean = partyRepository.save(party);
		}
		
		
		return user;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#getUserByEmailOrUserName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<User> getUserByEmailOrUserName(String email, String userName) throws NonUniqueResultException {
		List<User> user = userRepository.findUser(email, userName);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#updatePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User updatePassword(String userName, String password) {
		User user = userRepository.findOneByUserName(userName);
		user.setPassword(password);
		user.setModifiedDate(new Date());
		user = userRepository.save(user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#saveOrUpdateUser(com.letstagon.dao.model.Party)
	 */
	@Override
	public Party saveOrUpdateUser(Party party) {
		Party partyDetails = partyRepository.findByUserBean(party.getUserBean());
		if (partyDetails == null) {
			partyDetails = partyRepository.save(party);
		}
		return partyDetails;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#getConnectionSuggestionList(com.letstagon.dao.model.User, int, int)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedSearchResponseModel getConnectionSuggestionList(User volunteer, int page, int size) {
		Pageable pageReq = new PageRequest(page, size);
		Party partyDetails = partyRepository.findByUserBean(volunteer);
		List<Connection> connectedList = null;
		if (partyDetails != null) {
			connectedList = connectionRepository.findByParty1(partyDetails);
		}
		Page<Party> result = partyRepository.listOfUserSuggestions(volunteer, pageReq);

		List<Party> partyList = new ArrayList(result.getContent());
		if (connectedList != null) {
			for (int i = 0; i < connectedList.size(); i++) {
				for (int j = 0; j < partyList.size(); j++) {
					if (connectedList.get(i).getParty1().getId() == partyList.get(j).getId()) {
						partyList.remove(j);
					}
					if (connectedList.get(i).getParty2().getId() == partyList.get(j).getId()) {
						partyList.remove(j);
					}
				}
			}

		}

		return new PaginatedSearchResponseModel(partyList, page, result.getSize(), result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#getUserPartyDetails(com.letstagon.dao.model.User)
	 */
	@Override
	public Party getUserPartyDetails(User user) {
		return partyRepository.findByUserBean(user);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#saveUser(com.letstagon.dao.model.User)
	 */
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#updateModeifiedDate(long)
	 */
	@Override
	public void updateModeifiedDate(long userID) {
		Optional<User> user = this.getUserById(userID);
		if (!user.isPresent()) {
			return;
		}
		user.get().setModifiedDate(new Date());
		userRepository.save(user.get());

	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.UserService#findUsersByName(java.lang.String, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel findUsersByName(String name,int page,int size){
		Pageable pageReq = new PageRequest(page, size);
		Page<User> resultList = userRepository.findAllUsersByName(name, pageReq);
		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}

}