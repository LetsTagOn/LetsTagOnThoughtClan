package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The city. */
	private String city;
	
	/** The country. */
	private String country;
	
	/** The postal code. */
	private String postalCode;
	
	/** The state. */
	private String state;
	
	/** The street. */
	private String street;
	
	/** The opportunities. */
	private List<Opportunity> opportunities;
	
	/** The organizations. */
	private List<Organization> organizations;
	
	/** The users. */
	private List<User> users;
	
	/** The formatted address. */
	private String formattedAddress;

	/**
	 * Instantiates a new address.
	 */
	public Address() {
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
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the new postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * Gets the opportunities.
	 *
	 * @return the opportunities
	 */
	//bi-directional many-to-one association to Opportunity
	@OneToMany(mappedBy="addressBean")
	public List<Opportunity> getOpportunities() {
		return this.opportunities;
	}

	/**
	 * Sets the opportunities.
	 *
	 * @param opportunities the new opportunities
	 */
	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}

	/**
	 * Adds the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity
	 */
	public Opportunity addOpportunity(Opportunity opportunity) {
		getOpportunities().add(opportunity);
		opportunity.setAddressBean(this);

		return opportunity;
	}

	/**
	 * Removes the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity
	 */
	public Opportunity removeOpportunity(Opportunity opportunity) {
		getOpportunities().remove(opportunity);
		opportunity.setAddressBean(null);

		return opportunity;
	}


	/**
	 * Gets the organizations.
	 *
	 * @return the organizations
	 */
	//bi-directional many-to-one association to Organization
	@OneToMany(mappedBy="addressBean")
	public List<Organization> getOrganizations() {
		return this.organizations;
	}

	/**
	 * Sets the organizations.
	 *
	 * @param organizations the new organizations
	 */
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	/**
	 * Adds the organization.
	 *
	 * @param organization the organization
	 * @return the organization
	 */
	public Organization addOrganization(Organization organization) {
		getOrganizations().add(organization);
		organization.setAddressBean(this);

		return organization;
	}

	/**
	 * Removes the organization.
	 *
	 * @param organization the organization
	 * @return the organization
	 */
	public Organization removeOrganization(Organization organization) {
		getOrganizations().remove(organization);
		organization.setAddressBean(null);

		return organization;
	}


	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="addressBean")
	public List<User> getUsers() {
		return this.users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User addUser(User user) {
		getUsers().add(user);
		user.setAddressBean(this);

		return user;
	}

	/**
	 * Removes the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User removeUser(User user) {
		getUsers().remove(user);
		user.setAddressBean(null);

		return user;
	}


	/**
	 * Gets the formatted address.
	 *
	 * @return the formatted address
	 */
	public String getFormattedAddress() {
		return formattedAddress;
	}


	/**
	 * Sets the formatted address.
	 *
	 * @param formattedAddress the new formatted address
	 */
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

}