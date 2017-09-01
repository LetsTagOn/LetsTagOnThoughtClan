package com.letstagon.web.controller;

// TODO: Auto-generated Javadoc
/**
 * The Interface ControllerConstants.
 */
public interface ControllerConstants {
	
	/**
	 * The Interface ErrorCodes.
	 */
	interface ErrorCodes {

		/** The bad request. */
		int BAD_REQUEST = 400;
		
		/** The internal error. */
		int INTERNAL_ERROR = 500;
		
		/** The resource not found. */
		int RESOURCE_NOT_FOUND = 404;

	}

	/**
	 * The Interface ErrorMessages.
	 */
	interface ErrorMessages {

		/** The bad request. */
		String BAD_REQUEST = "The input provided is incorrect.";
		
		/** The internal error. */
		String INTERNAL_ERROR = "Internal error occurred. Unable to process.";
		
		/** The resource not found. */
		String RESOURCE_NOT_FOUND = "Requested resource was not found/unable to get the resource.";
		
		/** The link expired. */
		String LINK_EXPIRED = "The link has been expired.";
		
		/** The invalid token. */
		String INVALID_TOKEN = "The token is invalid. Please send a valid token.";
		
		/** The user not found. */
		String USER_NOT_FOUND = "The user name is not available in database.";
		
		/** The user already exist. */
		String USER_ALREADY_EXIST="User already exists";
		
		/** The invalid credentials. */
		String INVALID_CREDENTIALS = "Invalid Credentials";
		
		/** The save entity error. */
		String SAVE_ENTITY_ERROR = "There is some issue in saving/updating user data. Please check if proper values are filled.";
		
		/** The database connection error. */
		String DATABASE_CONNECTION_ERROR = "Technical issue in connecting to database. Please try after sometime. Sorry for the inconvineance.";
		
		/** The generic exception. */
		String GENERIC_EXCEPTION="Some error occured. Please contact the administrator. Sorry for the inconvineance.";
		
		/** The email not found. */
		String EMAIL_NOT_FOUND = "User with email provided is not found in the system.";
	}

	/**
	 * The Interface StatusCodes.
	 */
	interface StatusCodes {
		
		/** The success. */
		int SUCCESS = 200;
	}
}
