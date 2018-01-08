package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the OpportunityJobType database table.
 * 
 */
@Entity
@Table(name="OpportunityJobType")
@NamedQuery(name="OpportunityJobType.findAll", query="SELECT o FROM OpportunityJobType o")
public class OpportunityJobType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The number of positions. */
	private int numberOfPositions;
	
	private int hours;

	/** The selection criteria. */
	private String selectionCriteria;
	
	/** The status. */
	private Boolean status;
	
	/** The job type bean. */
	private JobType jobTypeBean;
	
	/** The opportunity bean. */
	private Opportunity opportunityBean;

	/**
	 * Instantiates a new opportunity job type.
	 */
	public OpportunityJobType() {
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
	 * Gets the number of hours.
	 *
	 * @return the number of hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Sets the number of hours.
	 *
	 * @param hours the new number of hours
	 */
	public void setHours(int hours) {
		this.hours = hours;
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

}