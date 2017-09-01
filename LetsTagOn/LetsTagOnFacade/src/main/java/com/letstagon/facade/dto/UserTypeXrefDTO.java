package com.letstagon.facade.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class UserTypeXrefDTO.
 */
public class UserTypeXrefDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The User DTO. */
	private UserDTO UserDTO;
	
	/** The User type DTO. */
	private UserTypeDTO UserTypeDTO;
	
	/** The active. */
	private boolean active;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
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
	 * Gets the user.
	 *
	 * @return the user
	 */
	@JsonIgnore
	public UserDTO getUser() {
		return UserDTO;
	}

	/**
	 * Sets the user.
	 *
	 * @param UserDTO the new user
	 */
	public void setUser(UserDTO UserDTO) {
		this.UserDTO = UserDTO;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public UserTypeDTO getUserType() {
		return UserTypeDTO;
	}

	/**
	 * Sets the user type.
	 *
	 * @param UserTypeDTO the new user type
	 */
	public void setUserType(UserTypeDTO UserTypeDTO) {
		this.UserTypeDTO = UserTypeDTO;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}


}
