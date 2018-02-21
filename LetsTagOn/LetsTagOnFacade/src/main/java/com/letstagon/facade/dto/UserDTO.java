package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the UserDTO core entity.
 * 
 */

public class UserDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The date of birth. */
	private Date dateOfBirth;
	
	/** The email address. */
	private String emailAddress;
	
	/** The name. */
	private String name;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The password. */
	private String password;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The privacy mode. */
	private String privacyMode;
	
	/** The profile picture. */
	private String profilePicture;
	
	/** The reset passord token. */
	private String resetPassordToken;
	
	/** The user name. */
	private String userName;
	
	/** The user role. */
	private String userRole;
	
	/** The app invites. */
	private List<AppInviteDTO> appInvites;
	
	/** The opportunities. */
	private List<OpportunityDTO> opportunities;
	
	/** The organizations. */
	private List<OrganizationDTO> organizations;
	
	/** The parties. */
	private List<PartyDTO> parties;
	
	/** The party feedbacks. */
	private List<PartyFeedbackDTO> partyFeedbacks;
	
	/** The address bean. */
	private AddressDTO addressBean;
	
	/** The user type bean. */
	private UserTypeDTO userTypeBean;
	
	/** The user additional profile attributes. */
	private List<UserAdditionalProfileAttributeDTO> userAdditionalProfileAttributes;
	
	/** The user experiences. */
	private List<UserExperienceDTO> userExperiences;
	
	/** The user social connects. */
	private List<UserSocialConnectDTO> userSocialConnects;
	
	/** The volunteer prefs. */
	private List<VolunteerPrefDTO> volunteerPrefs;
	
	/** The gender. */
	private String gender;
	
	/** The account verified. */
	private String accountVerified;
	
	/** The otp for verification */
	private String token;
	
	/** The reset password expiry. */
	private Date resetPasswordExpiry;
	
	/** The reset password verify. */
	private boolean resetPasswordVerify;
	
	/** The summary. */
	private String summary;

	/**
	 * Instantiates a new user DTO.
	 */
	public UserDTO() {
	}

	/**
	 * Instantiates a new user DTO.
	 *
	 * @param id the id
	 */
	public UserDTO(long id) {
		super();
		this.id = id;
	}

	/**
	 * Checks if is reset password verify.
	 *
	 * @return true, if is reset password verify
	 */
	public boolean isResetPasswordVerify() {
		return resetPasswordVerify;
	}

	/**
	 * Sets the reset password verify.
	 *
	 * @param resetPasswordVerify the new reset password verify
	 */
	public void setResetPasswordVerify(boolean resetPasswordVerify) {
		this.resetPasswordVerify = resetPasswordVerify;
	}

	/**
	 * Gets the reset password expiry.
	 *
	 * @return the reset password expiry
	 */
	public Date getResetPasswordExpiry() {
		return resetPasswordExpiry;
	}

	/**
	 * Sets the reset password expiry.
	 *
	 * @param resetPasswordExpiry the new reset password expiry
	 */
	public void setResetPasswordExpiry(Date resetPasswordExpiry) {
		this.resetPasswordExpiry = resetPasswordExpiry;
	}

	/**
	 * Gets the account verified.
	 *
	 * @return the account verified
	 */
	public String getAccountVerified() {
		return accountVerified;
	}

	/**
	 * Sets the account verified.
	 *
	 * @param accountVerified the new account verified
	 */
	public void setAccountVerified(String accountVerified) {
		this.accountVerified = accountVerified;
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
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the privacy mode.
	 *
	 * @return the privacy mode
	 */
	public String getPrivacyMode() {
		return this.privacyMode;
	}

	/**
	 * Sets the privacy mode.
	 *
	 * @param privacyMode the new privacy mode
	 */
	public void setPrivacyMode(String privacyMode) {
		this.privacyMode = privacyMode;
	}

	/**
	 * Gets the profile picture.
	 *
	 * @return the profile picture
	 */
	public String getProfilePicture() {
		return this.profilePicture;
	}

	/**
	 * Sets the profile picture.
	 *
	 * @param profilePicture the new profile picture
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * Gets the reset passord token.
	 *
	 * @return the reset passord token
	 */
	public String getResetPassordToken() {
		return this.resetPassordToken;
	}

	/**
	 * Sets the reset passord token.
	 *
	 * @param resetPassordToken the new reset passord token
	 */
	public void setResetPassordToken(String resetPassordToken) {
		this.resetPassordToken = resetPassordToken;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	public String getUserRole() {
		return this.userRole;
	}

	/**
	 * Sets the user role.
	 *
	 * @param userRole the new user role
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * Gets the app invites.
	 *
	 * @return the app invites
	 */
	public List<AppInviteDTO> getAppInvites() {
		return this.appInvites;
	}

	/**
	 * Sets the app invites.
	 *
	 * @param appInvites the new app invites
	 */
	public void setAppInvites(List<AppInviteDTO> appInvites) {
		this.appInvites = appInvites;
	}

	/**
	 * Adds the app invite.
	 *
	 * @param appInvite the app invite
	 * @return the app invite DTO
	 */
	public AppInviteDTO addAppInvite(AppInviteDTO appInvite) {
		getAppInvites().add(appInvite);
		appInvite.setUser(this);

		return appInvite;
	}

	/**
	 * Removes the app invite.
	 *
	 * @param appInvite the app invite
	 * @return the app invite DTO
	 */
	public AppInviteDTO removeAppInvite(AppInviteDTO appInvite) {
		getAppInvites().remove(appInvite);
		appInvite.setUser(null);

		return appInvite;
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
		opportunity.setContactPerson(this);

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
		opportunity.setContactPerson(null);

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
		organization.setUser(this);

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
		organization.setUser(null);

		return organization;
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
		party.setUserBean(this);

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
		party.setUserBean(null);

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
		partyFeedback.setUser(this);

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
		partyFeedback.setUser(null);

		return partyFeedback;
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
	 * Gets the user type bean.
	 *
	 * @return the user type bean
	 */
	public UserTypeDTO getUserTypeBean() {
		return this.userTypeBean;
	}

	/**
	 * Sets the user type bean.
	 *
	 * @param userTypeBean the new user type bean
	 */
	public void setUserTypeBean(UserTypeDTO userTypeBean) {
		this.userTypeBean = userTypeBean;
	}

	/**
	 * Gets the user additional profile attributes.
	 *
	 * @return the user additional profile attributes
	 */
	// UserAdditionalProfileAttributeDTO
	public List<UserAdditionalProfileAttributeDTO> getUserAdditionalProfileAttributes() {
		return this.userAdditionalProfileAttributes;
	}

	/**
	 * Sets the user additional profile attributes.
	 *
	 * @param userAdditionalProfileAttributes the new user additional profile attributes
	 */
	public void setUserAdditionalProfileAttributes(
			List<UserAdditionalProfileAttributeDTO> userAdditionalProfileAttributes) {
		this.userAdditionalProfileAttributes = userAdditionalProfileAttributes;
	}

	/**
	 * Adds the user additional profile attribute.
	 *
	 * @param userAdditionalProfileAttribute the user additional profile attribute
	 * @return the user additional profile attribute DTO
	 */
	public UserAdditionalProfileAttributeDTO addUserAdditionalProfileAttribute(
			UserAdditionalProfileAttributeDTO userAdditionalProfileAttribute) {
		getUserAdditionalProfileAttributes().add(userAdditionalProfileAttribute);
		userAdditionalProfileAttribute.setUserBean(this);

		return userAdditionalProfileAttribute;
	}

	/**
	 * Removes the user additional profile attribute.
	 *
	 * @param userAdditionalProfileAttribute the user additional profile attribute
	 * @return the user additional profile attribute DTO
	 */
	public UserAdditionalProfileAttributeDTO removeUserAdditionalProfileAttribute(
			UserAdditionalProfileAttributeDTO userAdditionalProfileAttribute) {
		getUserAdditionalProfileAttributes().remove(userAdditionalProfileAttribute);
		userAdditionalProfileAttribute.setUserBean(null);

		return userAdditionalProfileAttribute;
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
		userExperience.setUserBean(this);

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
		userExperience.setUserBean(null);

		return userExperience;
	}

	/**
	 * Gets the user social connects.
	 *
	 * @return the user social connects
	 */
	public List<UserSocialConnectDTO> getUserSocialConnects() {
		return this.userSocialConnects;
	}

	/**
	 * Sets the user social connects.
	 *
	 * @param userSocialConnects the new user social connects
	 */
	public void setUserSocialConnects(List<UserSocialConnectDTO> userSocialConnects) {
		this.userSocialConnects = userSocialConnects;
	}

	/**
	 * Adds the user social connect.
	 *
	 * @param userSocialConnect the user social connect
	 * @return the user social connect DTO
	 */
	public UserSocialConnectDTO addUserSocialConnect(UserSocialConnectDTO userSocialConnect) {
		getUserSocialConnects().add(userSocialConnect);
		userSocialConnect.setUser(this);

		return userSocialConnect;
	}

	/**
	 * Removes the user social connect.
	 *
	 * @param userSocialConnect the user social connect
	 * @return the user social connect DTO
	 */
	public UserSocialConnectDTO removeUserSocialConnect(UserSocialConnectDTO userSocialConnect) {
		getUserSocialConnects().remove(userSocialConnect);
		userSocialConnect.setUser(null);

		return userSocialConnect;
	}

	/**
	 * Gets the volunteer prefs.
	 *
	 * @return the volunteer prefs
	 */
	public List<VolunteerPrefDTO> getVolunteerPrefs() {
		return this.volunteerPrefs;
	}

	/**
	 * Sets the volunteer prefs.
	 *
	 * @param volunteerPrefs the new volunteer prefs
	 */
	public void setVolunteerPrefs(List<VolunteerPrefDTO> volunteerPrefs) {
		this.volunteerPrefs = volunteerPrefs;
	}

	/**
	 * Adds the volunteer pref.
	 *
	 * @param volunteerPref the volunteer pref
	 * @return the volunteer pref DTO
	 */
	public VolunteerPrefDTO addVolunteerPref(VolunteerPrefDTO volunteerPref) {
		getVolunteerPrefs().add(volunteerPref);
		volunteerPref.setUser(this);

		return volunteerPref;
	}

	/**
	 * Removes the volunteer pref.
	 *
	 * @param volunteerPref the volunteer pref
	 * @return the volunteer pref DTO
	 */
	public VolunteerPrefDTO removeVolunteerPref(VolunteerPrefDTO volunteerPref) {
		getVolunteerPrefs().remove(volunteerPref);
		volunteerPref.setUser(null);

		return volunteerPref;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the otp for verification.
	 *
	 * @returns the otp
	 */
	public String getToken() {
		return token;
	}


	/**
	 * Sets the otp for verification.
	 *
	 * @param token the new otp token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the summary.
	 *
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary.
	 *
	 * @param summary the new summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", emailAddress=" + emailAddress + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userName=" + userName + "]";
	}

}
