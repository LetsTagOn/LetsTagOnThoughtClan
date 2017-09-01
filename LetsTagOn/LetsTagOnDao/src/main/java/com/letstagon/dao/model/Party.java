package com.letstagon.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Party database table.
 * 
 */
@Entity
@NamedQuery(name = "Party.findAll", query = "SELECT p FROM Party p")
public class Party implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The party type. */
	private String partyType;
	
	/** The comments. */
	private List<Comment> comments;
	
	/** The connections 1. */
	private List<Connection> connections1;
	
	/** The connections 2. */
	private List<Connection> connections2;
	
	/** The notifications. */
	private List<Notification> notifications;
	
	/** The opportunities. */
	private List<Opportunity> opportunities;
	
	/** The organization bean. */
	private Organization organizationBean;
	
	/** The user bean. */
	private User userBean;
	
	/** The party cause xrefs. */
	private List<PartyCauseXref> partyCauseXrefs;
	
	/** The party job type xrefs. */
	private List<PartyJobTypeXref> partyJobTypeXrefs;
	
	/** The party participations. */
	private List<PartyParticipation> partyParticipations;
	
	/** The created posts. */
	private List<Post> createdPosts;
	
	/** The posted to me. */
	private List<Post> postedToMe;
	
	/** The rating. */
	private Float rating;

	/**
	 * Instantiates a new party.
	 */
	public Party() {
	}

	/**
	 * Instantiates a new party.
	 *
	 * @param id the id
	 */
	public Party(Integer id) {
		this.setId(id);
	}

	/**
	 * Instantiates a new party.
	 *
	 * @param organizationBean the organization bean
	 */
	public Party(Organization organizationBean) {
		super();
		this.organizationBean = organizationBean;
	}

	/**
	 * Instantiates a new party.
	 *
	 * @param userBean the user bean
	 */
	public Party(User userBean) {
		super();
		this.userBean = userBean;
	}

	/**
	 * Instantiates a new party.
	 *
	 * @param id the id
	 */
	public Party(long id) {
		this.setId(id);
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
	 * Gets the party type.
	 *
	 * @return the party type
	 */
	public String getPartyType() {
		return this.partyType;
	}

	/**
	 * Sets the party type.
	 *
	 * @param partyType the new party type
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "partyBean")
	public List<Comment> getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 * @return the comment
	 */
	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setPartyBean(this);

		return comment;
	}

	/**
	 * Removes the comment.
	 *
	 * @param comment the comment
	 * @return the comment
	 */
	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setPartyBean(null);

		return comment;
	}

	/**
	 * Gets the connections 1.
	 *
	 * @return the connections 1
	 */
	// bi-directional many-to-one association to Connection
	@OneToMany(mappedBy = "party1")
	public List<Connection> getConnections1() {
		return this.connections1;
	}

	/**
	 * Sets the connections 1.
	 *
	 * @param connections1 the new connections 1
	 */
	public void setConnections1(List<Connection> connections1) {
		this.connections1 = connections1;
	}

	/**
	 * Adds the connections 1.
	 *
	 * @param connections1 the connections 1
	 * @return the connection
	 */
	public Connection addConnections1(Connection connections1) {
		getConnections1().add(connections1);
		connections1.setParty1(this);

		return connections1;
	}

	/**
	 * Removes the connections 1.
	 *
	 * @param connections1 the connections 1
	 * @return the connection
	 */
	public Connection removeConnections1(Connection connections1) {
		getConnections1().remove(connections1);
		connections1.setParty1(null);

		return connections1;
	}

	/**
	 * Gets the connections 2.
	 *
	 * @return the connections 2
	 */
	// bi-directional many-to-one association to Connection
	@OneToMany(mappedBy = "party2")
	public List<Connection> getConnections2() {
		return this.connections2;
	}

	/**
	 * Sets the connections 2.
	 *
	 * @param connections2 the new connections 2
	 */
	public void setConnections2(List<Connection> connections2) {
		this.connections2 = connections2;
	}

	/**
	 * Adds the connections 2.
	 *
	 * @param connections2 the connections 2
	 * @return the connection
	 */
	public Connection addConnections2(Connection connections2) {
		getConnections2().add(connections2);
		connections2.setParty2(this);

		return connections2;
	}

	/**
	 * Removes the connections 2.
	 *
	 * @param connections2 the connections 2
	 * @return the connection
	 */
	public Connection removeConnections2(Connection connections2) {
		getConnections2().remove(connections2);
		connections2.setParty2(null);

		return connections2;
	}

	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	// bi-directional many-to-one association to Notification
	@OneToMany(mappedBy = "partyBean")
	public List<Notification> getNotifications() {
		return this.notifications;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications the new notifications
	 */
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	/**
	 * Adds the notification.
	 *
	 * @param notification the notification
	 * @return the notification
	 */
	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setPartyBean(this);

		return notification;
	}

	/**
	 * Removes the notification.
	 *
	 * @param notification the notification
	 * @return the notification
	 */
	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setPartyBean(null);

		return notification;
	}

	/**
	 * Gets the opportunities.
	 *
	 * @return the opportunities
	 */
	// bi-directional many-to-one association to Opportunity
	@OneToMany(mappedBy = "createdByParty")
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
		opportunity.setCreatedByParty(this);

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
		opportunity.setCreatedByParty(null);

		return opportunity;
	}

	/**
	 * Gets the organization bean.
	 *
	 * @return the organization bean
	 */
	// bi-directional many-to-one association to Organization
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	public Organization getOrganizationBean() {
		return this.organizationBean;
	}

	/**
	 * Sets the organization bean.
	 *
	 * @param organizationBean the new organization bean
	 */
	public void setOrganizationBean(Organization organizationBean) {
		this.organizationBean = organizationBean;
	}

	/**
	 * Gets the user bean.
	 *
	 * @return the user bean
	 */
	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
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

	/**
	 * Gets the party cause xrefs.
	 *
	 * @return the party cause xrefs
	 */
	// bi-directional many-to-one association to PartyCauseXref
	@OneToMany(mappedBy = "partyBean")
	public List<PartyCauseXref> getPartyCauseXrefs() {
		return this.partyCauseXrefs;
	}

	/**
	 * Sets the party cause xrefs.
	 *
	 * @param partyCauseXrefs the new party cause xrefs
	 */
	public void setPartyCauseXrefs(List<PartyCauseXref> partyCauseXrefs) {
		this.partyCauseXrefs = partyCauseXrefs;
	}

	/**
	 * Adds the party cause xref.
	 *
	 * @param partyCauseXref the party cause xref
	 * @return the party cause xref
	 */
	public PartyCauseXref addPartyCauseXref(PartyCauseXref partyCauseXref) {
		getPartyCauseXrefs().add(partyCauseXref);
		partyCauseXref.setPartyBean(this);

		return partyCauseXref;
	}

	/**
	 * Removes the party cause xref.
	 *
	 * @param partyCauseXref the party cause xref
	 * @return the party cause xref
	 */
	public PartyCauseXref removePartyCauseXref(PartyCauseXref partyCauseXref) {
		getPartyCauseXrefs().remove(partyCauseXref);
		partyCauseXref.setPartyBean(null);

		return partyCauseXref;
	}

	/**
	 * Gets the party job type xrefs.
	 *
	 * @return the party job type xrefs
	 */
	// bi-directional many-to-one association to PartyJobTypeXref
	@OneToMany(mappedBy = "partyBean")
	public List<PartyJobTypeXref> getPartyJobTypeXrefs() {
		return this.partyJobTypeXrefs;
	}

	/**
	 * Sets the party job type xrefs.
	 *
	 * @param partyJobTypeXrefs the new party job type xrefs
	 */
	public void setPartyJobTypeXrefs(List<PartyJobTypeXref> partyJobTypeXrefs) {
		this.partyJobTypeXrefs = partyJobTypeXrefs;
	}

	/**
	 * Adds the party job type xref.
	 *
	 * @param partyJobTypeXref the party job type xref
	 * @return the party job type xref
	 */
	public PartyJobTypeXref addPartyJobTypeXref(PartyJobTypeXref partyJobTypeXref) {
		getPartyJobTypeXrefs().add(partyJobTypeXref);
		partyJobTypeXref.setPartyBean(this);

		return partyJobTypeXref;
	}

	/**
	 * Removes the party job type xref.
	 *
	 * @param partyJobTypeXref the party job type xref
	 * @return the party job type xref
	 */
	public PartyJobTypeXref removePartyJobTypeXref(PartyJobTypeXref partyJobTypeXref) {
		getPartyJobTypeXrefs().remove(partyJobTypeXref);
		partyJobTypeXref.setPartyBean(null);

		return partyJobTypeXref;
	}

	/**
	 * Gets the party participations.
	 *
	 * @return the party participations
	 */
	// bi-directional many-to-one association to PartyParticipation
	@OneToMany(mappedBy = "partyBean")
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
		partyParticipation.setPartyBean(this);

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
		partyParticipation.setPartyBean(null);

		return partyParticipation;
	}

	/**
	 * Gets the created posts.
	 *
	 * @return the created posts
	 */
	// bi-directional many-to-one association to Post
	@OneToMany(mappedBy = "postedByParty")
	public List<Post> getCreatedPosts() {
		return createdPosts;
	}

	/**
	 * Sets the created posts.
	 *
	 * @param createdPosts the new created posts
	 */
	public void setCreatedPosts(List<Post> createdPosts) {
		this.createdPosts = createdPosts;
	}

	/**
	 * Gets the posted to me.
	 *
	 * @return the posted to me
	 */
	// bi-directional many-to-one association to Post
	@OneToMany(mappedBy = "postedForParty")
	public List<Post> getPostedToMe() {
		return postedToMe;
	}

	/**
	 * Sets the posted to me.
	 *
	 * @param postedToMe the new posted to me
	 */
	public void setPostedToMe(List<Post> postedToMe) {
		this.postedToMe = postedToMe;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Float getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(Float rating) {
		this.rating = rating;
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
		Party other = (Party) obj;
		if (id != other.id)
			return false;
		return true;
	}

}