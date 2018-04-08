package com.letstagon.web.controller.rest;

import java.util.Random;

import javax.persistence.NonUniqueResultException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.UserRepository;
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
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
			user.setAccountVerified(null);
			user.setToken(this.getToken());
			if(StringUtils.isNotBlank(user.getPassword())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			UserDTO response = userFacade.create(user);
			responseDTO.setData(response);
			// to send email to user after registration
			emailFacade.sendLTONotificationEmailVerfication(response.getName(), response.getEmailAddress(), response.getToken());
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
	 * Register customer verification.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/register/verifyOtp", method = RequestMethod.POST)
	public AjaxResponseDTO registerCustomerVerification(@RequestBody UserDTO user) throws Exception {
		LOG.info("User Account Activation for" + user.getName());
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		User userVerify = userRepository.findOneByUserName(user.getUserName());
		try {
			if(user.getToken().equals(userVerify.getToken())){
				userVerify.setAccountVerified("1");
				userVerify.setToken("");
				userRepository.save(userVerify);
				// to send email to user after registration
				emailFacade.sendLTONotification(userVerify.getName(), userVerify.getEmailAddress());
				LOG.info("Registration Email sent successfully to the user with email: " + user.getEmailAddress());
			
				responseDTO.setData(ControllerConstants.StatusCodes.SUCCESS);
			}
			else{
				AjaxErrorDTO errorDTO = new AjaxErrorDTO();
				errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
				errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.INVALID_OTP);
				responseDTO.setError(errorDTO);
			}
				
		} catch (Exception nue) {
			LOG.info("INVALID" + user.getUserName());
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.INVALID_OTP);
			responseDTO.setError(errorDTO);
		}

		return responseDTO;

	}
	
	private String getToken() {
		// TODO Auto-generated method stub
		String numbers = "0123456789";
		Random rndm_method = new Random();
        String OTP = "";
        for (int i = 0; i < 4; i++)
        {
        	OTP = OTP+numbers.charAt(rndm_method.nextInt(numbers.length()));
 
        }
        return OTP;
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
