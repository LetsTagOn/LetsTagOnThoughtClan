package com.letstagon.facade.dto.linkedin;


// TODO: Auto-generated Javadoc
/**
 * The Class LinkedinUserExperienceDTO.
 */
public class LinkedinUserExperienceDTO {

	/** The end date. */
	private LinkedInDateDTO endDate;
	
	/** The location. *
	private String location;
	/** The organization name. */
	private LinkedinLocationDTO location;
	
	/** The company. */
	private LinkedinCompanyDTO company;

	/** The summary. */
	private String summary;
	
	/** The is current. */
	private Boolean isCurrent;
	
	/** The start date. */
	private LinkedInDateDTO startDate;
	
	/** The title. */
	private String title;

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public LinkedinCompanyDTO getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(LinkedinCompanyDTO company) {
		this.company = company;
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

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedinUserExperienceDTO [endDate=" + endDate + ", location="
				+ location + ", company=" + company + ", summary=" + summary
				+ ", isCurrent=" + isCurrent + ", startDate=" + startDate
				+ ", title=" + title + "]";
	}

	/**
	 * Gets the checks if is current.
	 *
	 * @return the isCurrent
	 */
	public Boolean getIsCurrent() {
		return isCurrent;
	}

	/**
	 * Sets the checks if is current.
	 *
	 * @param isCurrent the isCurrent to set
	 */
	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public LinkedinLocationDTO getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the location to set
	 */
	public void setLocation(LinkedinLocationDTO location) {
		this.location = location;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public LinkedInDateDTO getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(LinkedInDateDTO endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public LinkedInDateDTO getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(LinkedInDateDTO startDate) {
		this.startDate = startDate;
	}
}
