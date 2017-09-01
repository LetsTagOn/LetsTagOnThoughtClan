package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Organization database table.
 * 
 */
@Entity
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
public class Organization implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The banner picture. */
	private String bannerPicture;
	
	/** The created on. */
	private Date createdOn;
	
	/** The email address. */
	private String emailAddress;
	
	/** The name. */
	private String name;
	
	/** The org type. */
	private String orgType;
	
	/** The page theme. */
	private String pageTheme;
	
	/** The rating. */
	private String rating;
	
	/** The website. */
	private String website;
	
	/** The address bean. */
	private Address addressBean;
	
	/** The user. */
	private User user;
	
	/** The parties. */
	private List<Party> parties;
	
	/** The party feedbacks. */
	private List<PartyFeedback> partyFeedbacks;
	
	/** The party participations. */
	private List<PartyParticipation> partyParticipations;
	
	/** The user experiences. */
	private List<UserExperience> userExperiences;

	/**
	 * Instantiates a new organization.
	 */
	public Organization() {
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
	 * Gets the banner picture.
	 *
	 * @return the banner picture
	 */
	public String getBannerPicture() {
		return this.bannerPicture;
	}

	/**
	 * Sets the banner picture.
	 *
	 * @param bannerPicture the new banner picture
	 */
	public void setBannerPicture(String bannerPicture) {
		this.bannerPicture = bannerPicture;
	}


	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	@Temporal(TemporalType.DATE)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
	 * Gets the org type.
	 *
	 * @return the org type
	 */
	public String getOrgType() {
		return this.orgType;
	}

	/**
	 * Sets the org type.
	 *
	 * @param orgType the new org type
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}


	/**
	 * Gets the page theme.
	 *
	 * @return the page theme
	 */
	public String getPageTheme() {
		return this.pageTheme;
	}

	/**
	 * Sets the page theme.
	 *
	 * @param pageTheme the new page theme
	 */
	public void setPageTheme(String pageTheme) {
		this.pageTheme = pageTheme;
	}


	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public String getRating() {
		return this.rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}


	/**
	 * Gets the website.
	 *
	 * @return the website
	 */
	public String getWebsite() {
		return this.website;
	}

	/**
	 * Sets the website.
	 *
	 * @param website the new website
	 */
	public void setWebsite(String website) {
		this.website = website;
	}


	/**
	 * Gets the address bean.
	 *
	 * @return the address bean
	 */
	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address")
	public Address getAddressBean() {
		return this.addressBean;
	}

	/**
	 * Sets the address bean.
	 *
	 * @param addressBean the new address bean
	 */
	public void setAddressBean(Address addressBean) {
		this.addressBean = addressBean;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="managedBy")
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * Gets the parties.
	 *
	 * @return the parties
	 */
	//bi-directional many-to-one association to Party
	@OneToMany(mappedBy="organizationBean")
	public List<Party> getParties() {
		return this.parties;
	}

	/**
	 * Sets the parties.
	 *
	 * @param parties the new parties
	 */
	public void setParties(List<Party> parties) {
		this.parties = parties;
	}

	/**
	 * Adds the party.
	 *
	 * @param party the party
	 * @return the party
	 */
	public Party addParty(Party party) {
		getParties().add(party);
		party.setOrganizationBean(this);

		return party;
	}

	/**
	 * Removes the party.
	 *
	 * @param party the party
	 * @return the party
	 */
	public Party removeParty(Party party) {
		getParties().remove(party);
		party.setOrganizationBean(null);

		return party;
	}


	/**
	 * Gets the party feedbacks.
	 *
	 * @return the party feedbacks
	 */
	//bi-directional many-to-one association to PartyFeedback
	@OneToMany(mappedBy="organization")
	public List<PartyFeedback> getPartyFeedbacks() {
		return this.partyFeedbacks;
	}

	/**
	 * Sets the party feedbacks.
	 *
	 * @param partyFeedbacks the new party feedbacks
	 */
	public void setPartyFeedbacks(List<PartyFeedback> partyFeedbacks) {
		this.partyFeedbacks = partyFeedbacks;
	}

	/**
	 * Adds the party feedback.
	 *
	 * @param partyFeedback the party feedback
	 * @return the party feedback
	 */
	public PartyFeedback addPartyFeedback(PartyFeedback partyFeedback) {
		getPartyFeedbacks().add(partyFeedback);
		partyFeedback.setOrganization(this);

		return partyFeedback;
	}

	/**
	 * Removes the party feedback.
	 *
	 * @param partyFeedback the party feedback
	 * @return the party feedback
	 */
	public PartyFeedback removePartyFeedback(PartyFeedback partyFeedback) {
		getPartyFeedbacks().remove(partyFeedback);
		partyFeedback.setOrganization(null);

		return partyFeedback;
	}


	/**
	 * Gets the party participations.
	 *
	 * @return the party participations
	 */
	//bi-directional many-to-one association to PartyParticipation
	@OneToMany(mappedBy="organization")
	public List<PartyParticipation> getPartyParticipations() {
		return this.partyParticipations;
	}

	/**
	 * Sets the party participations.
	 *
	 * @param partyParticipations the new party participations
	 */
	public void setPartyParticipations(List<PartyParticipation> partyParticipations) {
		this.partyParticipations = partyParticipations;
	}

	/**
	 * Adds the party participation.
	 *
	 * @param partyParticipation the party participation
	 * @return the party participation
	 */
	public PartyParticipation addPartyParticipation(PartyParticipation partyParticipation) {
		getPartyParticipations().add(partyParticipation);
		partyParticipation.setOrganization(this);

		return partyParticipation;
	}

	/**
	 * Removes the party participation.
	 *
	 * @param partyParticipation the party participation
	 * @return the party participation
	 */
	public PartyParticipation removePartyParticipation(PartyParticipation partyParticipation) {
		getPartyParticipations().remove(partyParticipation);
		partyParticipation.setOrganization(null);

		return partyParticipation;
	}


	/**
	 * Gets the user experiences.
	 *
	 * @return the user experiences
	 */
	//bi-directional many-to-one association to UserExperience
	@OneToMany(mappedBy="organizationBean")
	public List<UserExperience> getUserExperiences() {
		return this.userExperiences;
	}

	/**
	 * Sets the user experiences.
	 *
	 * @param userExperiences the new user experiences
	 */
	public void setUserExperiences(List<UserExperience> userExperiences) {
		this.userExperiences = userExperiences;
	}

	/**
	 * Adds the user experience.
	 *
	 * @param userExperience the user experience
	 * @return the user experience
	 */
	public UserExperience addUserExperience(UserExperience userExperience) {
		getUserExperiences().add(userExperience);
		userExperience.setOrganizationBean(this);

		return userExperience;
	}

	/**
	 * Removes the user experience.
	 *
	 * @param userExperience the user experience
	 * @return the user experience
	 */
	public UserExperience removeUserExperience(UserExperience userExperience) {
		getUserExperiences().remove(userExperience);
		userExperience.setOrganizationBean(null);

		return userExperience;
	}

}