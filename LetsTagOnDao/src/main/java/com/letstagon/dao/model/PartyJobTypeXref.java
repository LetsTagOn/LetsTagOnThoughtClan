package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PartyJobTypeXref database table.
 * 
 */
@Entity
@Table(name="PartyJobTypeXref")
@NamedQuery(name="PartyJobTypeXref.findAll", query="SELECT p FROM PartyJobTypeXref p")
public class PartyJobTypeXref implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The added on. */
	private Date addedOn;
	
	/** The comments. */
	private String comments;
	
	/** The status. */
	private Boolean status;
	
	/** The job type bean. */
	private JobType jobTypeBean;
	
	/** The party bean. */
	private Party partyBean;

	/**
	 * Instantiates a new party job type xref.
	 */
	public PartyJobTypeXref() {
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
	 * Gets the added on.
	 *
	 * @return the added on
	 */
	@Temporal(TemporalType.TIMESTAMP)
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
	public Boolean getStatus() {
		return status;
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

}