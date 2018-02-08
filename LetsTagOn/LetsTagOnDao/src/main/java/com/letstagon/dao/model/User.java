package com.letstagon.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

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
	
	/** The gender. */
	private String gender;
	
	/** The app invites. */
	private List<AppInvite> appInvites;
	
	/** The opportunities. */
	private List<Opportunity> opportunities;
	
	/** The organizations. */
	private List<Organization> organizations;
	
	/** The parties. */
	private List<Party> parties;
	
	/** The party feedbacks. */
	private List<PartyFeedback> partyFeedbacks;
	
	/** The address bean. */
	private Address addressBean;
	
	/** The user type bean. */
	private UserType userTypeBean;
	
	/** The user additional profile attributes. */
	private List<UserAdditionalProfileAttribute> userAdditionalProfileAttributes;
	
	/** The user experiences. */
	private List<UserExperience> userExperiences;
	
	/** The user social connects. */
	private List<UserSocialConnect> userSocialConnects;
	
	/** The volunteer prefs. */
	private List<VolunteerPref> volunteerPrefs;
	
	/** The account verified. */
	private String accountVerified;
	
	/** The otp for verification. */
	private String token;

	/** The reset password expiry. */
	private Date resetPasswordExpiry;
	
	/** The reset password verify. */
	private boolean resetPasswordVerify;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The summary. */
	private String summary;
	
	/** The modified date. */
	private Date modifiedDate;
	
	/** The user type xrefs. */
	private List<UserTypeXref> userTypeXrefs;

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

	/**
	 * Instantiates a new user.
	 */
	public User() {
		// default constructor
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 */
	public User(long id) {
		super();
		this.id = id;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 */
	public User(Integer id) {
		this.setId(id);
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param address the address
	 * @param userName the user name
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param emailAddress the email address
	 */
	public User(Address address, String userName, String firstName, String lastName, String emailAddress) {

		this.setUserName(userName);
		this.setEmailAddress(emailAddress);
		this.setPassword(UUID.randomUUID().toString());
		this.setFirstName(firstName);
		this.setLastName(lastName);

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
	@Temporal(TemporalType.DATE)
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	@Temporal(TemporalType.DATE)
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

		if (this.name == null) {
			return this.getFirstName() + "" + (this.getLastName() != null ? this.getLastName() : "");
		}
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
	 * Gets the app invites.
	 *
	 * @return the app invites
	 */
	// bi-directional many-to-one association to AppInvite
	@OneToMany(mappedBy = "user")
	public List<AppInvite> getAppInvites() {
		return this.appInvites;
	}

	/**
	 * Sets the app invites.
	 *
	 * @param appInvites the new app invites
	 */
	public void setAppInvites(List<AppInvite> appInvites) {
		this.appInvites = appInvites;
	}

	/**
	 * Adds the app invite.
	 *
	 * @param appInvite the app invite
	 * @return the app invite
	 */
	public AppInvite addAppInvite(AppInvite appInvite) {
		getAppInvites().add(appInvite);
		appInvite.setUser(this);

		return appInvite;
	}

	/**
	 * Removes the app invite.
	 *
	 * @param appInvite the app invite
	 * @return the app invite
	 */
	public AppInvite removeAppInvite(AppInvite appInvite) {
		getAppInvites().remove(appInvite);
		appInvite.setUser(null);

		return appInvite;
	}

	/**
	 * Gets the opportunities.
	 *
	 * @return the opportunities
	 */
	// bi-directional many-to-one association to Opportunity
	@OneToMany(mappedBy = "contactPerson")
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
		opportunity.setContactPerson(this);

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
		opportunity.setContactPerson(null);

		return opportunity;
	}

	/**
	 * Gets the organizations.
	 *
	 * @return the organizations
	 */
	// bi-directional many-to-one association to Organization
	@OneToMany(mappedBy = "user")
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
		organization.setUser(this);

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
		organization.setUser(null);

		return organization;
	}

	/**
	 * Gets the parties.
	 *
	 * @return the parties
	 */
	// bi-directional many-to-one association to Party
	@OneToMany(mappedBy = "userBean")
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
	 * Gets the user type xrefs.
	 *
	 * @return the user type xrefs
	 */
	// bi-directional many-to-one association to Party
	@OneToMany(mappedBy = "user")
	public List<UserTypeXref> getUserTypeXrefs() {
		return userTypeXrefs;
	}

	/**
	 * Sets the user type xrefs.
	 *
	 * @param userTypeXrefs the new user type xrefs
	 */
	public void setUserTypeXrefs(List<UserTypeXref> userTypeXrefs) {
		this.userTypeXrefs = userTypeXrefs;
	}

	/**
	 * Adds the party.
	 *
	 * @param party the party
	 * @return the party
	 */
	public Party addParty(Party party) {
		getParties().add(party);
		party.setUserBean(this);

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
		party.setUserBean(null);

		return party;
	}

	/**
	 * Gets the party feedbacks.
	 *
	 * @return the party feedbacks
	 */
	// bi-directional many-to-one association to PartyFeedback
	@OneToMany(mappedBy = "user")
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
		partyFeedback.setUser(this);

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
		partyFeedback.setUser(null);

		return partyFeedback;
	}

	/**
	 * Gets the address bean.
	 *
	 * @return the address bean
	 */
	// bi-directional many-to-one association to Address
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address")
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
	 * Gets the user type bean.
	 *
	 * @return the user type bean
	 */
	// bi-directional many-to-one association to UserType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userType")
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

	/**
	 * Gets the user additional profile attributes.
	 *
	 * @return the user additional profile attributes
	 */
	// bi-directional many-to-one association to UserAdditionalProfileAttribute
	@OneToMany(mappedBy = "userBean")
	public List<UserAdditionalProfileAttribute> getUserAdditionalProfileAttributes() {
		return this.userAdditionalProfileAttributes;
	}

	/**
	 * Sets the user additional profile attributes.
	 *
	 * @param userAdditionalProfileAttributes the new user additional profile attributes
	 */
	public void setUserAdditionalProfileAttributes(
			List<UserAdditionalProfileAttribute> userAdditionalProfileAttributes) {
		this.userAdditionalProfileAttributes = userAdditionalProfileAttributes;
	}

	/**
	 * Adds the user additional profile attribute.
	 *
	 * @param userAdditionalProfileAttribute the user additional profile attribute
	 * @return the user additional profile attribute
	 */
	public UserAdditionalProfileAttribute addUserAdditionalProfileAttribute(
			UserAdditionalProfileAttribute userAdditionalProfileAttribute) {
		getUserAdditionalProfileAttributes().add(userAdditionalProfileAttribute);
		userAdditionalProfileAttribute.setUserBean(this);

		return userAdditionalProfileAttribute;
	}

	/**
	 * Removes the user additional profile attribute.
	 *
	 * @param userAdditionalProfileAttribute the user additional profile attribute
	 * @return the user additional profile attribute
	 */
	public UserAdditionalProfileAttribute removeUserAdditionalProfileAttribute(
			UserAdditionalProfileAttribute userAdditionalProfileAttribute) {
		getUserAdditionalProfileAttributes().remove(userAdditionalProfileAttribute);
		userAdditionalProfileAttribute.setUserBean(null);

		return userAdditionalProfileAttribute;
	}

	/**
	 * Gets the user experiences.
	 *
	 * @return the user experiences
	 */
	// bi-directional many-to-one association to UserExperience
	@OneToMany(mappedBy = "userBean")
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
		userExperience.setUserBean(this);

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
		userExperience.setUserBean(null);

		return userExperience;
	}

	/**
	 * Gets the user social connects.
	 *
	 * @return the user social connects
	 */
	// bi-directional many-to-one association to UserSocialConnect
	@OneToMany(mappedBy = "user")
	public List<UserSocialConnect> getUserSocialConnects() {
		return this.userSocialConnects;
	}

	/**
	 * Sets the user social connects.
	 *
	 * @param userSocialConnects the new user social connects
	 */
	public void setUserSocialConnects(List<UserSocialConnect> userSocialConnects) {
		this.userSocialConnects = userSocialConnects;
	}

	/**
	 * Adds the user social connect.
	 *
	 * @param userSocialConnect the user social connect
	 * @return the user social connect
	 */
	public UserSocialConnect addUserSocialConnect(UserSocialConnect userSocialConnect) {
		getUserSocialConnects().add(userSocialConnect);
		userSocialConnect.setUser(this);

		return userSocialConnect;
	}

	/**
	 * Removes the user social connect.
	 *
	 * @param userSocialConnect the user social connect
	 * @return the user social connect
	 */
	public UserSocialConnect removeUserSocialConnect(UserSocialConnect userSocialConnect) {
		getUserSocialConnects().remove(userSocialConnect);
		userSocialConnect.setUser(null);

		return userSocialConnect;
	}

	/**
	 * Gets the volunteer prefs.
	 *
	 * @return the volunteer prefs
	 */
	// bi-directional many-to-one association to VolunteerPref
	@OneToMany(mappedBy = "user")
	public List<VolunteerPref> getVolunteerPrefs() {
		return this.volunteerPrefs;
	}

	/**
	 * Sets the volunteer prefs.
	 *
	 * @param volunteerPrefs the new volunteer prefs
	 */
	public void setVolunteerPrefs(List<VolunteerPref> volunteerPrefs) {
		this.volunteerPrefs = volunteerPrefs;
	}

	/**
	 * Adds the volunteer pref.
	 *
	 * @param volunteerPref the volunteer pref
	 * @return the volunteer pref
	 */
	public VolunteerPref addVolunteerPref(VolunteerPref volunteerPref) {
		getVolunteerPrefs().add(volunteerPref);
		volunteerPref.setUser(this);

		return volunteerPref;
	}

	/**
	 * Removes the volunteer pref.
	 *
	 * @param volunteerPref the volunteer pref
	 * @return the volunteer pref
	 */
	public VolunteerPref removeVolunteerPref(VolunteerPref volunteerPref) {
		getVolunteerPrefs().remove(volunteerPref);
		volunteerPref.setUser(null);

		return volunteerPref;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", emailAddress=" + emailAddress + ", userName=" + userName + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

}