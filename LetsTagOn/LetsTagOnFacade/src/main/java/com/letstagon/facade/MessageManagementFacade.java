package com.letstagon.facade;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.dto.MessageDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MessageManagementFacade.
 */
public interface MessageManagementFacade {
	
	/**
	 * Send message.
	 *
	 * @param messageDTO the message DTO
	 * @return the message DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public MessageDTO sendMessage(MessageDTO messageDTO) throws InvalidPreferenceException;

	/**
	 * Gets the all conversations between parties.
	 *
	 * @param fromParty the from party
	 * @param toParty the to party
	 * @param page the page
	 * @param size the size
	 * @return the all conversations between parties
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public PaginatedResponseDTO getAllConversationsBetweenParties(PartyDTO fromParty, PartyDTO toParty, int page,
			int size) throws InvalidPreferenceException;

	/**
	 * Delete message for party bean.
	 *
	 * @param fromParty the from party
	 * @param toParty the to party
	 */
	public void deleteMessageForPartyBean(PartyDTO fromParty, PartyDTO toParty);

	/**
	 * Find party conversing with.
	 *
	 * @param fromParty the from party
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	PaginatedResponseDTO findPartyConversingWith(PartyDTO fromParty, int page,
			int size) throws InvalidPreferenceException;

	/**
	 * Find party suggestion list.
	 *
	 * @param fromParty the from party
	 * @param name the name
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	PaginatedResponseDTO findPartySuggestionList(PartyDTO fromParty, String name, int page, int size)
			throws InvalidPreferenceException;
	
	/**
	 * Gets the unread messages.
	 *
	 * @param fromParty the from party
	 * @param page the page
	 * @param size the size
	 * @return the unread messages
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	PaginatedResponseDTO getUnreadMessages(PartyDTO fromParty, int page, int size)
			throws InvalidPreferenceException;
	
	/**
	 * Mark message as read.
	 *
	 * @param message the message
	 * @return the message DTO
	 */
	MessageDTO markMessageAsRead(MessageDTO message);

	/**
	 * Mark All message as read.
	 *
	 * @param partyBean the PartyDTO
	 */
	public void markAllMessageAsRead(PartyDTO partyBean) throws InvalidPreferenceException;

}
