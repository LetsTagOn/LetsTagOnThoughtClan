package com.letstagon.facade.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class AjaxResponseDTO.
 */
public class AjaxResponseDTO {
	
	/** The data. */
	private Object data;

	/** The error. */
	private AjaxErrorDTO error;

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public AjaxErrorDTO getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(AjaxErrorDTO error) {
		this.error = error;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Instantiates a new ajax response DTO.
	 *
	 * @param data the data
	 */
	public AjaxResponseDTO(Object data) {
		super();
		this.data = data;
	}

	/**
	 * Instantiates a new ajax response DTO.
	 *
	 * @param error the error
	 */
	public AjaxResponseDTO(AjaxErrorDTO error) {
		super();
		this.error = error;
	}

	/**
	 * Instantiates a new ajax response DTO.
	 */
	public AjaxResponseDTO() {
		super();
	}

}
