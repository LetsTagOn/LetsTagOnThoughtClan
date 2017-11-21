package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

public class UserPostDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityyDTO entity;
	private UserDTO User;
	private String content;
	private String url;
	private Date postedOn;
	
	public EntityyDTO getEntity() {
		return entity;
	}
	public void setEntity(EntityyDTO entity) {
		this.entity = entity;
	}
	
	public UserDTO getUser() {
		return User;
	}
	public void setUser(UserDTO user) {
		User = user;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	
}
