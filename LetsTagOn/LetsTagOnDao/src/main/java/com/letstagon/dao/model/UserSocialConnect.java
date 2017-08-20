package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the UserSocialConnect database table.
 * 
 */
@Entity
@Table(name="UserSocialConnect")
@NamedQuery(name="UserSocialConnect.findAll", query="SELECT u FROM UserSocialConnect u")
public class UserSocialConnect implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The refresh token ID. */
	private String refreshTokenID;
	
	/** The social app ID. */
	private String socialAppID;
	
	/** The social app type. */
	private SocialAppType socialAppType;
	
	/** The user. */
	private User user;

	/**
	 * Instantiates a new user social connect.
	 */
	public UserSocialConnect() {
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	//bi-directional many-to-one association to SocialAppType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="socialAppTypeID")
	public SocialAppType getSocialAppType() {
		return this.socialAppType;
	}

	/**
	 * Sets the social app type.
	 *
	 * @param socialAppType the new social app type
	 */
	public void setSocialAppType(SocialAppType socialAppType) {
		this.socialAppType = socialAppType;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userID")
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

}