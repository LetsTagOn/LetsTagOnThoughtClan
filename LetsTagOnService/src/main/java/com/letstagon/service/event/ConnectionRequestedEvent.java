package com.letstagon.service.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionRequestedEvent.
 */
public class ConnectionRequestedEvent extends ApplicationEvent {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8684516729206111775L;
	
	/** The from party. */
	private Party fromParty;
	
	/** The to party. */
	private Party toParty;
	
	/** The invited time. */
	private Date invitedTime;

	/**
	 * Instantiates a new connection requested event.
	 *
	 * @param source the source
	 */
	public ConnectionRequestedEvent(Object source) {
		super(source);

	}

	/**
	 * Instantiates a new connection requested event.
	 *
	 * @param source the source
	 * @param fromParty the from party
	 * @param toParty the to party
	 * @param invitedTime the invited time
	 */
	public ConnectionRequestedEvent(Object source, Party fromParty,
			Party toParty, Date invitedTime) {
		this(source);
		this.fromParty = fromParty;
		this.toParty = toParty;
		this.invitedTime = invitedTime;

	}

	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString() {
		return "message printed event raised with: (" + fromParty + " ) and ("
				+ toParty + ")  and (" + invitedTime + ")";
	}

	/**
	 * Gets the from party.
	 *
	 * @return the from party
	 */
	public Party getFromParty() {
		return fromParty;
	}

	/**
	 * Sets the from party.
	 *
	 * @param fromParty the new from party
	 */
	public void setFromParty(Party fromParty) {
		this.fromParty = fromParty;
	}

	/**
	 * Gets the to party.
	 *
	 * @return the to party
	 */
	public Party getToParty() {
		return toParty;
	}

	/**
	 * Sets the to party.
	 *
	 * @param toParty the new to party
	 */
	public void setToParty(Party toParty) {
		this.toParty = toParty;
	}

	/**
	 * Gets the invited time.
	 *
	 * @return the invited time
	 */
	public Date getInvitedTime() {
		return invitedTime;
	}

	/**
	 * Sets the invited time.
	 *
	 * @param invitedTime the new invited time
	 */
	public void setInvitedTime(Date invitedTime) {
		this.invitedTime = invitedTime;
	}

}
