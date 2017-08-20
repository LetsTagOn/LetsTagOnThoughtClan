package com.letstagon.service;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.letstagon.LetsTagOnServiceApplication;
import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.repository.PartyRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnServiceApplication.class)
@WebAppConfiguration
public class NotificationServiceTest {
	
	/** The notification service. */
	@Autowired
	private NotificationService notificationService;

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;

	/** The Constant LOG. */
	public static final Logger LOG = LoggerFactory.getLogger(NotificationServiceTest.class);

	/**
	 * Gets the notification.
	 *
	 * @return the notification
	 */
	private Notification getNotification() {
		Notification notification = new Notification();
		notification.setParams("param");
		notification.setThumbnailUrl("thumbnaiurl");

		notification.setStatus(true);
		
		notification.setIsRead(false);
		notification.setPartyBean(new Party(3));
		notification.setUrl("url");
		notification.setContent("content");
		notification.setSentOn(new Date());
		return notification;
	}


	/**
	 * Creates the notification test.
	 */
	@Ignore
	@Test
	public void createNotificationTest() {

		Notification createNotification = notificationService.createNotification(getNotification());

		LOG.info("Created Notification : " + createNotification);

	}

	/**
	 * Delete notification test.
	 */
	@Ignore
	@Test
	public void deleteNotificationTest() {
		Notification notification = new Notification();
		// notification.setId(24);

		notificationService.deleteNotification(notification);
	}

	/**
	 * Mark notification as read test.
	 */
	@Ignore
	@Test
	public void markNotificationAsReadTest() {
		// Notification result = notificationService.markNotificationAsRead(10);
		Notification result = notificationService.markNotificationAsRead(23);
		System.out.println(result.getIsRead());
	}

	/**
	 * Delete all notification of user test.
	 */
	@Ignore
	@Test
	public void deleteAllNotificationOfUserTest() {
		notificationService.deleteAllNotificationForUser(partyRepository.findOne((long) 1));
	}

	/**
	 * Mark all notification as read test.
	 */
	@Ignore
	@Test
	public void markAllNotificationAsReadTest() {
		notificationService.markAllNotificationAsReadForUser(partyRepository.findOne((long) 2), true);
	}

	/**
	 * Gets the all notification test.
	 *
	 * @return the all notification test
	 */
	@Ignore
	@Test
	public void getAllNotificationTest() {
		
		PaginatedSearchResponseModel result = notificationService.getAllNotification(partyRepository.findOne((long) 2),
				10, 10);
		System.out.println(result.getPage());
	}
	
	/**
	 * Gets the all unread notification test.
	 *
	 * @return the all unread notification test
	 */
	@Ignore
	@Test
	public void getAllUnreadNotificationTest(){
		
		PaginatedSearchResponseModel result = notificationService.getAllUnreadNotification(new Party(3), false, 10, 10);
		System.out.println("test result is" + result.getPage());
	}
	
	/**
	 * Facade create notification test.
	 */
	public void facadeCreateNotificationTest(){
	
	}
}
