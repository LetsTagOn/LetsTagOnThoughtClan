package com.letstagon.service;

import com.letstagon.dao.model.Message;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.exception.profile.InvalidPreferenceException;

// TODO: Auto-generated Javadoc
/**
 * The Interface MessageService.
 */
public interface MessageService {
	
	/**
	 * Send message.
	 *
	 * @param message the message
	 * @return the message
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public Message sendMessage(Message message) throws InvalidPreferenceException;

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
	public PaginatedSearchResponseModel getAllConversationsBetweenParties(Party fromParty, Party toParty, int page, int size) throws InvalidPreferenceException;

	/**
	 * Delete message for party bean.
	 *
	 * @param partybean the partybean
	 * @param party2 the party 2
	 */
	public void deleteMessageForPartyBean(Party partybean, Party party2);

	/**
	 * Find party conversing with.
	 *
	 * @param fromParty the from party
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	PaginatedSearchResponseModel findPartyConversingWith(Party fromParty,
			int page, int size) throws InvalidPreferenceException;

	/**
	 * Find party suggestion list.
	 *
	 * @param fromParty the from party
	 * @param name the name
	 * @param page the page
	 * @param size the size
	 * @return the paginated search response model
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	PaginatedSearchResponseModel findPartySuggestionList(Party fromParty, String name, int page, int size)
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
	PaginatedSearchResponseModel getUnreadMessages(Party fromParty, int page, int size)
			throws InvalidPreferenceException;
	
	/**
	 * Mark message as read.
	 *
	 * @param message the message
	 * @return the message
	 */
	Message markMessageAsRead(Message message);

	public void markAllNotificationAsRead(Party partyBean) throws InvalidPreferenceException;;
}
