package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the OpportunityCauseXref database table.
 * 
 */
@Entity
@Table(name="OpportunityCauseXref")
@NamedQuery(name="OpportunityCauseXref.findAll", query="SELECT o FROM OpportunityCauseXref o")
public class OpportunityCauseXref implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The opportunity bean. */
	private Opportunity opportunityBean;
	
	/** The cause bean. */
	private Cause causeBean;

	/**
	 * Instantiates a new opportunity cause xref.
	 */
	public OpportunityCauseXref() {
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
	 * Gets the cause bean.
	 *
	 * @return the cause bean
	 */
	//bi-directional many-to-one association to Cause
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cause")
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

}