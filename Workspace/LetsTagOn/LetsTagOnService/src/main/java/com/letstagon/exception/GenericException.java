package com.letstagon.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericException.
 */
public class GenericException extends RuntimeException{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The err code. */
	private String errCode;
	
	/** The err msg. */
	private String errMsg;
	
	/**
	 * Gets the err code.
	 *
	 * @return the err code
	 */
	public String getErrCode() {
		return errCode;
	}
	
	/**
	 * Sets the err code.
	 *
	 * @param errCode the new err code
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	/**
	 * Gets the err msg.
	 *
	 * @return the err msg
	 */
	public String getErrMsg() {
		return errMsg;
	}
	
	/**
	 * Sets the err msg.
	 *
	 * @param errMsg the new err msg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return "GenericException [errCode=" + errCode + ", errMsg=" + errMsg
				+ "]";
	}
	
}
