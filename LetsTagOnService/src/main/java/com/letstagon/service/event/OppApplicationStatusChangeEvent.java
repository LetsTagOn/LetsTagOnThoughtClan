package com.letstagon.service.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Class OppApplicationStatusChangeEvent.
 */
public class OppApplicationStatusChangeEvent extends ApplicationEvent {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4924502914102314994L;
	
	/** The opportunity. */
	private Opportunity opportunity;
	
	/** The party. */
	private Party party;
	
	/** The event time. */
	private Date eventTime;
	
	/**
	 * Instantiates a new opp application status change event.
	 *
	 * @param source the source
	 */
	public OppApplicationStatusChangeEvent(Object source) {
		super(source);
	
	}
	
	/**
	 * Instantiates a new opp application status change event.
	 *
	 * @param source the source
	 * @param opportunity the opportunity
	 * @param party the party
	 * @param eventTime the event time
	 */
	public OppApplicationStatusChangeEvent(Object source, Opportunity opportunity ,Party party, Date eventTime) {
		this(source);
		this.opportunity = opportunity;
		this.party = party;
		this.eventTime = eventTime;
	
	}
	
	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString(){
		return "message printed event raised with: ("+ opportunity +" ) and (" + party +")  and (" + eventTime +")";
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
	 * Gets the event time.
	 *
	 * @return the event time
	 */
	public Date getEventTime() {
		return eventTime;
	}
	
	/**
	 * Sets the event time.
	 *
	 * @param eventTime the new event time
	 */
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
}
