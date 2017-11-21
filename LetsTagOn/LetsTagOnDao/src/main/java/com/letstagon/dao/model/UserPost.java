package com.letstagon.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UserPost")
public class UserPost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Entityy entity;
	private String content;
	private String url;
	private Date postedOn;
	
	@PrePersist
	public void prePersist(){
		setPostedOn(new Date());
	}
	
	@Id
	@Column(name="entityId")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@MapsId
	@OneToOne(mappedBy="userPost")
	@JoinColumn(name="entityId", referencedColumnName="id")
	public Entityy getEntity() {
		return entity;
	}
	public void setEntity(Entityy entity) {
		this.entity = entity;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	
}
