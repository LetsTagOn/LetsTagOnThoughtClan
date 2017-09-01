package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageDTO.
 */
public class MessageDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;

	/** The message text. */
	private String messageText;
	
	/** The sent time. */
	private Date sentTime;
	
	/** The read time. */
	private Date readTime;
	
	/** The sent party status. */
	private Boolean sentPartyStatus;
	
	/** The received party status. */
	private Boolean receivedPartyStatus;
	
	/** The is read. */
	private Boolean isRead;
	
	/** The attachment url. */
	private String attachmentUrl;

	/** The url. */
	private String url;
	
	/** The from party. */
	private PartyDTO fromParty;
	
	/** The to party. */
	private PartyDTO toParty;
	
	/**
	 * Instantiates a new message DTO.
	 */
	public MessageDTO() {
	}
	
	/**
	 * Instantiates a new message DTO.
	 *
	 * @param id the id
	 */
	public MessageDTO(long id) {
		super();
		this.id = id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
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
	 * Gets the message text.
	 *
	 * @return the message text
	 */
	public String getMessageText() {
		return this.messageText;
	}
	
	/**
	 * Sets the message text.
	 *
	 * @param messageText the new message text
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	/**
	 * Gets the read time.
	 *
	 * @return the read time
	 */
	public Date getReadTime() {
		return this.readTime;
	}
	
	/**
	 * Sets the read time.
	 *
	 * @param readTime the new read time
	 */
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
	/**
	 * Gets the sent time.
	 *
	 * @return the sent time
	 */
	public Date getSentTime() {
		return this.sentTime;
	}
	
	/**
	 * Sets the sent time.
	 *
	 * @param sentTime the new sent time
	 */
	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	
	/**
	 * Gets the sent party status.
	 *
	 * @return the sent party status
	 */
	public Boolean getSentPartyStatus() {
		return this.sentPartyStatus;
	}
	
	/**
	 * Sets the sent party status.
	 *
	 * @param sentPartyStatus the new sent party status
	 */
	public void setSentPartyStatus(Boolean sentPartyStatus) {
		this.sentPartyStatus = sentPartyStatus;
	}
	
	/**
	 * Gets the received party status.
	 *
	 * @return the received party status
	 */
	public Boolean getReceivedPartyStatus() {
		return this.receivedPartyStatus;
	}
	
	/**
	 * Sets the received party status.
	 *
	 * @param receivedPartyStatus the new received party status
	 */
	public void setReceivedPartyStatus(Boolean receivedPartyStatus) {
		this.receivedPartyStatus = receivedPartyStatus;
	}
	
	/**
	 * Gets the checks if is read.
	 *
	 * @return the checks if is read
	 */
	public Boolean getIsRead() {
		return this.isRead;
	}
	
	/**
	 * Sets the checks if is read.
	 *
	 * @param isRead the new checks if is read
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	
	/**
	 * Gets the attachment url.
	 *
	 * @return the attachment url
	 */
	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}
	
	/**
	 * Sets the attachment url.
	 *
	 * @param attachmentUrl the new attachment url
	 */
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Gets the from party.
	 *
	 * @return the from party
	 */
	public PartyDTO getFromParty() {
		return this.fromParty;
	}
	
	/**
	 * Sets the from party.
	 *
	 * @param fromParty the new from party
	 */
	public void setFromParty(PartyDTO fromParty) {
		this.fromParty = fromParty;
	}
	
	/**
	 * Gets the to party.
	 *
	 * @return the to party
	 */
	public PartyDTO getToParty() {
		return this.toParty;
	}
	
	/**
	 * Sets the to party.
	 *
	 * @param toParty the new to party
	 */
	public void setToParty(PartyDTO toParty) {
		this.toParty = toParty;
	}

}
