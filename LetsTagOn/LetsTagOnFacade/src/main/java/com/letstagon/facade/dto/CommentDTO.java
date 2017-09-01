package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the CommentDTO core entity.
 * 
 */

public class CommentDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The comment. */
	private String comment;
	
	/** The commented on. */
	private Date commentedOn;
	
	/** The status. */
	private String status;
	
	/** The type. */
	private String type;
	
	/** The party bean. */
	private PartyDTO partyBean;
	
	/** The post bean. */
	private PostDTO postBean;

	/**
	 * Instantiates a new comment DTO.
	 */
	public CommentDTO() {
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
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * Gets the commented on.
	 *
	 * @return the commented on
	 */
	public Date getCommentedOn() {
		return this.commentedOn;
	}

	/**
	 * Sets the commented on.
	 *
	 * @param commentedOn the new commented on
	 */
	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * Gets the party bean.
	 *
	 * @return the party bean
	 */
	public PartyDTO getPartyBean() {
		return this.partyBean;
	}

	/**
	 * Sets the party bean.
	 *
	 * @param partyBean the new party bean
	 */
	public void setPartyBean(PartyDTO partyBean) {
		this.partyBean = partyBean;
	}


	/**
	 * Gets the post bean.
	 *
	 * @return the post bean
	 */
	public PostDTO getPostBean() {
		return this.postBean;
	}

	/**
	 * Sets the post bean.
	 *
	 * @param postBean the new post bean
	 */
	public void setPostBean(PostDTO postBean) {
		this.postBean = postBean;
	}

}
