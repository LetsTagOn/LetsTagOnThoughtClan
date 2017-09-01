package com.letstagon.facade;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.dto.NotificationDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface NotificationManagerFacade.
 */
public interface NotificationManagerFacade {
	
	/**
	 * Creates the notification.
	 *
	 * @param notification the notification
	 * @return the notification DTO
	 */
	public NotificationDTO createNotification(NotificationDTO notification);

	/**
	 * Gets the all notification.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @param size the size
	 * @return the all notification
	 */
	public PaginatedResponseDTO getAllNotification(PartyDTO partyBean, int page, int size);

	/**
	 * Gets the all unread notification.
	 *
	 * @param partyBean the party bean
	 * @param read the read
	 * @param page the page
	 * @param size the size
	 * @return the all unread notification
	 */
	public PaginatedResponseDTO getAllUnreadNotification(PartyDTO partyBean, Boolean read, int page, int size);

	/**
	 * Mark notification as read.
	 *
	 * @param notificationID the notification ID
	 * @return the notification DTO
	 */
	public NotificationDTO markNotificationAsRead(long notificationID);

	/**
	 * Delete all notification for user.
	 *
	 * @param partyBean the party bean
	 */
	public void deleteAllNotificationForUser(PartyDTO partyBean);

	/**
	 * Delete notification.
	 *
	 * @param notification the notification
	 */
	void deleteNotification(NotificationDTO notification);
	
	
	/**
	 * Mark all as read.
	 *
	 * @param party the party
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	void markAllAsRead(PartyDTO party) throws InvalidPreferenceException;
}
