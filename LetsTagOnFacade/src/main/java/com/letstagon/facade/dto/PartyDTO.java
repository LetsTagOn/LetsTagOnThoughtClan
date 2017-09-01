package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the PartyDTO core entity.
 * 
 */

public class PartyDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The party type. */
	private String partyType;
	
	/** The comments. */
	private List<CommentDTO> comments;
	
	/** The connections 1. */
	private List<ConnectionDTO> connections1;
	
	/** The connections 2. */
	private List<ConnectionDTO> connections2;
	
	/** The notifications. */
	private List<NotificationDTO> notifications;
	
	/** The opportunities. */
	private List<OpportunityDTO> opportunities;
	
	/** The organization bean. */
	private OrganizationDTO organizationBean;
	
	/** The user bean. */
	private UserDTO userBean;
	
	/** The party cause xrefs. */
	private List<PartyCauseXrefDTO> partyCauseXrefs;
	
	/** The party job type xrefs. */
	private List<PartyJobTypeXrefDTO> partyJobTypeXrefs;
	
	/** The party participations. */
	private List<PartyParticipationDTO> partyParticipations;
	
	/** The posts created. */
	private List<PostDTO> postsCreated;
	
	/** The posts to me. */
	private List<PostDTO> postsToMe;
	
	/** The rating. */
	private Float rating;

	/**
	 * Instantiates a new party DTO.
	 */
	public PartyDTO() {
	}

	/**
	 * Instantiates a new party DTO.
	 *
	 * @param id the id
	 */
	public PartyDTO(long id) {
		super();
		this.id = id;
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
	public List<CommentDTO> getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 * @return the comment DTO
	 */
	public CommentDTO addComment(CommentDTO comment) {
		getComments().add(comment);
		comment.setPartyBean(this);

		return comment;
	}

	/**
	 * Removes the comment.
	 *
	 * @param comment the comment
	 * @return the comment DTO
	 */
	public CommentDTO removeComment(CommentDTO comment) {
		getComments().remove(comment);
		comment.setPartyBean(null);

		return comment;
	}

	/**
	 * Gets the connections 1.
	 *
	 * @return the connections 1
	 */
	public List<ConnectionDTO> getConnections1() {
		return this.connections1;
	}

	/**
	 * Sets the connections 1.
	 *
	 * @param connections1 the new connections 1
	 */
	public void setConnections1(List<ConnectionDTO> connections1) {
		this.connections1 = connections1;
	}

	/**
	 * Adds the connections 1.
	 *
	 * @param connections1 the connections 1
	 * @return the connection DTO
	 */
	public ConnectionDTO addConnections1(ConnectionDTO connections1) {
		getConnections1().add(connections1);
		connections1.setParty1(this);

		return connections1;
	}

	/**
	 * Removes the connections 1.
	 *
	 * @param connections1 the connections 1
	 * @return the connection DTO
	 */
	public ConnectionDTO removeConnections1(ConnectionDTO connections1) {
		getConnections1().remove(connections1);
		connections1.setParty1(null);

		return connections1;
	}

	/**
	 * Gets the connections 2.
	 *
	 * @return the connections 2
	 */
	public List<ConnectionDTO> getConnections2() {
		return this.connections2;
	}

	/**
	 * Sets the connections 2.
	 *
	 * @param connections2 the new connections 2
	 */
	public void setConnections2(List<ConnectionDTO> connections2) {
		this.connections2 = connections2;
	}

	/**
	 * Adds the connections 2.
	 *
	 * @param connections2 the connections 2
	 * @return the connection DTO
	 */
	public ConnectionDTO addConnections2(ConnectionDTO connections2) {
		getConnections2().add(connections2);
		connections2.setParty2(this);

		return connections2;
	}

	/**
	 * Removes the connections 2.
	 *
	 * @param connections2 the connections 2
	 * @return the connection DTO
	 */
	public ConnectionDTO removeConnections2(ConnectionDTO connections2) {
		getConnections2().remove(connections2);
		connections2.setParty2(null);

		return connections2;
	}

	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public List<NotificationDTO> getNotifications() {
		return this.notifications;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications the new notifications
	 */
	public void setNotifications(List<NotificationDTO> notifications) {
		this.notifications = notifications;
	}

	/**
	 * Adds the notification.
	 *
	 * @param notification the notification
	 * @return the notification DTO
	 */
	public NotificationDTO addNotification(NotificationDTO notification) {
		getNotifications().add(notification);
		notification.setPartyBean(this);

		return notification;
	}

	/**
	 * Removes the notification.
	 *
	 * @param notification the notification
	 * @return the notification DTO
	 */
	public NotificationDTO removeNotification(NotificationDTO notification) {
		getNotifications().remove(notification);
		notification.setPartyBean(null);

		return notification;
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
		opportunity.setCreatedBy(this);

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
		opportunity.setCreatedBy(null);

		return opportunity;
	}

	/**
	 * Gets the organization bean.
	 *
	 * @return the organization bean
	 */
	public OrganizationDTO getOrganizationBean() {
		return this.organizationBean;
	}

	/**
	 * Sets the organization bean.
	 *
	 * @param organizationBean the new organization bean
	 */
	public void setOrganizationBean(OrganizationDTO organizationBean) {
		this.organizationBean = organizationBean;
	}

	/**
	 * Gets the user bean.
	 *
	 * @return the user bean
	 */
	public UserDTO getUserBean() {
		return this.userBean;
	}

	/**
	 * Sets the user bean.
	 *
	 * @param userBean the new user bean
	 */
	public void setUserBean(UserDTO userBean) {
		this.userBean = userBean;
	}

	/**
	 * Gets the party cause xrefs.
	 *
	 * @return the party cause xrefs
	 */
	public List<PartyCauseXrefDTO> getPartyCauseXrefs() {
		return this.partyCauseXrefs;
	}

	/**
	 * Sets the party cause xrefs.
	 *
	 * @param partyCauseXrefs the new party cause xrefs
	 */
	public void setPartyCauseXrefs(List<PartyCauseXrefDTO> partyCauseXrefs) {
		this.partyCauseXrefs = partyCauseXrefs;
	}

	/**
	 * Adds the party cause xref.
	 *
	 * @param partyCauseXref the party cause xref
	 * @return the party cause xref DTO
	 */
	public PartyCauseXrefDTO addPartyCauseXref(PartyCauseXrefDTO partyCauseXref) {
		getPartyCauseXrefs().add(partyCauseXref);
		partyCauseXref.setPartyBean(this);

		return partyCauseXref;
	}

	/**
	 * Removes the party cause xref.
	 *
	 * @param partyCauseXref the party cause xref
	 * @return the party cause xref DTO
	 */
	public PartyCauseXrefDTO removePartyCauseXref(PartyCauseXrefDTO partyCauseXref) {
		getPartyCauseXrefs().remove(partyCauseXref);
		partyCauseXref.setPartyBean(null);

		return partyCauseXref;
	}

	/**
	 * Gets the party job type xrefs.
	 *
	 * @return the party job type xrefs
	 */
	public List<PartyJobTypeXrefDTO> getPartyJobTypeXrefs() {
		return this.partyJobTypeXrefs;
	}

	/**
	 * Sets the party job type xrefs.
	 *
	 * @param partyJobTypeXrefs the new party job type xrefs
	 */
	public void setPartyJobTypeXrefs(List<PartyJobTypeXrefDTO> partyJobTypeXrefs) {
		this.partyJobTypeXrefs = partyJobTypeXrefs;
	}

	/**
	 * Adds the party job type xref.
	 *
	 * @param partyJobTypeXref the party job type xref
	 * @return the party job type xref DTO
	 */
	public PartyJobTypeXrefDTO addPartyJobTypeXref(PartyJobTypeXrefDTO partyJobTypeXref) {
		getPartyJobTypeXrefs().add(partyJobTypeXref);
		partyJobTypeXref.setPartyBean(this);

		return partyJobTypeXref;
	}

	/**
	 * Removes the party job type xref.
	 *
	 * @param partyJobTypeXref the party job type xref
	 * @return the party job type xref DTO
	 */
	public PartyJobTypeXrefDTO removePartyJobTypeXref(PartyJobTypeXrefDTO partyJobTypeXref) {
		getPartyJobTypeXrefs().remove(partyJobTypeXref);
		partyJobTypeXref.setPartyBean(null);

		return partyJobTypeXref;
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
		partyParticipation.setPartyBean(this);

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
		partyParticipation.setPartyBean(null);

		return partyParticipation;
	}

	/**
	 * Gets the posts created.
	 *
	 * @return the posts created
	 */
	public List<PostDTO> getPostsCreated() {
		return postsCreated;
	}

	/**
	 * Sets the posts created.
	 *
	 * @param postsCreated the new posts created
	 */
	public void setPostsCreated(List<PostDTO> postsCreated) {
		this.postsCreated = postsCreated;
	}

	/**
	 * Gets the posts to me.
	 *
	 * @return the posts to me
	 */
	public List<PostDTO> getPostsToMe() {
		return postsToMe;
	}

	/**
	 * Sets the posts to me.
	 *
	 * @param postsToMe the new posts to me
	 */
	public void setPostsToMe(List<PostDTO> postsToMe) {
		this.postsToMe = postsToMe;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PartyDTO [id=" + id + ", organizationBean=" + organizationBean + ", userBean=" + userBean + "]";
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
		PartyDTO other = (PartyDTO) obj;
		if (id != other.id)
			return false;
		return true;
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

}
