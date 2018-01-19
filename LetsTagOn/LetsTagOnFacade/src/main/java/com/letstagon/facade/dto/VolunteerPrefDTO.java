package com.letstagon.facade.dto;

import java.io.Serializable;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the VolunteerPrefDTO core entity.
 * 
 */

public class VolunteerPrefDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The day. */
	private String day;
	
	/** The end time. */
	private Time endTime;
	
	/** The location. */
	private String location;
	
	/** The location max radius. */
	private float locationMaxRadius;
	
	/** The start time. */
	private Time startTime;
	
	/** The type. */
	private String type;
	
	/** The user. */
	private UserDTO user;
	
	/** The is available. */
	private Boolean isAvailable;
	
	/** The type. */
	private String commitment;

	/**
	 * Gets the checks if is available.
	 *
	 * @return the checks if is available
	 */
	public Boolean getIsAvailable() {
		return isAvailable;
	}




	/**
	 * Sets the checks if is available.
	 *
	 * @param isAvailable the new checks if is available
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}




	/**
	 * Instantiates a new volunteer pref DTO.
	 */
	public VolunteerPrefDTO() {
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
	 * Gets the day.
	 *
	 * @return the day
	 */
	public String getDay() {
		return this.day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the new day
	 */
	public void setDay(String day) {
		this.day = day;
	}


	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public Time getEndTime() {
		return this.endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}


	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * Gets the location max radius.
	 *
	 * @return the location max radius
	 */
	public float getLocationMaxRadius() {
		return this.locationMaxRadius;
	}

	/**
	 * Sets the location max radius.
	 *
	 * @param locationMaxRadius the new location max radius
	 */
	public void setLocationMaxRadius(float locationMaxRadius) {
		this.locationMaxRadius = locationMaxRadius;
	}


	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public Time getStartTime() {
		return this.startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the commitment.
	 *
	 * @return the commitment
	 */
	public String getCommitment() {
		return commitment;
	}

	/**
	 * Sets the commitment.
	 *
	 * @param type the commitment
	 */
	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@JsonIgnore
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
