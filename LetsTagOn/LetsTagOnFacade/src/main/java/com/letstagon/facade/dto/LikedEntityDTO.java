package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;

public class LikedEntityDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityyDTO entity;
	private UserDTO user;
	private Date likedDate;
	
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
	
	public Date getLikedDate() {
		return likedDate;
	}
	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}
}
