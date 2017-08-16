package com.letstagon.service;

import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.exception.profile.InvalidPreferenceException;


// TODO: Auto-generated Javadoc
/**
 * The Interface NotificationService.
 */
public interface NotificationService {

	/**
	 * Creates the notification.
	 *
	 * @param notification the notification
	 * @return the notification
	 */
	public Notification createNotification(Notification notification);

	/**
	 * Delete notification.
	 *
	 * @param notification the notification
	 */
	public void deleteNotification(Notification notification);

	/**
	 * Mark notification as read.
	 *
	 * @param notificationID the notification ID
	 * @return the notification
	 */
	public Notification markNotificationAsRead(long notificationID);

	
	/**
	 * Mark all notification as read for user.
	 *
	 * @param partyBean the party bean
	 * @param isRead the is read
	 */
	public void markAllNotificationAsReadForUser(Party partyBean, Boolean isRead);

	/**
	 * Gets the all notification.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @param size the size
	 * @return the all notification
	 */
	public PaginatedSearchResponseModel getAllNotification(Party partyBean, int page, int size);



	/**
	 * Gets the all unread notification.
	 *
	 * @param partyBean the party bean
	 * @param read the read
	 * @param page the page
	 * @param size the size
	 * @return the all unread notification
	 */
	public PaginatedSearchResponseModel getAllUnreadNotification(Party partyBean, Boolean read, int page, int size);

	/**
	 * Delete all notification for user.
	 *
	 * @param partyBean the party bean
	 */
	public void deleteAllNotificationForUser(Party partyBean);
	
	/**
	 * Mark all notification as read.
	 *
	 * @param party the party
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	public void markAllNotificationAsRead(Party party) throws InvalidPreferenceException;

}
