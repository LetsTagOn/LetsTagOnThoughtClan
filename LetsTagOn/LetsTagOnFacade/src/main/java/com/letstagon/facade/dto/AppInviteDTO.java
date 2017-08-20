package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the AppInviteDTO core entity.
 * 
 */

public class AppInviteDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The email address. */
	private String emailAddress;
	
	/** The invited on. */
	private Date invitedOn;
	
	/** The status. */
	private String status;
	
	/** The social app type bean. */
	private SocialAppTypeDTO socialAppTypeBean;
	
	/** The user. */
	private UserDTO user;

	/**
	 * Instantiates a new app invite DTO.
	 */
	public AppInviteDTO() {
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
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	/**
	 * Gets the invited on.
	 *
	 * @return the invited on
	 */
	public Date getInvitedOn() {
		return this.invitedOn;
	}

	/**
	 * Sets the invited on.
	 *
	 * @param invitedOn the new invited on
	 */
	public void setInvitedOn(Date invitedOn) {
		this.invitedOn = invitedOn;
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
	 * Gets the social app type bean.
	 *
	 * @return the social app type bean
	 */
	public SocialAppTypeDTO getSocialAppTypeBean() {
		return this.socialAppTypeBean;
	}

	/**
	 * Sets the social app type bean.
	 *
	 * @param socialAppTypeBean the new social app type bean
	 */
	public void setSocialAppTypeBean(SocialAppTypeDTO socialAppTypeBean) {
		this.socialAppTypeBean = socialAppTypeBean;
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
