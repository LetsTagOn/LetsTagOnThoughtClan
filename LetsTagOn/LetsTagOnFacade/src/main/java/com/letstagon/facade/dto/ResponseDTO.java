package com.letstagon.facade.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseDTO.
 */
public class ResponseDTO {
	
	/** The message. */
	private String message;
	
	/** The status code. */
	private int statusCode;
	
	/** The return object. */
	private Object returnObject;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the return object.
	 *
	 * @return the return object
	 */
	public Object getReturnObject() {
		return returnObject;
	}

	/**
	 * Sets the return object.
	 *
	 * @param returnObject the new return object
	 */
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
}
