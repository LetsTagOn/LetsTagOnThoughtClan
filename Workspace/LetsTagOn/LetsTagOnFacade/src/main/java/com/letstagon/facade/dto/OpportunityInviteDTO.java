package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the PartyDTOParticipation database table.
 * 
 */
public class OpportunityInviteDTO implements Serializable {
	
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
	private JobTypeDTO jobTypeBean;
	
	/** The invited party. */
	private PartyDTO invitedParty;
	
	/** The invited by party. */
	private PartyDTO invitedByParty;
	
	/** The opportunity bean. */
	private OpportunityDTO opportunityBean;

	/**
	 * Instantiates a new opportunity invite DTO.
	 */
	public OpportunityInviteDTO() {
	}

	/**
	 * Instantiates a new opportunity invite DTO.
	 *
	 * @param invitedPartyDTO the invited party DTO
	 * @param invitedByPartyDTO the invited by party DTO
	 * @param opportunityBean the opportunity bean
	 */
	public OpportunityInviteDTO(PartyDTO invitedPartyDTO, PartyDTO invitedByPartyDTO, OpportunityDTO opportunityBean) {
		super();
		this.invitedParty = invitedPartyDTO;
		this.invitedByParty = invitedByPartyDTO;
		this.opportunityBean = opportunityBean;
		this.invitedDate = new Date();
	}

	/**
	 * Instantiates a new opportunity invite DTO.
	 *
	 * @param id the id
	 */
	public OpportunityInviteDTO(long id) {
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
	public JobTypeDTO getJobTypeBean() {
		return jobTypeBean;
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
	 * Gets the opportunity bean.
	 *
	 * @return the opportunity bean
	 */
	public OpportunityDTO getOpportunityBean() {
		return opportunityBean;
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
	 * Gets the invited party.
	 *
	 * @return the invited party
	 */
	public PartyDTO getInvitedParty() {
		return invitedParty;
	}

	/**
	 * Sets the invited party.
	 *
	 * @param invitedParty the new invited party
	 */
	public void setInvitedParty(PartyDTO invitedParty) {
		this.invitedParty = invitedParty;
	}

	/**
	 * Gets the invited by party.
	 *
	 * @return the invited by party
	 */
	public PartyDTO getInvitedByParty() {
		return invitedByParty;
	}

	/**
	 * Sets the invited by party.
	 *
	 * @param invitedByParty the new invited by party
	 */
	public void setInvitedByParty(PartyDTO invitedByParty) {
		this.invitedByParty = invitedByParty;
	}

}