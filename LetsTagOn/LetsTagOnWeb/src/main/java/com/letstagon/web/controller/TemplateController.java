package com.letstagon.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letstagon.web.constant.LetsTagOnwebConstants.UrlConstants;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class TemplateController.
 */
@Controller
public class TemplateController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TemplateController.class);

	/** The session service. */
	@Autowired
	private LtoSessionService sessionService;
	
	/**
	 * Pre login.
	 *
	 * @return the string
	 */
	@RequestMapping("/")
	public String preLogin() {
		return "index.html";
	}
	
	/**
	 * Reset password.
	 *
	 * @return the string
	 */
	@RequestMapping("/resetPassword")
	public String resetPassword() {
		return UrlConstants.REDIRECT_URL + "change_password.html";
	}
	
	/**
	 * User.
	 *
	 * @param user the user
	 * @return the principal
	 */
	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		LOG.trace("Fetching principal : " + user);
		 user = sessionService.reload();
		 return user;
	}

	/**
	 * Home.
	 *
	 * @return the map
	 */
	@RequestMapping("/resource")
	@ResponseBody
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}
}
