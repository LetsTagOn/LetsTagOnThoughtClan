package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EntityyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private UserDTO user;
	private UserPostDTO userPost;
	private Set<LikedEntityDTO> likes = new HashSet<>();
	private Set<EntityCommentDTO> comments = new HashSet<>();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public UserPostDTO getUserPost() {
		return userPost;
	}
	public void setUserPost(UserPostDTO userPost) {
		this.userPost = userPost;
	}
	
	public Set<LikedEntityDTO> getLikes() {
		return likes;
	}
	public void setLikes(Set<LikedEntityDTO> likes) {
		this.likes = likes;
	}
	
	public Set<EntityCommentDTO> getComments() {
		return comments;
	}
	public void setComments(Set<EntityCommentDTO> comments) {
		this.comments = comments;
	}
}
