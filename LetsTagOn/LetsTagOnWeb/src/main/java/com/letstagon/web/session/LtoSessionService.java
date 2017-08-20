package com.letstagon.web.session;

import org.springframework.security.core.Authentication;

import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.PartyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface LtoSessionService.
 */
public interface LtoSessionService {
	
	/**
	 * Gets the logged in user.
	 *
	 * @return the logged in user
	 */
	User getLoggedInUser();

	/**
	 * Find logged in party.
	 *
	 * @param applyingPartyID the applying party ID
	 * @return the party DTO
	 */
	PartyDTO findLoggedInParty(long applyingPartyID);
	
	/**
	 * Find party id of logged in user.
	 *
	 * @param userID the user ID
	 * @return the party DTO
	 */
	PartyDTO findPartyIdOfLoggedInUser(long userID);
	
	/**
	 * Reload.
	 *
	 * @return the authentication
	 */
	Authentication reload();

}
