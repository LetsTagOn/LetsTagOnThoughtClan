package com.letstagon.service.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Notification;
import com.letstagon.dao.repository.NotificationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseNotificationEventListner.
 */
@Component
public class BaseNotificationEventListner {
	
	/** The notification repository. */
	@Autowired
	private NotificationRepository notificationRepository;

	/**
	 * Creates the notification.
	 *
	 * @param source the source
	 * @return the notification
	 */
	
	public  Notification  createNotification(Notification source) {
		return notificationRepository.save(source);
	}
}
