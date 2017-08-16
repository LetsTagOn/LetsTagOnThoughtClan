package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the PartyJobTypeXrefDTO core entity.
 * 
 */

public class PartyJobTypeXrefDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The added on. */
	private Date addedOn;
	
	/** The comments. */
	private String comments;
	
	/** The status. */
	private boolean status;
	
	/** The job type bean. */
	private JobTypeDTO jobTypeBean;
	
	/** The party bean. */
	private PartyDTO partyBean;

	/**
	 * Instantiates a new party job type xref DTO.
	 */
	public PartyJobTypeXrefDTO() {
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
	 * Gets the added on.
	 *
	 * @return the added on
	 */
	public Date getAddedOn() {
		return this.addedOn;
	}

	/**
	 * Sets the added on.
	 *
	 * @param addedOn the new added on
	 */
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}


	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(boolean status) {
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

}
