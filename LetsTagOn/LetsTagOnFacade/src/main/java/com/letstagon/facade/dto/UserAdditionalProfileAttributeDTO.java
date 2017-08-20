package com.letstagon.facade.dto;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the UserAdditionalProfileAttributeDTO core entity.
 * 
 */

public class UserAdditionalProfileAttributeDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The value. */
	private String value;
	
	/** The additional profile attribute. */
	private AdditionalProfileAttributeDTO additionalProfileAttribute;
	
	/** The user bean. */
	private UserDTO userBean;

	/**
	 * Instantiates a new user additional profile attribute DTO.
	 */
	public UserAdditionalProfileAttributeDTO() {
	}


	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/**
	 * Gets the additional profile attribute.
	 *
	 * @return the additional profile attribute
	 */
	public AdditionalProfileAttributeDTO getAdditionalProfileAttribute() {
		return this.additionalProfileAttribute;
	}

	/**
	 * Sets the additional profile attribute.
	 *
	 * @param additionalProfileAttribute the new additional profile attribute
	 */
	public void setAdditionalProfileAttribute(AdditionalProfileAttributeDTO additionalProfileAttribute) {
		this.additionalProfileAttribute = additionalProfileAttribute;
	}


	/**
	 * Gets the user bean.
	 *
	 * @return the user bean
	 */
	public UserDTO getUserBean() {
		return this.userBean;
	}

	/**
	 * Sets the user bean.
	 *
	 * @param userBean the new user bean
	 */
	public void setUserBean(UserDTO userBean) {
		this.userBean = userBean;
	}

}
