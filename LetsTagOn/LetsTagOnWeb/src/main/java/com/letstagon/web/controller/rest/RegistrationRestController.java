package com.letstagon.web.controller.rest;

import javax.persistence.NonUniqueResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.facade.UserFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.service.EmailFacade;
import com.letstagon.web.controller.ControllerConstants;
// TODO: Auto-generated Javadoc

/**
 * The Class RegistrationRestController.
 */
@RestController(value="/register")
public class RegistrationRestController {
	
	/** The user facade. */
	@Autowired
	private UserFacade userFacade;

	/** The email facade. */
	@Autowired
	private EmailFacade emailFacade;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationRestController.class);

	/**
	 * Register customer to portal.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/register/customer", method = RequestMethod.POST)
	public AjaxResponseDTO registerCustomerToPortal(@RequestBody UserDTO user) throws Exception {
		LOG.info("Registartion process initiated for a customer name:" + user.getName() + " and with email:"
				+ user.getEmailAddress());
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			UserDTO response = userFacade.create(user);
			responseDTO.setData(response);
			// to send email to user after registration
			emailFacade.sendLTONotification(response.getName(), response.getEmailAddress());
			LOG.info("Registration Email sent successfully to the user with email: " + user.getEmailAddress());

			LOG.info("Registartion process completed for a customer name:" + user.getName() + " and with email:"
					+ user.getEmailAddress() + " and userId is:" + user.getId());
		} catch (NonUniqueResultException nue) {
			LOG.info("User Name already exist: " + user.getUserName());
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.USER_ALREADY_EXIST);
			responseDTO.setError(errorDTO);
		}

		return responseDTO;

	}
	
	/**
	 * Customer contact message mail service.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/contact/us", method = RequestMethod.POST)
	public AjaxResponseDTO contactUs(@RequestBody UserDTO user) throws Exception {
		LOG.info("Contact us details of:" + user.getName() + " and with email:"
				+ user.getEmailAddress());
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			// to send email to user after registration
			emailFacade.sendContactUsMail(user.getUserName(), user.getEmailAddress(), user.getSummary());
			LOG.info("contact us Email sent successfully to the user with email: " + user.getEmailAddress());
			responseDTO.setData("success");
		} catch (NonUniqueResultException nue) {
			LOG.info("User details Error: " + user.getUserName());
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.USER_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}

		return responseDTO;

	}

}
