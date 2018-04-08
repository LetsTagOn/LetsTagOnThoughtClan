package com.letstagon.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.service.security.CurrentUser;
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

	/** The user model converter. */
	@Autowired
	private Converter<User, UserDTO> userModelConverter;
	
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
	 * Reset password.
	 *
	 * @return the string
	 */
	@RequestMapping("/verifyToken")
	public String verifyToken() {
		return UrlConstants.REDIRECT_URL + "otp.html";
	}
	
	/**
	 * User.
	 *
	 * @param principal the user
	 * @return the principal
	 */
	@RequestMapping("/user")
	@ResponseBody
	public UserDTO user(Principal principal) {
		LOG.trace("Fetching principal : " + principal);
		 principal = sessionService.reload();
		 Authentication authentication = (Authentication) principal;
		 CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
		 User user = currentUser.getUser();
		 UserDTO userDTO = userModelConverter.convert(user);
		 return userDTO;
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
