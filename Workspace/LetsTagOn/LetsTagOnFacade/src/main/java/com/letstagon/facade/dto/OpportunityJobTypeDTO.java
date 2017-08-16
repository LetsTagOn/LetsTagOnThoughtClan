package com.letstagon.facade.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the OpportunityJobTypeDTO core entity.
 * 
 */

public class OpportunityJobTypeDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The number of positions. */
	private int numberOfPositions;
	
	/** The selection criteria. */
	private String selectionCriteria;
	
	/** The status. */
	private Boolean status;
	
	/** The job type bean. */
	private JobTypeDTO jobTypeBean;
	
	/** The opportunity bean. */
	private OpportunityDTO opportunityBean;

	/**
	 * Instantiates a new opportunity job type DTO.
	 */
	public OpportunityJobTypeDTO() {
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
	 * Gets the number of positions.
	 *
	 * @return the number of positions
	 */
	public int getNumberOfPositions() {
		return this.numberOfPositions;
	}

	/**
	 * Sets the number of positions.
	 *
	 * @param numberOfPositions the new number of positions
	 */
	public void setNumberOfPositions(int numberOfPositions) {
		this.numberOfPositions = numberOfPositions;
	}

	/**
	 * Gets the selection criteria.
	 *
	 * @return the selection criteria
	 */
	public String getSelectionCriteria() {
		return this.selectionCriteria;
	}

	/**
	 * Sets the selection criteria.
	 *
	 * @param selectionCriteria the new selection criteria
	 */
	public void setSelectionCriteria(String selectionCriteria) {
		this.selectionCriteria = selectionCriteria;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OpportunityJobTypeDTO [id=" + id + ", numberOfPositions=" + numberOfPositions + ", selectionCriteria="
				+ selectionCriteria + ", status=" + status + ", jobTypeBean=" + jobTypeBean + ", opportunityBean="
				+ opportunityBean + "]";
	}

}
