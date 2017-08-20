package com.letstagon.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Connection;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.ConnectionRepository;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.service.VolunteerConnectionsService;
import com.letstagon.service.event.ConnectionAcceptedEvent;
import com.letstagon.service.event.ConnectionRequestedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerConnectionsServiceImpl.
 */
@Component
public class VolunteerConnectionsServiceImpl implements VolunteerConnectionsService {

	/** The connection repository. */
	@Autowired
	private ConnectionRepository connectionRepository;

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;

	/** The publisher. */
	@Autowired
	private ApplicationEventPublisher publisher;

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#connect(com.letstagon.dao.model.User, com.letstagon.dao.model.User)
	 */
	@Override
	public Connection connect(User user, User connectWithUser) {

		Party userPartyBean = partyRepository.findByUserBean(user);
		if (userPartyBean == null) {
			Party party = new Party();
			party.setUserBean(user);
			userPartyBean = partyRepository.save(party);
		}
		Party connectWithParty = partyRepository.findByUserBean(connectWithUser);
		if (connectWithParty == null) {
			Party party = new Party();
			party.setUserBean(connectWithUser);
			connectWithParty = partyRepository.save(party);
		}
		Connection connection = this.connectionRepository.findOneByParty1AndParty2(userPartyBean, connectWithParty);

		if (connection == null) {
			connection = new Connection();
			connection.setParty1(userPartyBean);
			connection.setParty2(connectWithParty);
			connection.setInitiatedOn(new Date());
		}

		connection = this.connectionRepository.save(connection);
		
		if(connection.getParty1().getUserBean() == null){
			connection.setParty1(partyRepository.findOne(connection.getParty1().getId()));
		}
		if(connection.getParty2().getUserBean() == null){
			connection.setParty2(partyRepository.findOne(connection.getParty2().getId()));
		}
		ConnectionRequestedEvent connectionRequestedEvent = new ConnectionRequestedEvent(connection,
				connection.getParty1(), connection.getParty2(), new Date());
		publisher.publishEvent(connectionRequestedEvent);

		return connection;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#removeConnection(com.letstagon.dao.model.Party, com.letstagon.dao.model.Party)
	 */
	@Override
	public Connection removeConnection(Party party, Party removeConnectionWith) {

		Connection connection = this.connectionRepository.findOneByParty1AndParty2(party, removeConnectionWith);

		if (connection == null) {
			return null;
		}
		connection.setConnected(Boolean.FALSE);

		return this.connectionRepository.save(connection);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#listOpenConnections(com.letstagon.dao.model.User, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel listOpenConnections(User volunteer, int page, int size) {

		Pageable pageReq = new PageRequest(page, size);

		Party party = partyRepository.findByUserBean(volunteer);
		Page<Connection> result = connectionRepository.listOpenConnections(party, pageReq);

		return new PaginatedSearchResponseModel(result.getContent(), page, result.getSize(), result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#listConnectionSuggestions(com.letstagon.dao.model.User, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel listConnectionSuggestions(User volunteer, int page, int size) {
		Pageable pageReq = new PageRequest(page, size);

		Party party = partyRepository.findByUserBean(volunteer);
		Page<Connection> result = connectionRepository.listOpenConnections(party, pageReq);

		return new PaginatedSearchResponseModel(result.getContent(), page, result.getSize(), result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#listConnectedVolunteers(com.letstagon.dao.model.User, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel listConnectedVolunteers(User volunteer, int page, int size) {
		Pageable pageReq = new PageRequest(page, size);

		Party party = partyRepository.findByUserBean(volunteer);
		Page<Connection> result = connectionRepository.listConnectedVolunteers(party, pageReq);

		return new PaginatedSearchResponseModel(result.getContent(), page, result.getSize(), result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#listConnectedOrganizations(com.letstagon.dao.model.User, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel listConnectedOrganizations(User volunteer, int page, int size) {
		Pageable pageReq = new PageRequest(page, size);

		Party party = partyRepository.findByUserBean(volunteer);
		Page<Connection> result = connectionRepository.listConnectedOrganizations(party, pageReq);

		return new PaginatedSearchResponseModel(result.getContent(), page, result.getSize(), result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#rejectConnection(com.letstagon.dao.model.Party, com.letstagon.dao.model.Party)
	 */
	@Override
	public Connection rejectConnection(Party partyModel, Party removeConnectionWithPartyModel) {

		Connection connection = this.connectionRepository.findOneByParty1AndParty2(partyModel,
				removeConnectionWithPartyModel);

		if (connection == null) {
			return null;
		}
		this.connectionRepository.delete(connection);
		return connection;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#acceptConnection(com.letstagon.dao.model.Party, com.letstagon.dao.model.Party)
	 */
	@Override
	public Connection acceptConnection(Party partyModel, Party connectWithPartyModel) {

		Connection connection = this.connectionRepository.findOneByParty1AndParty2(partyModel, connectWithPartyModel);

		if (connection == null) {
			return null;
		}
		connection.setConnected(Boolean.TRUE);

		connection = this.connectionRepository.save(connection);
		if(connection.getParty1().getUserBean() == null){
			connection.setParty1(partyRepository.findOne(connection.getParty1().getId()));
		}
		if(connection.getParty2().getUserBean() == null){
			connection.setParty2(partyRepository.findOne(connection.getParty2().getId()));
		}
		ConnectionAcceptedEvent connectionAcceptedEvent = new ConnectionAcceptedEvent(connection,
				connection.getParty1(), connection.getParty2(), new Date());
		publisher.publishEvent(connectionAcceptedEvent);

		return connection;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#getConnectionListOfUser(long)
	 */
	@Override
	public List<Connection> getConnectionListOfUser(long userId) {
		User volunteer = new User();
		volunteer.setId(userId);
		Party party = partyRepository.findByUserBean(volunteer);
		if (party != null) {
			return this.connectionRepository.findByParty1(party);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.VolunteerConnectionsService#checkIfConnected(com.letstagon.dao.model.User, com.letstagon.dao.model.User)
	 */
	@Override
	public Connection checkIfConnected(User user, User connectWithUser) {
		Party userPartyBean = partyRepository.findByUserBean(user);

		Party connectWithParty = partyRepository.findByUserBean(connectWithUser);

		if (userPartyBean == null || connectWithParty == null) {
			return null;
		}

		Connection connection = this.connectionRepository.findOneByParty1AndParty2(userPartyBean, connectWithParty);

		return connection;
	}

}
