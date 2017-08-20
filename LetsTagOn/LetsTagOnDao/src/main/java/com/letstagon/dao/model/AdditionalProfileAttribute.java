package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the AdditionalProfileAttribute database table.
 * 
 */
@Entity
@Table(name="AdditionalProfileAttribute")
@NamedQuery(name="AdditionalProfileAttribute.findAll", query="SELECT a FROM AdditionalProfileAttribute a")
public class AdditionalProfileAttribute implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The description. */
	private String description;
	
	/** The name. */
	private String name;
	
	/** The user additional profile attributes. */
	private List<UserAdditionalProfileAttribute> userAdditionalProfileAttributes;
	
	/** The user type attribute xrefs. */
	private List<UserTypeAttributeXref> userTypeAttributeXrefs;

	/**
	 * Instantiates a new additional profile attribute.
	 */
	public AdditionalProfileAttribute() {
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
	 * Gets the user additional profile attributes.
	 *
	 * @return the user additional profile attributes
	 */
	//bi-directional many-to-one association to UserAdditionalProfileAttribute
	@OneToMany(mappedBy="additionalProfileAttribute")
	public List<UserAdditionalProfileAttribute> getUserAdditionalProfileAttributes() {
		return this.userAdditionalProfileAttributes;
	}

	/**
	 * Sets the user additional profile attributes.
	 *
	 * @param userAdditionalProfileAttributes the new user additional profile attributes
	 */
	public void setUserAdditionalProfileAttributes(List<UserAdditionalProfileAttribute> userAdditionalProfileAttributes) {
		this.userAdditionalProfileAttributes = userAdditionalProfileAttributes;
	}

	/**
	 * Adds the user additional profile attribute.
	 *
	 * @param userAdditionalProfileAttribute the user additional profile attribute
	 * @return the user additional profile attribute
	 */
	public UserAdditionalProfileAttribute addUserAdditionalProfileAttribute(UserAdditionalProfileAttribute userAdditionalProfileAttribute) {
		getUserAdditionalProfileAttributes().add(userAdditionalProfileAttribute);
		userAdditionalProfileAttribute.setAdditionalProfileAttribute(this);

		return userAdditionalProfileAttribute;
	}

	/**
	 * Removes the user additional profile attribute.
	 *
	 * @param userAdditionalProfileAttribute the user additional profile attribute
	 * @return the user additional profile attribute
	 */
	public UserAdditionalProfileAttribute removeUserAdditionalProfileAttribute(UserAdditionalProfileAttribute userAdditionalProfileAttribute) {
		getUserAdditionalProfileAttributes().remove(userAdditionalProfileAttribute);
		userAdditionalProfileAttribute.setAdditionalProfileAttribute(null);

		return userAdditionalProfileAttribute;
	}


	/**
	 * Gets the user type attribute xrefs.
	 *
	 * @return the user type attribute xrefs
	 */
	//bi-directional many-to-one association to UserTypeAttributeXref
	@OneToMany(mappedBy="additionalProfileAttribute")
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
		userTypeAttributeXref.setAdditionalProfileAttribute(this);

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
		userTypeAttributeXref.setAdditionalProfileAttribute(null);

		return userTypeAttributeXref;
	}

}