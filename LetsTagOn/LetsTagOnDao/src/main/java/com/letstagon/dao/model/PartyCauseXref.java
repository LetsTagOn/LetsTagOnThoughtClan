package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PartyCauseXref database table.
 * 
 */
@Entity
@Table(name = "PartyCauseXref")
@NamedQuery(name = "PartyCauseXref.findAll", query = "SELECT p FROM PartyCauseXref p")
public class PartyCauseXref implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The added on. */
	private Date addedOn;
	
	/** The cause bean. */
	private Cause causeBean;
	
	/** The party bean. */
	private Party partyBean;
	
	/** The status. */
	private boolean status;

	/**
	 * Instantiates a new party cause xref.
	 */
	public PartyCauseXref() {
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
	 * Checks if is status.
	 *
	 * @return true, if is status
	 */
	public boolean isStatus() {
		return status;
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
	 * Gets the cause bean.
	 *
	 * @return the cause bean
	 */
	// bi-directional many-to-one association to Cause
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cause")
	public Cause getCauseBean() {
		return this.causeBean;
	}

	/**
	 * Sets the cause bean.
	 *
	 * @param causeBean the new cause bean
	 */
	public void setCauseBean(Cause causeBean) {
		this.causeBean = causeBean;
	}

	/**
	 * Gets the party bean.
	 *
	 * @return the party bean
	 */
	// bi-directional many-to-one association to Party
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "party")
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