package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the PartyCauseXrefDTO core entity.
 * 
 */

public class PartyCauseXrefDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The added on. */
	private Date addedOn;
	
	/** The cause bean. */
	private CauseDTO causeBean;
	
	/** The party bean. */
	private PartyDTO partyBean;
	
	/** The status. */
	private boolean status;

	/**
	 * Instantiates a new party cause xref DTO.
	 */
	public PartyCauseXrefDTO() {
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
	 * Gets the cause bean.
	 *
	 * @return the cause bean
	 */
	public CauseDTO getCauseBean() {
		return this.causeBean;
	}

	/**
	 * Sets the cause bean.
	 *
	 * @param causeBean the new cause bean
	 */
	public void setCauseBean(CauseDTO causeBean) {
		this.causeBean = causeBean;
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

}
