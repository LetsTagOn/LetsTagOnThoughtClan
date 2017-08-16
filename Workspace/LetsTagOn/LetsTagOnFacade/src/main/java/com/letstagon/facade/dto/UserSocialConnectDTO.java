package com.letstagon.facade.dto;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the UserSocialConnectDTO core entity.
 * 
 */

public class UserSocialConnectDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The refresh token ID. */
	private String refreshTokenID;
	
	/** The social app ID. */
	private String socialAppID;
	
	/** The social app type. */
	private SocialAppTypeDTO socialAppType;
	
	/** The user. */
	private UserDTO user;

	/**
	 * Instantiates a new user social connect DTO.
	 */
	public UserSocialConnectDTO() {
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
	 * Gets the refresh token ID.
	 *
	 * @return the refresh token ID
	 */
	public String getRefreshTokenID() {
		return this.refreshTokenID;
	}

	/**
	 * Sets the refresh token ID.
	 *
	 * @param refreshTokenID the new refresh token ID
	 */
	public void setRefreshTokenID(String refreshTokenID) {
		this.refreshTokenID = refreshTokenID;
	}


	/**
	 * Gets the social app ID.
	 *
	 * @return the social app ID
	 */
	public String getSocialAppID() {
		return this.socialAppID;
	}

	/**
	 * Sets the social app ID.
	 *
	 * @param socialAppID the new social app ID
	 */
	public void setSocialAppID(String socialAppID) {
		this.socialAppID = socialAppID;
	}


	/**
	 * Gets the social app type.
	 *
	 * @return the social app type
	 */
	public SocialAppTypeDTO getSocialAppType() {
		return this.socialAppType;
	}

	/**
	 * Sets the social app type.
	 *
	 * @param socialAppType the new social app type
	 */
	public void setSocialAppType(SocialAppTypeDTO socialAppType) {
		this.socialAppType = socialAppType;
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
