package com.letstagon.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class NonFatalException.
 */
public class NonFatalException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -384298129770087417L;

	/**
	 * Instantiates a new non fatal exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public NonFatalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new non fatal exception.
	 *
	 * @param message the message
	 */
	public NonFatalException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new non fatal exception.
	 *
	 * @param cause the cause
	 */
	public NonFatalException(Throwable cause) {
		super(cause);
	}
	
	

}
