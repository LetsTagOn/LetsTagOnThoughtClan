package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the ConnectionDTO core entity.
 * 
 */

public class ConnectionDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The connected on. */
	private Date connectedOn;
	
	/** The connection type. */
	private String connectionType;
	
	/** The initiated on. */
	private Date initiatedOn;
	
	/** The connected. */
	private Boolean connected;
	
	/** The party 1. */
	private PartyDTO party1;
	
	/** The party 2. */
	private PartyDTO party2;

	/**
	 * Instantiates a new connection DTO.
	 */
	public ConnectionDTO() {
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
	 * Gets the connected on.
	 *
	 * @return the connected on
	 */
	public Date getConnectedOn() {
		return this.connectedOn;
	}

	/**
	 * Sets the connected on.
	 *
	 * @param connectedOn the new connected on
	 */
	public void setConnectedOn(Date connectedOn) {
		this.connectedOn = connectedOn;
	}


	/**
	 * Gets the connection type.
	 *
	 * @return the connection type
	 */
	public String getConnectionType() {
		return this.connectionType;
	}

	/**
	 * Sets the connection type.
	 *
	 * @param connectionType the new connection type
	 */
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}


	/**
	 * Gets the initiated on.
	 *
	 * @return the initiated on
	 */
	public Date getInitiatedOn() {
		return this.initiatedOn;
	}

	/**
	 * Sets the initiated on.
	 *
	 * @param initiatedOn the new initiated on
	 */
	public void setInitiatedOn(Date initiatedOn) {
		this.initiatedOn = initiatedOn;
	}


	/**
	 * Gets the party 1.
	 *
	 * @return the party 1
	 */
	public PartyDTO getParty1() {
		return this.party1;
	}

	/**
	 * Sets the party 1.
	 *
	 * @param party1 the new party 1
	 */
	public void setParty1(PartyDTO party1) {
		this.party1 = party1;
	}


	/**
	 * Gets the party 2.
	 *
	 * @return the party 2
	 */
	public PartyDTO getParty2() {
		return this.party2;
	}

	/**
	 * Sets the party 2.
	 *
	 * @param party2 the new party 2
	 */
	public void setParty2(PartyDTO party2) {
		this.party2 = party2;
	}




	/**
	 * Gets the connected.
	 *
	 * @return the connected
	 */
	public Boolean getConnected() {
		return connected;
	}




	/**
	 * Sets the connected.
	 *
	 * @param connected the new connected
	 */
	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

}
