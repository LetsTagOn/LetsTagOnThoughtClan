package com.letstagon.service.event.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.letstagon.common.CommonConstants;
import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.enums.NotificationTypeEnum;
import com.letstagon.service.UserPrivacySettingsService;
import com.letstagon.service.event.ConnectionAcceptedEvent;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving connectionAcceptedEvent events. The
 * class that is interested in processing a connectionAcceptedEvent event
 * implements this interface, and the object created with that class is
 * registered with a component using the component's
 * <code>addConnectionAcceptedEventListener<code> method. When the
 * connectionAcceptedEvent event occurs, that object's appropriate method is
 * invoked.
 *
 * @see ConnectionAcceptedEventEvent
 */
@Component
public class ConnectionAcceptedEventListener extends BaseNotificationEventListner
		implements ApplicationListener<ConnectionAcceptedEvent> {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionAcceptedEventListener.class);

	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	/** The velocity engine. */
	@Autowired
	private VelocityEngine velocityEngine;

	/** The privacy settings. */
	@Autowired
	private UserPrivacySettingsService privacySettings;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.
	 * springframework.context.ApplicationEvent)
	 */
	@Override
	@Async
	public void onApplicationEvent(ConnectionAcceptedEvent event) {
		LOG.trace("ConnectionAcceptedEventListener occured");
		boolean sendNotification = true;
		boolean sendEmailAlerts = true;
		PrivacySettings privacy = privacySettings.getUserPrivacySettings(event.getToParty().getUserBean().getId());
		if (privacy != null) {
			if (privacy.getEmailAlertsOn() != null && !privacy.getEmailAlertsOn()) {
				sendEmailAlerts = false;
			}
			if (privacy.getEmailNotificationFrequency() != null && !privacy.getEmailNotificationFrequency()) {
				sendNotification = false;
			}
		}
		if (sendNotification) {
			Notification notification = new Notification();
			notification.setContent(NotificationTypeEnum.CONNECTION_ACCEPT.getMessage());
			notification.setPartyBean(event.getFromParty());
			notification.setType(NotificationTypeEnum.CONNECTION_ACCEPT.getEventType());
			notification.setSentOn(new Date());
			notification.setStatus(true);
			notification.setIsRead(false);
			notification.setThumbnailUrl(CommonConstants.THUMBNAIL_PROFILE_URL);
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("senderId", event.getFromParty().getId());
			node.put("senderUserId", event.getFromParty().getUserBean().getId());
			node.put("senderName", event.getFromParty().getUserBean().getName());
			node.put("senderProfilePicture", event.getFromParty().getUserBean().getProfilePicture());
			notification.setParams(node.toString());

			this.createNotification(notification);
		} else {
			LOG.info("Connection accepted Notification has not been sent check privacy settings to user id"
					+ event.getFromParty().getUserBean().getId());
		}
		if (sendEmailAlerts) {
			this.sendEmail(event);
		} else {
			LOG.info("Connection accepted Email has not been sent check privacy settings to user id"
					+ event.getFromParty().getUserBean().getId());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.letstagon.service.event.listener.BaseNotificationEventListner#
	 * createNotification(com.letstagon.dao.model.Notification)
	 */
	public Notification createNotification(Notification source) {
		return super.createNotification(source);
	}

	/**
	 * Send email.
	 *
	 * @param event
	 *            the event
	 */

	public void sendEmail(ConnectionAcceptedEvent event) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(event.getFromParty().getUserBean().getEmailAddress());
				message.setFrom("letstagon@gmail.com");
				message.setSubject(CommonConstants.EMAIL_SUBJECT);
				message.setSentDate(new Date());
				Map model = new HashMap();
				model.put("regMessage",
						CommonConstants.CONNECTION_ACCEPT_EVENT_MESSAGE + event.getToParty().getUserBean().getName());
				model.put("name", event.getFromParty().getUserBean().getName());
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"velocity/connectionAcceptTemplate.vm", "UTF-8", model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}
}
