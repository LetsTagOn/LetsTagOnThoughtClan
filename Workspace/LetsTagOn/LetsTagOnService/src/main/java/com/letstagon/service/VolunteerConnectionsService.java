package com.letstagon.service;

import java.util.List;

import com.letstagon.dao.model.Connection;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * Service layer class for Volunteer connections.
 *
 * @author Thoughtclan
 */
public interface VolunteerConnectionsService {

	/**
	 * Connect.
	 *
	 * @param user the user
	 * @param connectWithUserModel the connect with user model
	 * @return the connection
	 */
	Connection connect(User user, User connectWithUserModel);

	/**
	 * Removes the connection.
	 *
	 * @param party the party
	 * @param connectWithParty the connect with party
	 * @return the connection
	 */
	Connection removeConnection(Party party, Party connectWithParty);

	/**
	 * Reject connection.
	 *
	 * @param partyModel the party model
	 * @param removeConnectionWithPartyModel the remove connection with party model
	 * @return the connection
	 */
	Connection rejectConnection(Party partyModel, Party removeConnectionWithPartyModel);

	/**
	 * Accept connection.
	 *
	 * @param partyModel the party model
	 * @param connectWithPartyModel the connect with party model
	 * @return the connection
	 */
	Connection acceptConnection(Party partyModel, Party connectWithPartyModel);

	/**
	 * List open connections.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel listOpenConnections(User volunteer, int page, int size);

	/**
	 * List connection suggestions.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel listConnectionSuggestions(User volunteer, int page, int size);

	/**
	 * List connected volunteers.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel listConnectedVolunteers(User volunteer, int page, int size);

	/**
	 * List connected organizations.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel listConnectedOrganizations(User volunteer, int page, int size);

	/**
	 * Gets the connection list of user.
	 *
	 * @param userId the user id
	 * @return the connection list of user
	 */
	List<Connection> getConnectionListOfUser(long userId);

	/**
	 * Check if connected.
	 *
	 * @param party the party
	 * @param connectWith the connect with
	 * @return the connection
	 */
	Connection checkIfConnected(User party, User connectWith);

}
