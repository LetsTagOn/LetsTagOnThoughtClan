package com.letstagon.service.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Class InviteToOppEvent.
 */
public class InviteToOppEvent extends ApplicationEvent {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6156820740740470183L;
	
	/** The opportunity. */
	private Opportunity opportunity;
	
	/** The inviting party. */
	private Party invitingParty;
	
	/** The invited party. */
	private Party invitedParty;
	
	/** The application time. */
	private Date applicationTime;

	/**
	 * Instantiates a new invite to opp event.
	 *
	 * @param source the source
	 */
	public InviteToOppEvent(Object source) {
		super(source);

	}

	/**
	 * Instantiates a new invite to opp event.
	 *
	 * @param source the source
	 * @param opportunity the opportunity
	 * @param invitingParty the inviting party
	 * @param invitedParty the invited party
	 * @param applicationTime the application time
	 */
	public InviteToOppEvent(Object source, Opportunity opportunity,
			Party invitingParty, Party invitedParty, Date applicationTime) {
		this(source);
		this.opportunity = opportunity;
		this.invitingParty = invitingParty;
		this.invitedParty = invitedParty;
		this.applicationTime = applicationTime;

	}

	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString() {
		return "message printed event raised with: (" + opportunity
				+ " ) and (" + invitingParty + ") and (" + invitedParty
				+ ")  and (" + applicationTime + ")";
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
	 * Gets the inviting party.
	 *
	 * @return the inviting party
	 */
	public Party getInvitingParty() {
		return invitingParty;
	}

	/**
	 * Sets the inviting party.
	 *
	 * @param invitingParty the new inviting party
	 */
	public void setInvitingParty(Party invitingParty) {
		this.invitingParty = invitingParty;
	}

	/**
	 * Gets the invited party.
	 *
	 * @return the invited party
	 */
	public Party getInvitedParty() {
		return invitedParty;
	}

	/**
	 * Sets the invited party.
	 *
	 * @param invitedParty the new invited party
	 */
	public void setInvitedParty(Party invitedParty) {
		this.invitedParty = invitedParty;
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
