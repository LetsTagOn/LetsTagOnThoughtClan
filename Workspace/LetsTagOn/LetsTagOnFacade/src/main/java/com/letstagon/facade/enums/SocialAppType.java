package com.letstagon.facade.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum SocialAppType.
 */
public enum SocialAppType {

	/** The linkedin. */
	LINKEDIN(1L);

	/** The app id. */
	private long appId;

	/**
	 * Instantiates a new social app type.
	 *
	 * @param appId the app id
	 */
	SocialAppType(long appId) {
		this.appId = appId;
	}

	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
	public long getAppId() {
		return appId;
	}

	/**
	 * Sets the app id.
	 *
	 * @param appId the new app id
	 */
	public void setAppId(long appId) {
		this.appId = appId;
	}

	
	
}
