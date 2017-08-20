package com.letstagon.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class MailConfig.
 */
@Configuration
public class MailConfig {

	/** The host. */
	//@Value("${email.host}")
	private String host="smtp.gmail.com";

	/** The port. */
	//@Value("${email.port}")
	private Integer port=587;

	/** The username. */
	//@Value("${email.username}")
	private String username="info.letstagon@gmail.com";

	/** The password. */
	//@Value("${email.password}")
	private String password="Harley@1";

	/**
	 * Java mail service.
	 *
	 * @return the java mail sender
	 */
	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);

		javaMailSender.setJavaMailProperties(getMailProperties());

		return javaMailSender;
	}

	/**
	 * Gets the mail properties.
	 *
	 * @return the mail properties
	 */
	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "true");
		return properties;
	}
}
