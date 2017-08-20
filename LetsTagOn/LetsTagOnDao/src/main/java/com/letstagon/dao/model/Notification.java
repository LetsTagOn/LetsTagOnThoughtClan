package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Notification database table.
 * 
 */
@Entity
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The content. */
	private String content;
	
	/** The params. */
	private String params;
	
	/** The sent on. */
	private Date sentOn;
	
	/** The status. */
	private Boolean status;
	
	/** The is read. */
	private Boolean isRead;
	
	/** The thumbnail url. */
	private String thumbnailUrl;
	
	/** The type. */
	private String type;
	
	/** The url. */
	private String url;
	
	/** The party bean. */
	private Party partyBean;

	/**
	 * Instantiates a new notification.
	 */
	public Notification() {
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
	 * Gets the content.
	 *
	 * @return the content
	 */
	@Lob
	public String getContent() {
		return this.content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public String getParams() {
		return this.params;
	}

	/**
	 * Sets the params.
	 *
	 * @param params the new params
	 */
	public void setParams(String params) {
		this.params = params;
	}


	/**
	 * Gets the sent on.
	 *
	 * @return the sent on
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSentOn() {
		return this.sentOn;
	}

	/**
	 * Sets the sent on.
	 *
	 * @param sentOn the new sent on
	 */
	public void setSentOn(Date sentOn) {
		this.sentOn = sentOn;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Boolean getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	


	


	/**
	 * Gets the thumbnail url.
	 *
	 * @return the thumbnail url
	 */
	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	/**
	 * Sets the thumbnail url.
	 *
	 * @param thumbnailUrl the new thumbnail url
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * Gets the party bean.
	 *
	 * @return the party bean
	 */
	//bi-directional many-to-one association to Party
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="party")
	public Party getPartyBean() {
		return this.partyBean;
	}

	/**
	 * Sets the party bean.
	 *
	 * @param partyBean the new party bean
	 */
	public void setPartyBean(Party partyBean) {
		this.partyBean = partyBean;
	}


	/**
	 * Gets the checks if is read.
	 *
	 * @return the checks if is read
	 */
	public Boolean getIsRead() {
		return isRead;
	}


	/**
	 * Sets the checks if is read.
	 *
	 * @param isRead the new checks if is read
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}