package com.letstagon.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.UserRepository;
import com.letstagon.service.ForgotPasswordService;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgotPasswordServiceImpl.
 */
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;
	
	/** The velocity engine. */
	@Autowired
	private VelocityEngine velocityEngine;	
	
	/** The app url. */
	@Value("${letsTagon.appUrl}")
	private String appUrl;
	
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.ForgotPasswordService#forgotPassword(java.lang.String)
	 */
	//need to add exception related to finding the user. Check with ranjitha.
	@Override
	public void forgotPassword(String userName) throws UsernameNotFoundException{
		User user = userRepository.findOneByEmailAddress(userName);
		if(user==null){
			throw new UsernameNotFoundException("Username not found");
		}
		//check if user needs to update email.
		//If Yes, then save the email else leave it as such.
		else{
		user.setResetPassordToken(this.getResetPasswordToken());
		user.setResetPasswordExpiry(expiryDate());	
		user.setResetPasswordVerify(true);
		userRepository.save(user);
		sendLTONotification(user,user.getEmailAddress());
		}
	}


	/**
	 * Return a random unique ID.
	 *
	 * @return the reset password token
	 */
	private String getResetPasswordToken() {
		return UUID.randomUUID().toString();
	}


	/**
	 * Expiry date.
	 *
	 * @return the date
	 */
	public static Date expiryDate() {
		Calendar cal = Calendar.getInstance();
	    int daysToIncrement = 5;
	    cal.add(Calendar.DATE, daysToIncrement);
	    return cal.getTime();
	}
	
	/**
	 * Send LTO notification.
	 *
	 * @param user the user
	 * @param email the email
	 */
	public void sendLTONotification(User user,String email) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(email);
				//message.setBcc("balaji@thoughtclan.com");
				message.setFrom("letstagon@gmail.com");
				message.setSubject("Password Reset Notification-LetsTagOn");
				message.setSentDate(new Date());
				Map model = new HashMap();
				model.put("resetMessage",
						"Welcome to Letstagon. A platform to enhance your volunteer skills.! Please find below your reset password link. Click on it.");
				model.put("token", user.getResetPassordToken());
				model.put("name", user.getUserName());
				model.put("expiryDate",user.getResetPasswordExpiry());
				model.put("appUrl", appUrl);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "velocity/forgotPasswprdTemplate.vm", "UTF-8",
						model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}


}
