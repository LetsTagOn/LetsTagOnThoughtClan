package com.letstagon.web.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.web.controller.ControllerConstants;

// TODO: Auto-generated Javadoc
/**
 * To handle runtime exceptions.
 * 
 * @author Thoughtclan
 *
 */

@ControllerAdvice
public class LTORestControllerExceptionHandlerAdvice {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LTORestControllerExceptionHandlerAdvice.class);

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the ajax response DTO
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	AjaxResponseDTO handleException(Exception ex) {
		LOG.error("Generic exception: " + ex);
		LOG.info("Exception message :" + ex.getLocalizedMessage());

		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		AjaxErrorDTO errorDTO = new AjaxErrorDTO();
		errorDTO.setErrorCode(ControllerConstants.ErrorCodes.INTERNAL_ERROR);
		errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.GENERIC_EXCEPTION);
		responseDTO.setError(errorDTO);
		return responseDTO;

	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the ajax response DTO
	 */
	@ResponseBody
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	AjaxResponseDTO handleException(DataAccessException ex) {
		LOG.error("Generic exception: " + ex);
		LOG.info("Exception message :" + ex.getLocalizedMessage());

		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		AjaxErrorDTO errorDTO = new AjaxErrorDTO();
		errorDTO.setErrorCode(ControllerConstants.ErrorCodes.RESOURCE_NOT_FOUND);
		errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
		responseDTO.setError(errorDTO);

		return responseDTO;

	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the ajax response DTO
	 */
	@ResponseBody
	@ExceptionHandler(CannotCreateTransactionException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	AjaxResponseDTO handleException(CannotCreateTransactionException ex) {
		LOG.error("Generic exception: " + ex);
		LOG.info("Exception message :" + ex.getLocalizedMessage());

		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		AjaxErrorDTO errorDTO = new AjaxErrorDTO();
		errorDTO.setErrorCode(ControllerConstants.ErrorCodes.INTERNAL_ERROR);
		errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.DATABASE_CONNECTION_ERROR);
		responseDTO.setError(errorDTO);

		return responseDTO;

	}
}
