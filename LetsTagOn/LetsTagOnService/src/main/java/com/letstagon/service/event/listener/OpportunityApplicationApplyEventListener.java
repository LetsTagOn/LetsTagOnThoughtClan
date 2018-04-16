package com.letstagon.service.event.listener;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.letstagon.common.CommonConstants;
import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.enums.NotificationTypeEnum;
import com.letstagon.service.UserPrivacySettingsService;
import com.letstagon.service.event.OpportunityApplicationApplyEvent;

/**
 * The listener interface for receiving OpportunityApplicationApplyEvent events.
 * The class that is interested in processing a OpportunityApplicationApplyEvent
 * event implements this interface, and the object created with that class is
 * registered with a component using the component's
 * <code>addOpportunityApplicationApplyEventListener<code> method. When the
 * OpportunityApplicationApplyEventEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see OpportunityApplicationApplyEventEvent
 */
@Component
public class OpportunityApplicationApplyEventListener extends BaseNotificationEventListner
		implements ApplicationListener<OpportunityApplicationApplyEvent> {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OpportunityApplicationApplyEventListener.class);

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
	public void onApplicationEvent(OpportunityApplicationApplyEvent event) {
		LOG.trace("OpportunityApplicationSentEventListener OCCURED");
		PrivacySettings privacy = privacySettings
				.getUserPrivacySettings(event.getOpportunity().getCreatedByParty().getUserBean().getId());
		if (privacy == null || privacy.getEmailNotificationFrequency() == null || privacy.getEmailNotificationFrequency()) {
			Notification notification = new Notification();
			notification.setContent(NotificationTypeEnum.OPPORTUNITY_APPLICATION_APPLY.getMessage());
			notification.setPartyBean(event.getOpportunity().getCreatedByParty());
			notification.setType(NotificationTypeEnum.OPPORTUNITY_APPLICATION_APPLY.getEventType());
			notification.setSentOn(new Date());
			notification.setStatus(true);
			notification.setIsRead(false);
			notification.setThumbnailUrl(CommonConstants.THUMBNAIL_OPPORTUNITY_URL);
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			
			node.put("senderId", event.getApplicantParty().getId());
			node.put("senderUserId",event.getApplicantParty().getUserBean().getId());
			node.put("senderName",event.getApplicantParty().getUserBean().getName());
			node.put("senderProfilePicture",
					event.getApplicantParty().getUserBean().getProfilePicture());
			node.put("opportunityId", event.getOpportunity().getId());
			notification.setParams(node.toString());
			
			this.createNotification(notification);
		} else {
			LOG.info("Opp application sent status Notification has not been sent check privacy settings to user id"
					+ event.getApplicantParty().getUserBean().getId());
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
}
