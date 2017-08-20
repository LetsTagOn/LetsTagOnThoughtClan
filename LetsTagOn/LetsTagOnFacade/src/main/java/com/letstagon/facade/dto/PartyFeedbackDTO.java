package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the PartyFeedbackDTO core entity.
 * 
 */

public class PartyFeedbackDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The comments. */
	private String comments;
	
	/** The rated on. */
	private Date ratedOn;
	
	/** The rating. */
	private float rating;
	
	/** The status. */
	private String status;
	
	/** The organization. */
	private OrganizationDTO organization;
	
	/** The user. */
	private UserDTO user;

	/**
	 * Instantiates a new party feedback DTO.
	 */
	public PartyFeedbackDTO() {
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
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * Gets the rated on.
	 *
	 * @return the rated on
	 */
	public Date getRatedOn() {
		return this.ratedOn;
	}

	/**
	 * Sets the rated on.
	 *
	 * @param ratedOn the new rated on
	 */
	public void setRatedOn(Date ratedOn) {
		this.ratedOn = ratedOn;
	}


	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public float getRating() {
		return this.rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	public OrganizationDTO getOrganization() {
		return this.organization;
	}

	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(OrganizationDTO organization) {
		this.organization = organization;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public UserDTO getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

}
