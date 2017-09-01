package com.letstagon.dao.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerSearchModel.
 */
public class VolunteerSearchModel {

	/** The name. */
	private String name;

	/** The causes. */
	private List<Cause> causes;

	/** The skills. */
	private List<JobType> skills;

	/** The location. */
	private String location;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the causes.
	 *
	 * @return the causes
	 */
	public List<Cause> getCauses() {
		return causes;
	}

	/**
	 * Sets the causes.
	 *
	 * @param causes the new causes
	 */
	public void setCauses(List<Cause> causes) {
		this.causes = causes;
	}

	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<JobType> getSkills() {
		return skills;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(List<JobType> skills) {
		this.skills = skills;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
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
	 * Instantiates a new volunteer search model.
	 */
	public VolunteerSearchModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new volunteer search model.
	 *
	 * @param name the name
	 * @param causes the causes
	 * @param skills the skills
	 * @param location the location
	 */
	public VolunteerSearchModel(String name, List<Cause> causes, List<JobType> skills, String location) {
		super();
		this.name = name;
		this.causes = causes;
		this.skills = skills;
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VolunteerSearchDTO [name=" + name + ", causes=" + causes + ", skills=" + skills + ", location="
				+ location + "]";
	}
	
	
	
	
}
