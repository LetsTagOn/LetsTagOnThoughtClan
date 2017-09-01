package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the OrganizationDTO core entity.
 * 
 */

public class OrganizationDTO implements Serializable {
	
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
	private AddressDTO addressBean;
	
	/** The user. */
	private UserDTO user;
	
	/** The parties. */
	private List<PartyDTO> parties;
	
	/** The party feedbacks. */
	private List<PartyFeedbackDTO> partyFeedbacks;
	
	/** The party participations. */
	private List<PartyParticipationDTO> partyParticipations;
	
	/** The user experiences. */
	private List<UserExperienceDTO> userExperiences;

	/**
	 * Instantiates a new organization DTO.
	 */
	public OrganizationDTO() {
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
	public AddressDTO getAddressBean() {
		return this.addressBean;
	}

	/**
	 * Sets the address bean.
	 *
	 * @param addressBean the new address bean
	 */
	public void setAddressBean(AddressDTO addressBean) {
		this.addressBean = addressBean;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public UserDTO getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * Gets the parties.
	 *
	 * @return the parties
	 */
	public List<PartyDTO> getParties() {
		return this.parties;
	}

	/**
	 * Sets the parties.
	 *
	 * @param parties the new parties
	 */
	public void setParties(List<PartyDTO> parties) {
		this.parties = parties;
	}

	/**
	 * Adds the party.
	 *
	 * @param party the party
	 * @return the party DTO
	 */
	public PartyDTO addParty(PartyDTO party) {
		getParties().add(party);
		party.setOrganizationBean(this);

		return party;
	}

	/**
	 * Removes the party.
	 *
	 * @param party the party
	 * @return the party DTO
	 */
	public PartyDTO removeParty(PartyDTO party) {
		getParties().remove(party);
		party.setOrganizationBean(null);

		return party;
	}

	/**
	 * Gets the party feedbacks.
	 *
	 * @return the party feedbacks
	 */
	public List<PartyFeedbackDTO> getPartyFeedbacks() {
		return this.partyFeedbacks;
	}

	/**
	 * Sets the party feedbacks.
	 *
	 * @param partyFeedbacks the new party feedbacks
	 */
	public void setPartyFeedbacks(List<PartyFeedbackDTO> partyFeedbacks) {
		this.partyFeedbacks = partyFeedbacks;
	}

	/**
	 * Adds the party feedback.
	 *
	 * @param partyFeedback the party feedback
	 * @return the party feedback DTO
	 */
	public PartyFeedbackDTO addPartyFeedback(PartyFeedbackDTO partyFeedback) {
		getPartyFeedbacks().add(partyFeedback);
		partyFeedback.setOrganization(this);

		return partyFeedback;
	}

	/**
	 * Removes the party feedback.
	 *
	 * @param partyFeedback the party feedback
	 * @return the party feedback DTO
	 */
	public PartyFeedbackDTO removePartyFeedback(PartyFeedbackDTO partyFeedback) {
		getPartyFeedbacks().remove(partyFeedback);
		partyFeedback.setOrganization(null);

		return partyFeedback;
	}

	/**
	 * Gets the party participations.
	 *
	 * @return the party participations
	 */
	public List<PartyParticipationDTO> getPartyParticipations() {
		return this.partyParticipations;
	}

	/**
	 * Sets the party participations.
	 *
	 * @param partyParticipations the new party participations
	 */
	public void setPartyParticipations(List<PartyParticipationDTO> partyParticipations) {
		this.partyParticipations = partyParticipations;
	}

	/**
	 * Adds the party participation.
	 *
	 * @param partyParticipation the party participation
	 * @return the party participation DTO
	 */
	public PartyParticipationDTO addPartyParticipation(PartyParticipationDTO partyParticipation) {
		getPartyParticipations().add(partyParticipation);
		partyParticipation.setOrganization(this);

		return partyParticipation;
	}

	/**
	 * Removes the party participation.
	 *
	 * @param partyParticipation the party participation
	 * @return the party participation DTO
	 */
	public PartyParticipationDTO removePartyParticipation(PartyParticipationDTO partyParticipation) {
		getPartyParticipations().remove(partyParticipation);
		partyParticipation.setOrganization(null);

		return partyParticipation;
	}

	/**
	 * Gets the user experiences.
	 *
	 * @return the user experiences
	 */
	public List<UserExperienceDTO> getUserExperiences() {
		return this.userExperiences;
	}

	/**
	 * Sets the user experiences.
	 *
	 * @param userExperiences the new user experiences
	 */
	public void setUserExperiences(List<UserExperienceDTO> userExperiences) {
		this.userExperiences = userExperiences;
	}

	/**
	 * Adds the user experience.
	 *
	 * @param userExperience the user experience
	 * @return the user experience DTO
	 */
	public UserExperienceDTO addUserExperience(UserExperienceDTO userExperience) {
		getUserExperiences().add(userExperience);
		userExperience.setOrganizationBean(this);

		return userExperience;
	}

	/**
	 * Removes the user experience.
	 *
	 * @param userExperience the user experience
	 * @return the user experience DTO
	 */
	public UserExperienceDTO removeUserExperience(UserExperienceDTO userExperience) {
		getUserExperiences().remove(userExperience);
		userExperience.setOrganizationBean(null);

		return userExperience;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrganizationDTO [id=" + id + ", emailAddress=" + emailAddress + ", name=" + name + "]";
	}

}
