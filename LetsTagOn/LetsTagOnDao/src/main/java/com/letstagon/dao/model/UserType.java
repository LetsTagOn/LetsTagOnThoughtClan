package com.letstagon.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the UserType database table.
 * 
 */
@Entity(name="UserType")
@Table(name="UserType")
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The description. */
	private String description;
	
	/** The name. */
	private String name;
	
	/** The user type attribute xrefs. */
	private List<UserTypeAttributeXref> userTypeAttributeXrefs;

	/**
	 * Instantiates a new user type.
	 */
	public UserType() {
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets the user type attribute xrefs.
	 *
	 * @return the user type attribute xrefs
	 */
	//bi-directional many-to-one association to UserTypeAttributeXref
	@OneToMany(mappedBy="userTypeBean")
	public List<UserTypeAttributeXref> getUserTypeAttributeXrefs() {
		return this.userTypeAttributeXrefs;
	}

	/**
	 * Sets the user type attribute xrefs.
	 *
	 * @param userTypeAttributeXrefs the new user type attribute xrefs
	 */
	public void setUserTypeAttributeXrefs(List<UserTypeAttributeXref> userTypeAttributeXrefs) {
		this.userTypeAttributeXrefs = userTypeAttributeXrefs;
	}

	/**
	 * Adds the user type attribute xref.
	 *
	 * @param userTypeAttributeXref the user type attribute xref
	 * @return the user type attribute xref
	 */
	public UserTypeAttributeXref addUserTypeAttributeXref(UserTypeAttributeXref userTypeAttributeXref) {
		getUserTypeAttributeXrefs().add(userTypeAttributeXref);
		userTypeAttributeXref.setUserTypeBean(this);

		return userTypeAttributeXref;
	}

	/**
	 * Removes the user type attribute xref.
	 *
	 * @param userTypeAttributeXref the user type attribute xref
	 * @return the user type attribute xref
	 */
	public UserTypeAttributeXref removeUserTypeAttributeXref(UserTypeAttributeXref userTypeAttributeXref) {
		getUserTypeAttributeXrefs().remove(userTypeAttributeXref);
		userTypeAttributeXref.setUserTypeBean(null);

		return userTypeAttributeXref;
	}
	
	/**
	 * Gets the default bean.
	 *
	 * @return the default bean
	 */
	public static UserType getDefaultBean(){
		
		UserType userBeanDefault=new UserType();
		userBeanDefault.setId(1);
		return userBeanDefault;
	}

}