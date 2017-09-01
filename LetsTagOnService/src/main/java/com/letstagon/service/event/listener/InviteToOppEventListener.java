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

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.letstagon.common.CommonConstants;
import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.enums.NotificationTypeEnum;
import com.letstagon.service.UserPrivacySettingsService;
import com.letstagon.service.event.InviteToOppEvent;
// TODO: Auto-generated Javadoc

/**
 * The listener interface for receiving inviteToOppEvent events.
 * The class that is interested in processing a inviteToOppEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addInviteToOppEventListener<code> method. When
 * the inviteToOppEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see InviteToOppEventEvent
 */
@Component
public class InviteToOppEventListener extends BaseNotificationEventListner implements ApplicationListener<InviteToOppEvent> {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(InviteToOppEventListener.class);
	
	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;
	
	/** The velocity engine. */
	@Autowired
	private VelocityEngine velocityEngine;
	
	/** The privacy settings. */
	@Autowired
	private UserPrivacySettingsService privacySettings;
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	@Async
	public void onApplicationEvent(InviteToOppEvent event) {
		LOG.trace("InviteToOppEventListener occured");
		boolean sendNotification = true;
		boolean sendEmailAlerts = true;
		PrivacySettings privacy = privacySettings.getUserPrivacySettings(event.getInvitingParty().getUserBean().getId());
		if(privacy != null){
			if(privacy.getEmailAlertsOn() != null && !privacy.getEmailAlertsOn()){
				sendEmailAlerts = false;
			}
			if(privacy.getEmailNotificationFrequency() != null && !privacy.getEmailNotificationFrequency()){
				sendNotification = false;
			}
		}
		if(sendNotification){
			Notification notification = new Notification();
			notification.setContent(NotificationTypeEnum.INVITE_TO_OPPORTUNITY.getMessage());
			notification.setPartyBean(event.getInvitedParty());
			notification.setType(NotificationTypeEnum.INVITE_TO_OPPORTUNITY.getEventType());
			notification.setSentOn(new Date());
			notification.setStatus(true);
			notification.setIsRead(false);
			notification.setThumbnailUrl(CommonConstants.THUMBNAIL_OPPORTUNITY_URL);
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("senderId", event.getInvitingParty().getId());
				jsonObject.put("senderUserId",event.getInvitingParty().getUserBean().getId());
				jsonObject.put("senderName", event.getInvitingParty().getUserBean().getName());
				jsonObject.put("senderProfilePicture", event.getInvitingParty().getUserBean().getProfilePicture());
				jsonObject.put("opportunityId", event.getOpportunity().getId());
				notification.setParams(jsonObject.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.createNotification(notification);
		}else {
			LOG.info("invite to opp Notification has not been sent check privacy settings to user id"+event.getInvitingParty().getUserBean().getId());
		}
		if(sendEmailAlerts){
			this.sendEmail(event);
		}else {
			LOG.info("invite to opp Email has not been sent check privacy settings to user id"+event.getInvitingParty().getUserBean().getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.event.listener.BaseNotificationEventListner#createNotification(com.letstagon.dao.model.Notification)
	 */
	public Notification createNotification(Notification source){
		return super.createNotification(source);
	}
	
	/**
	 * Send email.
	 *
	 * @param event the event
	 */
	public void sendEmail(InviteToOppEvent event){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(event.getInvitedParty().getUserBean().getEmailAddress());
				message.setFrom("info.letstagon@gmail.com");
				message.setSubject(CommonConstants.EMAIL_SUBJECT);
				message.setSentDate(new Date());
				Map model = new HashMap();
				model.put("regMessage",
						event.getInvitingParty().getUserBean().getName()+CommonConstants.INVITE_OPP_EVENT_MESSAGE);
				model.put("name", event.getInvitedParty().getUserBean().getName());
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "velocity/inviteTemplate.vm", "UTF-8",
						model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}
}
