package com.letstagon.service.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityApplicationSentEvent.
 */
public class OpportunityApplicationSentEvent extends ApplicationEvent {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3667306370577097883L;
	
	/** The opportunity. */
	private Opportunity opportunity;
	
	/** The party. */
	private Party party;
	
	/** The application time. */
	private Date applicationTime;

	/**
	 * Instantiates a new opportunity application sent event.
	 *
	 * @param source the source
	 */
	public OpportunityApplicationSentEvent(Object source) {
		super(source);

	}

	/**
	 * Instantiates a new opportunity application sent event.
	 *
	 * @param source the source
	 * @param opportunity the opportunity
	 * @param party the party
	 * @param applicationTime the application time
	 */
	public OpportunityApplicationSentEvent(Object source,
			Opportunity opportunity, Party party, Date applicationTime) {
		this(source);
		this.opportunity = opportunity;
		this.party = party;
		this.applicationTime = applicationTime;

	}

	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString() {
		return "message printed event raised with: (" + opportunity
				+ " ) and (" + party + ")  and (" + applicationTime + ")";
	}

	/**
	 * Gets the opportunity.
	 *
	 * @return the opportunity
	 */
	public Opportunity getOpportunity() {
		return opportunity;
	}

	/**
	 * Sets the opportunity.
	 *
	 * @param opportunity the new opportunity
	 */
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * Sets the party.
	 *
	 * @param party the new party
	 */
	public void setParty(Party party) {
		this.party = party;
	}

	/**
	 * Gets the application time.
	 *
	 * @return the application time
	 */
	public Date getApplicationTime() {
		return applicationTime;
	}

	/**
	 * Sets the application time.
	 *
	 * @param applicationTime the new application time
	 */
	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}
}
