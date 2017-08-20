package com.letstagon.facade;

import java.util.List;

import com.letstagon.facade.dto.ConnectionDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * Facade layer class for managing Volunteer connections.
 *
 * @author Thoughtclan
 */
public interface VolunteerConnectionsFacade {

	/**
	 * Connect.
	 *
	 * @param party the party
	 * @param connectWith the connect with
	 * @return the connection DTO
	 */
	ConnectionDTO connect(UserDTO party, UserDTO connectWith);

	/**
	 * Removes the connection.
	 *
	 * @param party the party
	 * @param removeConnectionWithParty the remove connection with party
	 * @return the connection DTO
	 */
	ConnectionDTO removeConnection(PartyDTO party, PartyDTO removeConnectionWithParty);

	/**
	 * List open connections.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO listOpenConnections(UserDTO volunteer, int page, int size);

	/**
	 * List connection suggestions.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO listConnectionSuggestions(UserDTO volunteer, int page, int size);

	/**
	 * List connected volunteers.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO listConnectedVolunteers(UserDTO volunteer, int page, int size);

	/**
	 * List connected organizations.
	 *
	 * @param volunteer the volunteer
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO listConnectedOrganizations(UserDTO volunteer, int page, int size);

	/**
	 * Accept connection.
	 *
	 * @param party the party
	 * @param acceptConnectionWithParty the accept connection with party
	 * @return the connection DTO
	 */
	ConnectionDTO acceptConnection(PartyDTO party, PartyDTO acceptConnectionWithParty);

	/**
	 * Reject connection.
	 *
	 * @param party the party
	 * @param removeConnectionWithParty the remove connection with party
	 * @return the connection DTO
	 */
	ConnectionDTO rejectConnection(PartyDTO party, PartyDTO removeConnectionWithParty);
	
	/**
	 * Gets the connection list of user.
	 *
	 * @param userId the user id
	 * @return the connection list of user
	 */
	List<ConnectionDTO> getConnectionListOfUser(long userId);

	/**
	 * Accept connection.
	 *
	 * @param party the party
	 * @param acceptConnectionWithParty the accept connection with party
	 * @return the connection DTO
	 */
	ConnectionDTO acceptConnection(UserDTO party,
			UserDTO acceptConnectionWithParty);

	/**
	 * Reject connection.
	 *
	 * @param party the party
	 * @param removeConnectionWithParty the remove connection with party
	 * @return the connection DTO
	 */
	ConnectionDTO rejectConnection(UserDTO party,
			UserDTO removeConnectionWithParty);

	/**
	 * Check if connected.
	 *
	 * @param party the party
	 * @param connectWith the connect with
	 * @return the connection DTO
	 */
	ConnectionDTO checkIfConnected(UserDTO party, UserDTO connectWith);

}
