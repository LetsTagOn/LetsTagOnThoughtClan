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
 * The Class PrivacySettings.
 */
@Entity
@NamedQuery(name = "PrivacySettings.findAll", query = "SELECT p FROM PrivacySettings p")
public class PrivacySettings implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The user. */
	private User user;
	
	/** The mobile number visibility. */
	private Boolean mobileNumberVisibility;
	
	/** The profile details visibility. */
	private Boolean profileDetailsVisibility;
	
	/** The email alerts on. */
	private Boolean emailAlertsOn;
	
	/** The email notification frequency. */
	private Boolean emailNotificationFrequency;

	/**
	 * Instantiates a new privacy settings.
	 */
	public PrivacySettings() {

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
}
