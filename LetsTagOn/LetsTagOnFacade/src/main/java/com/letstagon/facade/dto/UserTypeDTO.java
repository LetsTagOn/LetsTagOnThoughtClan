package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the UserTypeDTO core entity.
 * 
 */
public class UserTypeDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The description. */
	private String description;
	
	/** The name. */
	private String name;
	
	/** The user type attribute xrefs. */
	private List<UserTypeAttributeXrefDTO> userTypeAttributeXrefs;

	/**
	 * Instantiates a new user type DTO.
	 */
	public UserTypeDTO() {
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the user type attribute xrefs.
	 *
	 * @return the user type attribute xrefs
	 */
	public List<UserTypeAttributeXrefDTO> getUserTypeAttributeXrefs() {
		return this.userTypeAttributeXrefs;
	}

	/**
	 * Sets the user type attribute xrefs.
	 *
	 * @param userTypeAttributeXrefs the new user type attribute xrefs
	 */
	public void setUserTypeAttributeXrefs(List<UserTypeAttributeXrefDTO> userTypeAttributeXrefs) {
		this.userTypeAttributeXrefs = userTypeAttributeXrefs;
	}

	/**
	 * Adds the user type attribute xref.
	 *
	 * @param userTypeAttributeXref the user type attribute xref
	 * @return the user type attribute xref DTO
	 */
	public UserTypeAttributeXrefDTO addUserTypeAttributeXref(UserTypeAttributeXrefDTO userTypeAttributeXref) {
		getUserTypeAttributeXrefs().add(userTypeAttributeXref);
		userTypeAttributeXref.setUserTypeBean(this);

		return userTypeAttributeXref;
	}

	/**
	 * Removes the user type attribute xref.
	 *
	 * @param userTypeAttributeXref the user type attribute xref
	 * @return the user type attribute xref DTO
	 */
	public UserTypeAttributeXrefDTO removeUserTypeAttributeXref(UserTypeAttributeXrefDTO userTypeAttributeXref) {
		getUserTypeAttributeXrefs().remove(userTypeAttributeXref);
		userTypeAttributeXref.setUserTypeBean(null);

		return userTypeAttributeXref;
	}

}
