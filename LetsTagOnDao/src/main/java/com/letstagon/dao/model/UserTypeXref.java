package com.letstagon.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class UserTypeXref.
 */
@Entity
@NamedQuery(name = "UserTypeXref.findAll", query = "SELECT u FROM UserTypeXref u")
public class UserTypeXref implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The user. */
	private User user;
	
	/** The user type. */
	private UserType userType;
	
	/** The active. */
	private boolean active;
	

	/**
	 * Instantiates a new user type xref.
	 */
	public UserTypeXref() {
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userType")
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
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
