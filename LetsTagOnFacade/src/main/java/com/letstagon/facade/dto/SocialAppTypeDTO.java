package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the SocialAppTypeDTO core entity.
 * 
 */

public class SocialAppTypeDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The auth method. */
	private String authMethod;
	
	/** The auth url. */
	private String authUrl;
	
	/** The name. */
	private String name;
	
	/** The status. */
	private String status;
	
	/** The app invites. */
	private List<AppInviteDTO> appInvites;
	
	/** The user social connects. */
	private List<UserSocialConnectDTO> userSocialConnects;

	/**
	 * Instantiates a new social app type DTO.
	 */
	public SocialAppTypeDTO() {
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
	 * Gets the auth method.
	 *
	 * @return the auth method
	 */
	public String getAuthMethod() {
		return this.authMethod;
	}

	/**
	 * Sets the auth method.
	 *
	 * @param authMethod the new auth method
	 */
	public void setAuthMethod(String authMethod) {
		this.authMethod = authMethod;
	}


	/**
	 * Gets the auth url.
	 *
	 * @return the auth url
	 */
	public String getAuthUrl() {
		return this.authUrl;
	}

	/**
	 * Sets the auth url.
	 *
	 * @param authUrl the new auth url
	 */
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
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
	 * Gets the app invites.
	 *
	 * @return the app invites
	 */
	public List<AppInviteDTO> getAppInvites() {
		return this.appInvites;
	}

	/**
	 * Sets the app invites.
	 *
	 * @param appInvites the new app invites
	 */
	public void setAppInvites(List<AppInviteDTO> appInvites) {
		this.appInvites = appInvites;
	}

	/**
	 * Adds the app invite.
	 *
	 * @param appInvite the app invite
	 * @return the app invite DTO
	 */
	public AppInviteDTO addAppInvite(AppInviteDTO appInvite) {
		getAppInvites().add(appInvite);
		appInvite.setSocialAppTypeBean(this);

		return appInvite;
	}

	/**
	 * Removes the app invite.
	 *
	 * @param appInvite the app invite
	 * @return the app invite DTO
	 */
	public AppInviteDTO removeAppInvite(AppInviteDTO appInvite) {
		getAppInvites().remove(appInvite);
		appInvite.setSocialAppTypeBean(null);

		return appInvite;
	}


	/**
	 * Gets the user social connects.
	 *
	 * @return the user social connects
	 */
	public List<UserSocialConnectDTO> getUserSocialConnects() {
		return this.userSocialConnects;
	}

	/**
	 * Sets the user social connects.
	 *
	 * @param userSocialConnects the new user social connects
	 */
	public void setUserSocialConnects(List<UserSocialConnectDTO> userSocialConnects) {
		this.userSocialConnects = userSocialConnects;
	}

	/**
	 * Adds the user social connect.
	 *
	 * @param userSocialConnect the user social connect
	 * @return the user social connect DTO
	 */
	public UserSocialConnectDTO addUserSocialConnect(UserSocialConnectDTO userSocialConnect) {
		getUserSocialConnects().add(userSocialConnect);
		userSocialConnect.setSocialAppType(this);

		return userSocialConnect;
	}

	/**
	 * Removes the user social connect.
	 *
	 * @param userSocialConnect the user social connect
	 * @return the user social connect DTO
	 */
	public UserSocialConnectDTO removeUserSocialConnect(UserSocialConnectDTO userSocialConnect) {
		getUserSocialConnects().remove(userSocialConnect);
		userSocialConnect.setSocialAppType(null);

		return userSocialConnect;
	}

}
