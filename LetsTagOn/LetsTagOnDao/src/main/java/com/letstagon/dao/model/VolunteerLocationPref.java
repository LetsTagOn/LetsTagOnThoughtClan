package com.letstagon.dao.model;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="VolunteerLocationPref.findAll", query="SELECT v FROM VolunteerLocationPref v")
public class VolunteerLocationPref implements Serializable {
	
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
	 * Instantiates a new volunteer location pref.
	 */
	public VolunteerLocationPref() {
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
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="volunteer")
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
