package com.letstagon.facade.dto;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerSearchDTO.
 */
public class VolunteerSearchDTO {

	/** The name. */
	private String name;

	/** The Cause DT os. */
	private List<CauseDTO> CauseDTOs;

	/** The skills. */
	private List<JobTypeDTO> skills;

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
	 * Gets the cause DT os.
	 *
	 * @return the cause DT os
	 */
	public List<CauseDTO> getCauseDTOs() {
		return CauseDTOs;
	}

	/**
	 * Sets the cause DT os.
	 *
	 * @param CauseDTOs the new cause DT os
	 */
	public void setCauseDTOs(List<CauseDTO> CauseDTOs) {
		this.CauseDTOs = CauseDTOs;
	}

	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<JobTypeDTO> getSkills() {
		return skills;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(List<JobTypeDTO> skills) {
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
	 * Instantiates a new volunteer search DTO.
	 */
	public VolunteerSearchDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new volunteer search DTO.
	 *
	 * @param name the name
	 * @param CauseDTOs the cause DT os
	 * @param skills the skills
	 * @param location the location
	 */
	public VolunteerSearchDTO(String name, List<CauseDTO> CauseDTOs, List<JobTypeDTO> skills, String location) {
		super();
		this.name = name;
		this.CauseDTOs = CauseDTOs;
		this.skills = skills;
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VolunteerSearchDTO [name=" + name + ", CauseDTOs=" + CauseDTOs + ", skills=" + skills + ", location="
				+ location + "]";
	}

}
