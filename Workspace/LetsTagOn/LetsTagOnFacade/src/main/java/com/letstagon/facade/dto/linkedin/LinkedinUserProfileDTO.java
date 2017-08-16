package com.letstagon.facade.dto.linkedin;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedinUserProfileDTO.
 */
public class LinkedinUserProfileDTO {
	
	/** The id. */
	private String id;
	
	/** The email address. */
	private String emailAddress;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The num connections. */
	private Long numConnections;
	
	/** The picture url. */
	private String pictureUrl;
	
	/** The summary. */
	private String summary;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
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
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the num connections.
	 *
	 * @return the num connections
	 */
	public Long getNumConnections() {
		return numConnections;
	}
	
	/**
	 * Sets the num connections.
	 *
	 * @param numConnections the new num connections
	 */
	public void setNumConnections(Long numConnections) {
		this.numConnections = numConnections;
	}
	
	/**
	 * Gets the picture url.
	 *
	 * @return the picture url
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}
	
	/**
	 * Sets the picture url.
	 *
	 * @param pictureUrl the new picture url
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	/**
	 * Gets the summary.
	 *
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Sets the summary.
	 *
	 * @param summary the new summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedinUserProfileDTO [id=" + id + ", emailAddress=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", numConnections=" + numConnections + ", pictureUrl=" + pictureUrl
				+ ", summary=" + summary + "]";
	}
	
	

}
