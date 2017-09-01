package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the UserAdditionalProfileAttribute database table.
 * 
 */
@Entity
@Table(name="UserAdditionalProfileAttribute")
@NamedQuery(name="UserAdditionalProfileAttribute.findAll", query="SELECT u FROM UserAdditionalProfileAttribute u")
public class UserAdditionalProfileAttribute implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The value. */
	private String value;
	
	/** The additional profile attribute. */
	private AdditionalProfileAttribute additionalProfileAttribute;
	
	/** The user bean. */
	private User userBean;

	/**
	 * Instantiates a new user additional profile attribute.
	 */
	public UserAdditionalProfileAttribute() {
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/**
	 * Gets the additional profile attribute.
	 *
	 * @return the additional profile attribute
	 */
	//bi-directional many-to-one association to AdditionalProfileAttribute
	@ManyToOne(fetch=FetchType.LAZY)
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
	 * Gets the user bean.
	 *
	 * @return the user bean
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	public User getUserBean() {
		return this.userBean;
	}

	/**
	 * Sets the user bean.
	 *
	 * @param userBean the new user bean
	 */
	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}