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

import com.letstagon.common.CommonConstants;
import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PrivacySettings;
import com.letstagon.enums.NotificationTypeEnum;
import com.letstagon.service.UserPrivacySettingsService;
import com.letstagon.service.event.PasswordResetNotificationEvent;
// TODO: Auto-generated Javadoc

/**
 * The listener interface for receiving passwordResetNotificationEvent events.
 * The class that is interested in processing a passwordResetNotificationEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addPasswordResetNotificationEventListener<code> method. When
 * the passwordResetNotificationEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see PasswordResetNotificationEventEvent
 */
@Component
public class PasswordResetNotificationEventListener extends BaseNotificationEventListner implements ApplicationListener<PasswordResetNotificationEvent>  {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PasswordResetNotificationEventListener.class);
	
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
	public void onApplicationEvent(PasswordResetNotificationEvent event) {
		LOG.trace("PasswordResetNotificationEventListener occured");
		boolean sendNotification = true;
		boolean sendEmailAlerts = true;
		PrivacySettings privacy = privacySettings.getUserPrivacySettings(event.getUser().getId());
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
			notification.setContent(NotificationTypeEnum.PASSWORD_RESET.getMessage());
			notification.setPartyBean(event.getUser());
			notification.setType(NotificationTypeEnum.PASSWORD_RESET.getEventType());
			notification.setSentOn(new Date());
			notification.setStatus(true);
			notification.setIsRead(false);
			this.createNotification(notification);
		}else {
			LOG.info("Reset password status Notification has not been sent check privacy settings to user id"+event.getUser().getId());
		}
		if(sendEmailAlerts){
			this.sendEmail(event);
		}else {
			LOG.info("Reset password status Email has not been sent check privacy settings to user id"+event.getUser().getId());
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
	public void sendEmail(PasswordResetNotificationEvent event){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(event.getUser().getUserBean().getEmailAddress());
				message.setFrom("no-reply@letstagon.com");
				message.setSubject(CommonConstants.EMAIL_SUBJECT);
				message.setSentDate(new Date());
				Map model = new HashMap();
				model.put("regMessage",
						CommonConstants.PASSWORD_RESET_EVENT_MESSAGE);
				model.put("name", event.getUser().getUserBean().getName());
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "velocity/updatePasswordtemplate.vm", "UTF-8",
						model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}

}
