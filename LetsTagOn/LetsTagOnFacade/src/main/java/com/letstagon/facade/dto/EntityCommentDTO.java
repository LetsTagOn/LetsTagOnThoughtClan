package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

public class EntityCommentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityyDTO entity;
	private UserDTO user;
	private String comment;
	private Date commentDate;
	
	public EntityyDTO getEntity() {
		return entity;
	}
	public void setEntity(EntityyDTO entity) {
		this.entity = entity;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}
