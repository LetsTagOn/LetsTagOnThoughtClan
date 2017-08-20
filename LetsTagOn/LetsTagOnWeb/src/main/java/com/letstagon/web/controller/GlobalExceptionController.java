package com.letstagon.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.letstagon.exception.GenericException;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionController.
 */
@ControllerAdvice
public class GlobalExceptionController {
	
	/** The model. */
	Model model;
	
	/**
	 * Handle custom exception.
	 *
	 * @param ex the ex
	 * @return the string
	 */
	@ExceptionHandler(GenericException.class)
	public String handleCustomException(GenericException ex) {
		model.addAttribute("errCode", ex.getErrCode());
		model.addAttribute("errMsg", ex.getErrMsg());
		return "error";
	}
}
