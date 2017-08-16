package com.letstagon.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.UserRepository;
import com.letstagon.exception.InvalidTokenException;
import com.letstagon.exception.LinkExpiredException;
import com.letstagon.service.PartyService;
import com.letstagon.service.ResetPasswordService;
import com.letstagon.service.event.OppFeedbackChangeEvent;
import com.letstagon.service.event.PasswordResetNotificationEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ResetPasswordServiceImpl.
 */
@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;
	
	/** The velocity engine. */
	@Autowired
	private VelocityEngine velocityEngine;
	
	/** The event publisher. */
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	/** The party service. */
	@Autowired
	private PartyService partyService;

	/* (non-Javadoc)
	 * @see com.letstagon.service.ResetPasswordService#resetPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void resetPassword(String userName, String token, String password) throws LinkExpiredException,InvalidTokenException {
		User user = userRepository.findOneByUserName(userName);
		Calendar cal = Calendar.getInstance();
		if (user.getResetPasswordExpiry().compareTo(cal.getTime()) >= 0 && user.isResetPasswordVerify()) {
			if (user.getResetPassordToken().equals(token)) {
				user.setPassword(password);
				System.out.println("Setting the password verify to 0 so that user cant use the same link to reset once again..");
				user.setResetPasswordVerify(false);
				userRepository.save(user);
				// send email
				sendLTONotification(user);
				Party party = partyService.findByUserBean(user);
				PasswordResetNotificationEvent passwordResetNotificationEvent = new PasswordResetNotificationEvent(user, party, new Date());
				eventPublisher.publishEvent(passwordResetNotificationEvent);
			} else {
				throw new InvalidTokenException();
				//System.out.println("Wrong token sent..!");
			}
		} else {
			throw new LinkExpiredException();
			// Handle exception here
			//System.out.println("The link has expired..!");

		}

	}

	/**
	 * Send LTO notification.
	 *
	 * @param user the user
	 */
	public void sendLTONotification(User user){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(user.getEmailAddress());
				// message.setBcc("balaji@thoughtclan.com");
				message.setFrom("info.letstagon@gmail.com");
				message.setSubject("Reset Password Update-LetsTagOn");
				message.setSentDate(new Date());
				Map model = new HashMap();
				model.put(
						"resetPwdMessage",
						"Welcome to Letstagon. A platform to enhance your volunteer skills.! Your password has been updated.");
				model.put("name", user.getUserName());
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "velocity/updatePasswordtemplate.vm",
						"UTF-8", model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}
}
