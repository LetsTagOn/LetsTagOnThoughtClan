package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerLocationPrefDTO.
 */
public class VolunteerLocationPrefDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The user. */
	private User user;
	
	/** The location area name. */
	private String locationAreaName;
	
	/** The location city. */
	private String locationCity;
	
	/** The location postal code. */
	private String locationPostalCode;
	
	/** The include surrounding areas. */
	private boolean includeSurroundingAreas;
	
	/** The modified date. */
	private Date modifiedDate;
	
	/** The inserted date. */
	private Date insertedDate;

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
	 * Gets the user.
	 *
	 * @return the user
	 */
	@JsonIgnore
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
	 * Gets the location area name.
	 *
	 * @return the location area name
	 */
	public String getLocationAreaName() {
		return locationAreaName;
	}

	/**
	 * Sets the location area name.
	 *
	 * @param locationAreaName the new location area name
	 */
	public void setLocationAreaName(String locationAreaName) {
		this.locationAreaName = locationAreaName;
	}

	/**
	 * Gets the location city.
	 *
	 * @return the location city
	 */
	public String getLocationCity() {
		return locationCity;
	}

	/**
	 * Sets the location city.
	 *
	 * @param locationCity the new location city
	 */
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	/**
	 * Gets the location postal code.
	 *
	 * @return the location postal code
	 */
	public String getLocationPostalCode() {
		return locationPostalCode;
	}

	/**
	 * Sets the location postal code.
	 *
	 * @param locationPostalCode the new location postal code
	 */
	public void setLocationPostalCode(String locationPostalCode) {
		this.locationPostalCode = locationPostalCode;
	}

	/**
	 * Checks if is include surrounding areas.
	 *
	 * @return true, if is include surrounding areas
	 */
	public boolean isIncludeSurroundingAreas() {
		return includeSurroundingAreas;
	}

	/**
	 * Sets the include surrounding areas.
	 *
	 * @param includeSurroundingAreas the new include surrounding areas
	 */
	public void setIncludeSurroundingAreas(boolean includeSurroundingAreas) {
		this.includeSurroundingAreas = includeSurroundingAreas;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Gets the inserted date.
	 *
	 * @return the inserted date
	 */
	public Date getInsertedDate() {
		return insertedDate;
	}

	/**
	 * Sets the inserted date.
	 *
	 * @param insertedDate the new inserted date
	 */
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
}
