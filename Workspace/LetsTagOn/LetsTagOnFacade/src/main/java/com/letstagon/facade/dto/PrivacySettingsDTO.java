package com.letstagon.facade.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PrivacySettingsDTO.
 */
public class PrivacySettingsDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The user. */
	private UserDTO user;
	
	/** The mobile number visibility. */
	private Boolean mobileNumberVisibility;
	
	/** The profile details visibility. */
	private Boolean profileDetailsVisibility;
	
	/** The email alerts on. */
	private Boolean emailAlertsOn;
	
	/** The email notification frequency. */
	private Boolean emailNotificationFrequency;

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
	 * Gets the mobile number visibility.
	 *
	 * @return the mobile number visibility
	 */
	public Boolean getMobileNumberVisibility() {
		return mobileNumberVisibility;
	}

	/**
	 * Sets the mobile number visibility.
	 *
	 * @param mobileNumberVisibility the new mobile number visibility
	 */
	public void setMobileNumberVisibility(Boolean mobileNumberVisibility) {
		this.mobileNumberVisibility = mobileNumberVisibility;
	}

	/**
	 * Gets the profile details visibility.
	 *
	 * @return the profile details visibility
	 */
	public Boolean getProfileDetailsVisibility() {
		return profileDetailsVisibility;
	}

	/**
	 * Sets the profile details visibility.
	 *
	 * @param profileDetailsVisibility the new profile details visibility
	 */
	public void setProfileDetailsVisibility(Boolean profileDetailsVisibility) {
		this.profileDetailsVisibility = profileDetailsVisibility;
	}

	/**
	 * Gets the email alerts on.
	 *
	 * @return the email alerts on
	 */
	public Boolean getEmailAlertsOn() {
		return emailAlertsOn;
	}

	/**
	 * Sets the email alerts on.
	 *
	 * @param emailAlertsOn the new email alerts on
	 */
	public void setEmailAlertsOn(Boolean emailAlertsOn) {
		this.emailAlertsOn = emailAlertsOn;
	}

	/**
	 * Gets the email notification frequency.
	 *
	 * @return the email notification frequency
	 */
	public Boolean getEmailNotificationFrequency() {
		return emailNotificationFrequency;
	}

	/**
	 * Sets the email notification frequency.
	 *
	 * @param emailNotificationFrequency the new email notification frequency
	 */
	public void setEmailNotificationFrequency(Boolean emailNotificationFrequency) {
		this.emailNotificationFrequency = emailNotificationFrequency;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
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
