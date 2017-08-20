package com.letstagon.facade.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the UserTypeAttributeXrefDTO core entity.
 * 
 */

public class UserTypeAttributeXrefDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The compulsory. */
	private Boolean compulsory;
	
	/** The additional profile attribute. */
	private AdditionalProfileAttributeDTO additionalProfileAttribute;
	
	/** The user type bean. */
	private UserTypeDTO userTypeBean;

	/**
	 * Instantiates a new user type attribute xref DTO.
	 */
	public UserTypeAttributeXrefDTO() {
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
	 * Gets the compulsory.
	 *
	 * @return the compulsory
	 */
	public Boolean getCompulsory() {
		return this.compulsory;
	}

	/**
	 * Sets the compulsory.
	 *
	 * @param compulsory the new compulsory
	 */
	public void setCompulsory(Boolean compulsory) {
		this.compulsory = compulsory;
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
	 * Gets the user type bean.
	 *
	 * @return the user type bean
	 */
	public UserTypeDTO getUserTypeBean() {
		return this.userTypeBean;
	}

	/**
	 * Sets the user type bean.
	 *
	 * @param userTypeBean the new user type bean
	 */
	public void setUserTypeBean(UserTypeDTO userTypeBean) {
		this.userTypeBean = userTypeBean;
	}

}
