package com.letstagon.facade.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the OpportunityCauseXrefDTO core entity.
 * 
 */

public class OpportunityCauseXrefDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The opportunity bean. */
	private OpportunityDTO opportunityBean;
	
	/** The cause bean. */
	private CauseDTO causeBean;

	/**
	 * Instantiates a new opportunity cause xref DTO.
	 */
	public OpportunityCauseXrefDTO() {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OpportunityCauseXrefDTO [id=" + id + ", opportunityBean="
				+ (opportunityBean == null ? "" : opportunityBean.getId()) + ", causeBean=" + causeBean + "]";
	}

}
