package com.letstagon.web.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.facade.VolunteerConnectionsFacade;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.ConnectionDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerConnectionsRestController.
 */
@RestController
public class VolunteerConnectionsRestController {

	/** The connections facade. */
	@Autowired
	private VolunteerConnectionsFacade connectionsFacade;

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VolunteerConnectionsRestController.class);

	/**
	 * List open connection requests.
	 *
	 * @param userID the user ID
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/connection/{userID}/openRequests", method = RequestMethod.GET)
	public PaginatedResponseDTO listOpenConnectionRequests(@PathVariable(value = "userID") long userID,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		UserDTO volunteer = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		return connectionsFacade.listOpenConnections(volunteer, page, size);
	}

	/**
	 * List connections.
	 *
	 * @param userID the user ID
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/connection/{userID}/list", method = RequestMethod.GET)
	public PaginatedResponseDTO listConnections(@PathVariable(value = "userID") long userID,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		UserDTO volunteer = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		return connectionsFacade.listConnectedVolunteers(volunteer, page, size);

	}

	/**
	 * List organization connections.
	 *
	 * @param userID the user ID
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/connection/{userID}/organization", method = RequestMethod.GET)
	public PaginatedResponseDTO listOrganizationConnections(@PathVariable(value = "userID") long userID,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		UserDTO volunteer = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		return connectionsFacade.listConnectedOrganizations(volunteer, page, size);

	}

	/**
	 * List connection suggestions.
	 *
	 * @param userID the user ID
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/connection/{userID}/suggest", method = RequestMethod.GET)
	public PaginatedResponseDTO listConnectionSuggestions(@PathVariable(value = "userID") long userID,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		UserDTO volunteer = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		return connectionsFacade.listConnectionSuggestions(volunteer, page, size);

	}

	/**
	 * Connect.
	 *
	 * @param userID the user ID
	 * @param connectWithUserId the connect with user id
	 * @return the connection DTO
	 */
	@RequestMapping(value = "/party/{userID}/connection/{connectWithUserId}", method = RequestMethod.POST)
	public ConnectionDTO connect(@PathVariable long userID, @PathVariable long connectWithUserId) {

		UserDTO party = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		UserDTO connectWith = new UserDTO(connectWithUserId);
		return connectionsFacade.connect(party, connectWith);

	}

	/**
	 * Removes the connection.
	 *
	 * @param partyID the party ID
	 * @param removeConnectWithPartyID the remove connect with party ID
	 * @return the connection DTO
	 */
	@RequestMapping(value = "/party/{partyID}/connection/{removeConnectWithPartyID}", method = RequestMethod.DELETE)
	public ConnectionDTO removeConnection(@PathVariable(value = "partyID") long partyID,
			@PathVariable(value = "removeConnectWithPartyID") long removeConnectWithPartyID) {

		PartyDTO party = new PartyDTO(partyID);
		PartyDTO removeConnectionWithParty = new PartyDTO(removeConnectWithPartyID);
		return connectionsFacade.removeConnection(party, removeConnectionWithParty);

	}

	/**
	 * Accept connection.
	 *
	 * @param partyID the party ID
	 * @param connectWithPartyID the connect with party ID
	 * @return the connection DTO
	 */
	@RequestMapping(value = "/party/{partyID}/connection/accept/{connectWithPartyID}", method = RequestMethod.POST)
	public ConnectionDTO acceptConnection(@PathVariable(value = "partyID") long partyID,
			@PathVariable(value = "connectWithPartyID") long connectWithPartyID) {

		PartyDTO party = new PartyDTO(partyID);
		PartyDTO acceptConnectionWithParty = new PartyDTO(connectWithPartyID);
		return connectionsFacade.acceptConnection(party, acceptConnectionWithParty);

	}

	/**
	 * Reject connection.
	 *
	 * @param partyID the party ID
	 * @param connectWithPartyID the connect with party ID
	 * @return the connection DTO
	 */
	@RequestMapping(value = "/party/{partyID}/connection/reject/{connectWithPartyID}", method = RequestMethod.DELETE)
	public ConnectionDTO rejectConnection(@PathVariable(value = "partyID") long partyID,
			@PathVariable(value = "connectWithPartyID") long connectWithPartyID) {

		PartyDTO party = new PartyDTO(partyID);
		PartyDTO removeConnectionWithParty = new PartyDTO(connectWithPartyID);
		return connectionsFacade.rejectConnection(party, removeConnectionWithParty);

	}

	/**
	 * Accept invite.
	 *
	 * @param userId the user id
	 * @param connectWithUserId the connect with user id
	 * @return the connection DTO
	 */
	@RequestMapping(value = "/user/{userId}/connection/accept/{connectWithUserId}", method = RequestMethod.POST)
	public ConnectionDTO acceptInvite(@PathVariable long userId, @PathVariable long connectWithUserId) {

		UserDTO party = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		UserDTO acceptConnectionWithParty = new UserDTO(connectWithUserId);
		return connectionsFacade.acceptConnection(party, acceptConnectionWithParty);

	}

	/**
	 * Reject invite.
	 *
	 * @param userId the user id
	 * @param connectWithUserId the connect with user id
	 * @return the connection DTO
	 */
	@RequestMapping(value = "/user/{userId}/connection/reject/{connectWithUserId}", method = RequestMethod.DELETE)
	public ConnectionDTO rejectInvite(@PathVariable long userId, @PathVariable long connectWithUserId) {

		UserDTO party = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		UserDTO removeConnectionWithParty = new UserDTO(connectWithUserId);
		return connectionsFacade.rejectConnection(party, removeConnectionWithParty);

	}

	/**
	 * User connection list.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@RequestMapping(value = "/user/{userId}/connections", method = RequestMethod.GET)
	public List<ConnectionDTO> userConnectionList(@PathVariable long userId) {
		return connectionsFacade.getConnectionListOfUser(userId);

	}

	/**
	 * Check connection.
	 *
	 * @param userID the user ID
	 * @param connectWithUserId the connect with user id
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/party/{userID}/checkConnection/{connectWithUserId}", method = RequestMethod.GET)
	public AjaxResponseDTO checkConnection(@PathVariable long userID, @PathVariable long connectWithUserId) {

		UserDTO party = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		UserDTO connectWith = new UserDTO(connectWithUserId);
		return new AjaxResponseDTO(connectionsFacade.checkIfConnected(party, connectWith));

	}

}
