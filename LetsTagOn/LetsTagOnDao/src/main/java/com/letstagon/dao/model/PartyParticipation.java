package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PartyParticipation database table.
 * 
 */
@Entity
@Table(name="PartyParticipation")
@NamedQuery(name="PartyParticipation.findAll", query="SELECT p FROM PartyParticipation p")
public class PartyParticipation implements Serializable {
	
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
	private JobType jobTypeBean;
	
	/** The party bean. */
	private Party partyBean;
	
	/** The opportunity bean. */
	private Opportunity opportunityBean;
	
	/** The organization. */
	private Organization organization;

	/**
	 * Instantiates a new party participation.
	 */
	public PartyParticipation() {
	}


	/**
	 * Instantiates a new party participation.
	 *
	 * @param id the id
	 */
	public PartyParticipation(long id) {
		super();
		this.id = id;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
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
	//bi-directional many-to-one association to JobType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="jobType")
	public JobType getJobTypeBean() {
		return this.jobTypeBean;
	}

	/**
	 * Sets the job type bean.
	 *
	 * @param jobTypeBean the new job type bean
	 */
	public void setJobTypeBean(JobType jobTypeBean) {
		this.jobTypeBean = jobTypeBean;
	}


	/**
	 * Gets the party bean.
	 *
	 * @return the party bean
	 */
	//bi-directional many-to-one association to Party
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="party")
	public Party getPartyBean() {
		return this.partyBean;
	}

	/**
	 * Sets the party bean.
	 *
	 * @param partyBean the new party bean
	 */
	public void setPartyBean(Party partyBean) {
		this.partyBean = partyBean;
	}


	/**
	 * Gets the opportunity bean.
	 *
	 * @return the opportunity bean
	 */
	//bi-directional many-to-one association to Opportunity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="opportunity")
	public Opportunity getOpportunityBean() {
		return this.opportunityBean;
	}

	/**
	 * Sets the opportunity bean.
	 *
	 * @param opportunityBean the new opportunity bean
	 */
	public void setOpportunityBean(Opportunity opportunityBean) {
		this.opportunityBean = opportunityBean;
	}


	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	//bi-directional many-to-one association to Organization
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="linkedOrg")
	public Organization getOrganization() {
		return this.organization;
	}

	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
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