package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the PartyParticipationDTO core entity.
 * 
 */

public class PartyParticipationDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The attendance. */
	private Boolean attendance;
	
	/** The date end. */
	private Date dateEnd;
	
	/** The date start. */
	private Date dateStart;
	
	/** The org link approved. */
	private Boolean orgLinkApproved;
	
	/** The participation type. */
	private String participationType;
	
	/** The rating. */
	private Float rating;
	
	/** The review. */
	private String review;
	
	/** The status. */
	private Boolean status;
	
	/** The job type bean. */
	private JobTypeDTO jobTypeBean;
	
	/** The party bean. */
	private PartyDTO partyBean;
	
	/** The opportunity bean. */
	private OpportunityDTO opportunityBean;
	
	/** The organization. */
	private OrganizationDTO organization;

	/**
	 * Instantiates a new party participation DTO.
	 */
	public PartyParticipationDTO() {
	}

	/**
	 * Instantiates a new party participation DTO.
	 *
	 * @param id the id
	 */
	public PartyParticipationDTO(long id) {
		super();
		this.id = id;
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
	 * Gets the attendance.
	 *
	 * @return the attendance
	 */
	public Boolean getAttendance() {
		return this.attendance;
	}

	/**
	 * Sets the attendance.
	 *
	 * @param attendance the new attendance
	 */
	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}

	/**
	 * Gets the date end.
	 *
	 * @return the date end
	 */
	public Date getDateEnd() {
		return this.dateEnd;
	}

	/**
	 * Sets the date end.
	 *
	 * @param dateEnd the new date end
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * Gets the date start.
	 *
	 * @return the date start
	 */
	public Date getDateStart() {
		return this.dateStart;
	}

	/**
	 * Sets the date start.
	 *
	 * @param dateStart the new date start
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Gets the org link approved.
	 *
	 * @return the org link approved
	 */
	public Boolean getOrgLinkApproved() {
		return this.orgLinkApproved;
	}

	/**
	 * Sets the org link approved.
	 *
	 * @param orgLinkApproved the new org link approved
	 */
	public void setOrgLinkApproved(Boolean orgLinkApproved) {
		this.orgLinkApproved = orgLinkApproved;
	}

	/**
	 * Gets the participation type.
	 *
	 * @return the participation type
	 */
	public String getParticipationType() {
		return this.participationType;
	}

	/**
	 * Sets the participation type.
	 *
	 * @param participationType the new participation type
	 */
	public void setParticipationType(String participationType) {
		this.participationType = participationType;
	}


	/**
	 * Gets the review.
	 *
	 * @return the review
	 */
	public String getReview() {
		return this.review;
	}

	/**
	 * Sets the review.
	 *
	 * @param review the new review
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Boolean getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * Gets the job type bean.
	 *
	 * @return the job type bean
	 */
	public JobTypeDTO getJobTypeBean() {
		return this.jobTypeBean;
	}

	/**
	 * Sets the job type bean.
	 *
	 * @param jobTypeBean the new job type bean
	 */
	public void setJobTypeBean(JobTypeDTO jobTypeBean) {
		this.jobTypeBean = jobTypeBean;
	}

	/**
	 * Gets the party bean.
	 *
	 * @return the party bean
	 */
	public PartyDTO getPartyBean() {
		return this.partyBean;
	}

	/**
	 * Sets the party bean.
	 *
	 * @param partyBean the new party bean
	 */
	public void setPartyBean(PartyDTO partyBean) {
		this.partyBean = partyBean;
	}

	/**
	 * Gets the opportunity bean.
	 *
	 * @return the opportunity bean
	 */
	public OpportunityDTO getOpportunityBean() {
		return this.opportunityBean;
	}

	/**
	 * Sets the opportunity bean.
	 *
	 * @param opportunityBean the new opportunity bean
	 */
	public void setOpportunityBean(OpportunityDTO opportunityBean) {
		this.opportunityBean = opportunityBean;
	}

	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	public OrganizationDTO getOrganization() {
		return this.organization;
	}

	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(OrganizationDTO organization) {
		this.organization = organization;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PartyParticipationDTO [id=" + id + ", attendance=" + attendance + ", dateEnd=" + dateEnd
				+ ", dateStart=" + dateStart + ", rating=" + rating + ", review=" + review + ", status=" + status
				+ ", jobTypeBean=" + jobTypeBean + ", partyBean=" + partyBean + ", opportunityBean=" + opportunityBean
				+ "]";
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Float getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(Float rating) {
		this.rating = rating;
	}

}
