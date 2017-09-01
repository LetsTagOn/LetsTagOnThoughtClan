package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the AddressDTO core entity.
 * 
 */

public class AddressDTO implements Serializable {
	
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
	private List<OpportunityDTO> opportunities;
	
	/** The organizations. */
	private List<OrganizationDTO> organizations;
	
	/** The users. */
	private List<UserDTO> users;
	
	/** The formatted address. */
	private String formattedAddress;

	/**
	 * Instantiates a new address DTO.
	 */
	public AddressDTO() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
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
	public List<OpportunityDTO> getOpportunities() {
		return this.opportunities;
	}

	/**
	 * Sets the opportunities.
	 *
	 * @param opportunities the new opportunities
	 */
	public void setOpportunities(List<OpportunityDTO> opportunities) {
		this.opportunities = opportunities;
	}

	/**
	 * Adds the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity DTO
	 */
	public OpportunityDTO addOpportunity(OpportunityDTO opportunity) {
		getOpportunities().add(opportunity);
		opportunity.setAddressBean(this);

		return opportunity;
	}

	/**
	 * Removes the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity DTO
	 */
	public OpportunityDTO removeOpportunity(OpportunityDTO opportunity) {
		getOpportunities().remove(opportunity);
		opportunity.setAddressBean(null);

		return opportunity;
	}

	/**
	 * Gets the organizations.
	 *
	 * @return the organizations
	 */
	public List<OrganizationDTO> getOrganizations() {
		return this.organizations;
	}

	/**
	 * Sets the organizations.
	 *
	 * @param organizations the new organizations
	 */
	public void setOrganizations(List<OrganizationDTO> organizations) {
		this.organizations = organizations;
	}

	/**
	 * Adds the organization.
	 *
	 * @param organization the organization
	 * @return the organization DTO
	 */
	public OrganizationDTO addOrganization(OrganizationDTO organization) {
		getOrganizations().add(organization);
		organization.setAddressBean(this);

		return organization;
	}

	/**
	 * Removes the organization.
	 *
	 * @param organization the organization
	 * @return the organization DTO
	 */
	public OrganizationDTO removeOrganization(OrganizationDTO organization) {
		getOrganizations().remove(organization);
		organization.setAddressBean(null);

		return organization;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<UserDTO> getUsers() {
		return this.users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user DTO
	 */
	public UserDTO addUser(UserDTO user) {
		getUsers().add(user);
		user.setAddressBean(this);

		return user;
	}

	/**
	 * Removes the user.
	 *
	 * @param user the user
	 * @return the user DTO
	 */
	public UserDTO removeUser(UserDTO user) {
		getUsers().remove(user);
		user.setAddressBean(null);

		return user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddressDTO [id=" + id + ", city=" + city + ", country=" + country + ", postalCode=" + postalCode
				+ ", state=" + state + ", street=" + street + "]";
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
