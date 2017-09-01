package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the UserTypeAttributeXref database table.
 * 
 */
@Entity
@Table(name="UserTypeAttributeXref")
@NamedQuery(name="UserTypeAttributeXref.findAll", query="SELECT u FROM UserTypeAttributeXref u")
public class UserTypeAttributeXref implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The compulsory. */
	private Boolean compulsory;
	
	/** The additional profile attribute. */
	private AdditionalProfileAttribute additionalProfileAttribute;
	
	/** The user type bean. */
	private UserType userTypeBean;

	/**
	 * Instantiates a new user type attribute xref.
	 */
	public UserTypeAttributeXref() {
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
	 * Gets the compulsory.
	 *
	 * @return the compulsory
	 */
	public Boolean getCompulsory() {
		return this.compulsory;
	}

	/**
	 * Sets the compulsory.
	 *
	 * @param compulsory the new compulsory
	 */
	public void setCompulsory(Boolean compulsory) {
		this.compulsory = compulsory;
	}


	/**
	 * Gets the additional profile attribute.
	 *
	 * @return the additional profile attribute
	 */
	//bi-directional many-to-one association to AdditionalProfileAttribute
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="attributeType")
	public AdditionalProfileAttribute getAdditionalProfileAttribute() {
		return this.additionalProfileAttribute;
	}

	/**
	 * Sets the additional profile attribute.
	 *
	 * @param additionalProfileAttribute the new additional profile attribute
	 */
	public void setAdditionalProfileAttribute(AdditionalProfileAttribute additionalProfileAttribute) {
		this.additionalProfileAttribute = additionalProfileAttribute;
	}


	/**
	 * Gets the user type bean.
	 *
	 * @return the user type bean
	 */
	//bi-directional many-to-one association to UserType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userType")
	public UserType getUserTypeBean() {
		return this.userTypeBean;
	}

	/**
	 * Sets the user type bean.
	 *
	 * @param userTypeBean the new user type bean
	 */
	public void setUserTypeBean(UserType userTypeBean) {
		this.userTypeBean = userTypeBean;
	}

}