package com.letstagon.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.letstagon.service.EmailService;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailServiceImpl.
 */
@Service
public class EmailServiceImpl implements EmailService {
	
	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;
	
	/** The velocity engine. */
	@Autowired
	private VelocityEngine velocityEngine;
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.EmailService#sendLTONotification(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendLTONotification(final String name,final String email) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(email);
				message.setFrom("letstagon@gmail.com");
				message.setSubject("Registration-LetsTagOn");
				message.setSentDate(new Date());
				Map model = new HashMap();
				model.put("regMessage",
						"Please Login with registered email and complete the next steps of your profile.");
				model.put("name", name);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "velocity/emailtemplate.vm", "UTF-8",
						model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.EmailService#sendEmail(org.springframework.mail.javamail.MimeMessagePreparator)
	 */
	@Override
	public void sendEmail(MimeMessagePreparator preparator) {
		mailSender.send(preparator);
	}
}
