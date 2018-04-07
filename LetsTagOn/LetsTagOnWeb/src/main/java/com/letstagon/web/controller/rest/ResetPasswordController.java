package com.letstagon.web.controller.rest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.exception.InvalidTokenException;
import com.letstagon.exception.LinkExpiredException;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.service.EmailFacade;
import com.letstagon.web.controller.ControllerConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class ResetPasswordController.
 */
@RestController(value = "/password")
public class ResetPasswordController {

	/** The email facade. */
	@Autowired
	private EmailFacade emailFacade;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ResetPasswordController.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	/**
	 * Change password.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/password/resetPassword", method = RequestMethod.POST)
	public AjaxResponseDTO changePassword(@RequestBody UserDTO user) {
		LOG.info("Change password called for user with userName " + user.getUserName() + " and the password "
				+ user.getPassword());
		AjaxResponseDTO response = new AjaxResponseDTO();
		try {
			if(StringUtils.isNotBlank(user.getPassword())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			emailFacade.resetPassword(user.getUserName(), user.getResetPassordToken(), user.getPassword());
			response.setData(ControllerConstants.StatusCodes.SUCCESS);
		} catch (LinkExpiredException le) {
			AjaxErrorDTO error = new AjaxErrorDTO();
			error.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			error.setErrorMessage(ControllerConstants.ErrorMessages.LINK_EXPIRED);
			response.setError(error);
		} catch (InvalidTokenException ite) {
			AjaxErrorDTO error = new AjaxErrorDTO();
			error.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			error.setErrorMessage(ControllerConstants.ErrorMessages.INVALID_TOKEN);
			response.setError(error);
		}
		return response;

	}

	/**
	 * Sendforgot password link.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/password/forgotPassword", method = RequestMethod.POST)
	public AjaxResponseDTO sendforgotPasswordLink(@RequestBody UserDTO user) throws Exception {
		LOG.info("forgot password called for user with emailid " + user.getEmailAddress() + " and the password "
				+ user.getEmailAddress());
		AjaxResponseDTO response = new AjaxResponseDTO();
		try {
			emailFacade.forgotPassword(user.getEmailAddress());
			response.setData(ControllerConstants.StatusCodes.SUCCESS);
			LOG.info("forgot password called for user with userName ");
		} catch (UsernameNotFoundException ue) {
			LOG.info("Username not found: " + user.getUserName());
			AjaxErrorDTO error = new AjaxErrorDTO();
			error.setErrorCode(ControllerConstants.ErrorCodes.RESOURCE_NOT_FOUND);
			error.setErrorMessage(ControllerConstants.ErrorMessages.EMAIL_NOT_FOUND);
			response.setError(error);
		}
		return response;

	}

}
