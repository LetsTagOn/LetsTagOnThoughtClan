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
@Table(name = "OpportunityInvite")
@NamedQuery(name = "OpportunityInvite.findAll", query = "SELECT oi FROM OpportunityInvite oi")
public class OpportunityInvite implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The accepted. */
	private Boolean accepted;
	
	/** The invited date. */
	private Date invitedDate;
	
	/** The invite accepted date. */
	private Date inviteAcceptedDate;
	
	/** The status. */
	private Boolean status;
	
	/** The job type bean. */
	private JobType jobTypeBean;
	
	/** The invited party. */
	private Party invitedParty;
	
	/** The invited by party. */
	private Party invitedByParty;
	
	/** The opportunity bean. */
	private Opportunity opportunityBean;

	/**
	 * Instantiates a new opportunity invite.
	 */
	public OpportunityInvite() {
	}
	
	

	/**
	 * Instantiates a new opportunity invite.
	 *
	 * @param invitedParty the invited party
	 * @param invitedByParty the invited by party
	 * @param opportunityBean the opportunity bean
	 */
	public OpportunityInvite(Party invitedParty, Party invitedByParty, Opportunity opportunityBean) {
		super();
		this.invitedParty = invitedParty;
		this.invitedByParty = invitedByParty;
		this.opportunityBean = opportunityBean;
		this.invitedDate=new Date();
	}



	/**
	 * Instantiates a new opportunity invite.
	 *
	 * @param id the id
	 */
	public OpportunityInvite(long id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * Gets the accepted.
	 *
	 * @return the accepted
	 */
	public Boolean getAccepted() {
		return accepted;
	}

	/**
	 * Sets the accepted.
	 *
	 * @param accepted the new accepted
	 */
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	/**
	 * Gets the invited date.
	 *
	 * @return the invited date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getInvitedDate() {
		return invitedDate;
	}

	/**
	 * Sets the invited date.
	 *
	 * @param invitedDate the new invited date
	 */
	public void setInvitedDate(Date invitedDate) {
		this.invitedDate = invitedDate;
	}

	/**
	 * Gets the invite accepted date.
	 *
	 * @return the invite accepted date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getInviteAcceptedDate() {
		return inviteAcceptedDate;
	}

	/**
	 * Sets the invite accepted date.
	 *
	 * @param inviteAcceptedDate the new invite accepted date
	 */
	public void setInviteAcceptedDate(Date inviteAcceptedDate) {
		this.inviteAcceptedDate = inviteAcceptedDate;
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
	// bi-directional many-to-one association to JobType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jobType")
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
	 * Gets the invited party.
	 *
	 * @return the invited party
	 */
	// bi-directional many-to-one association to Party
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invitedParty")
	public Party getInvitedParty() {
		return invitedParty;
	}

	/**
	 * Sets the invited party.
	 *
	 * @param invitedParty the new invited party
	 */
	public void setInvitedParty(Party invitedParty) {
		this.invitedParty = invitedParty;
	}

	/**
	 * Gets the invited by party.
	 *
	 * @return the invited by party
	 */
	// bi-directional many-to-one association to Party
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invitedByParty")
	public Party getInvitedByParty() {
		return invitedByParty;
	}

	/**
	 * Sets the invited by party.
	 *
	 * @param invitedByParty the new invited by party
	 */
	public void setInvitedByParty(Party invitedByParty) {
		this.invitedByParty = invitedByParty;
	}

	/**
	 * Gets the opportunity bean.
	 *
	 * @return the opportunity bean
	 */
	// bi-directional many-to-one association to Opportunity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opportunity")
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

}