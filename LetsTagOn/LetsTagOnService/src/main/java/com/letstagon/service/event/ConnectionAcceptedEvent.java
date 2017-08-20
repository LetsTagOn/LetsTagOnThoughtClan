package com.letstagon.service.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionAcceptedEvent.
 */
public class ConnectionAcceptedEvent extends ApplicationEvent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new connection accepted event.
	 *
	 * @param source the source
	 * @param fromParty the from party
	 * @param toParty the to party
	 * @param acceptedTime the accepted time
	 */
	public ConnectionAcceptedEvent(Object source, Party fromParty,
			Party toParty, Date acceptedTime) {
		super(source);
		this.fromParty = fromParty;
		this.toParty = toParty;
		this.acceptedTime = acceptedTime;
	}

	/** The from party. */
	private Party fromParty;
	
	/** The to party. */
	private Party toParty;
	
	/** The accepted time. */
	private Date acceptedTime;

	/**
	 * Instantiates a new connection accepted event.
	 *
	 * @param source the source
	 */
	public ConnectionAcceptedEvent(Object source) {
		super(source);

	}

	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString() {
		return "message printed event raised with: (" + fromParty + " ) and ("
				+ toParty + ")  and (" + acceptedTime + ")";
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
	 * Gets the accepted time.
	 *
	 * @return the accepted time
	 */
	public Date getAcceptedTime() {
		return acceptedTime;
	}

	/**
	 * Sets the accepted time.
	 *
	 * @param acceptedTime the new accepted time
	 */
	public void setAcceptedTime(Date acceptedTime) {
		this.acceptedTime = acceptedTime;
	}
}
