package com.letstagon.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EntityComment")
public class EntityComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Entityy entity;
	private User user;
	private String comment;
	private Date commentDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@MapsId
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="entityId", referencedColumnName="id")
	public Entityy getEntity() {
		return entity;
	}
	public void setEntity(Entityy entity) {
		this.entity = entity;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}
