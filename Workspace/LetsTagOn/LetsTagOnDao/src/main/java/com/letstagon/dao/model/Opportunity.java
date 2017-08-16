package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Opportunity database table.
 * 
 */
@Entity
@NamedQuery(name="Opportunity.findAll", query="SELECT o FROM Opportunity o")
public class Opportunity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The banner image. */
	private String bannerImage;
	
	/** The date end. */
	private Date dateEnd;
	
	/** The date start. */
	private Date dateStart;
	
	/** The description. */
	private String description;
	
	/** The lat long. */
	private String latLong;
	
	/** The name. */
	private String name;
	
	/** The type. */
	private String type;
	
	/** The parent program. */
	private Opportunity parentProgram;
	
	/** The linked events. */
	private List<Opportunity> linkedEvents;
	
	/** The address bean. */
	private Address addressBean;
	
	/** The contact person. */
	private User contactPerson;
	
	/** The created by party. */
	private Party createdByParty;
	
	/** The opportunity cause xrefs. */
	private List<OpportunityCauseXref> opportunityCauseXrefs;
	
	/** The opportunity job types. */
	private List<OpportunityJobType> opportunityJobTypes;
	
	/** The party participations. */
	private List<PartyParticipation> partyParticipations;
	
	/** The modified date. */
	private Date modifiedDate;

	/**
	 * Instantiates a new opportunity.
	 */
	public Opportunity() {
	}


	/**
	 * Instantiates a new opportunity.
	 *
	 * @param id the id
	 */
	public Opportunity(long id) {
		super();
		this.id = id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Opportunity [id=" + id + ", bannerImage=" + bannerImage + ", dateEnd=" + dateEnd 
				+ ", dateStart="+ dateStart + ", description=" + description + ", latLong="
				+ latLong + ", name=" + name + ", type="+ type ;
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
	 * Gets the banner image.
	 *
	 * @return the banner image
	 */
	public String getBannerImage() {
		return this.bannerImage;
	}

	/**
	 * Sets the banner image.
	 *
	 * @param bannerImage the new banner image
	 */
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}


	/**
	 * Gets the date end.
	 *
	 * @return the date end
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateEnd() {
		return this.dateEnd;
	}

	/**
	 * Sets the date end.
	 *
	 * @param dateEnd the new date end
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}


	/**
	 * Gets the date start.
	 *
	 * @return the date start
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateStart() {
		return this.dateStart;
	}

	/**
	 * Sets the date start.
	 *
	 * @param dateStart the new date start
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
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
	 * Gets the lat long.
	 *
	 * @return the lat long
	 */
	public String getLatLong() {
		return this.latLong;
	}

	/**
	 * Sets the lat long.
	 *
	 * @param latLong the new lat long
	 */
	public void setLatLong(String latLong) {
		this.latLong = latLong;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * Gets the parent program.
	 *
	 * @return the parent program
	 */
	//bi-directional many-to-one association to Opportunity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parentProgram")
	public Opportunity getParentProgram() {
		return parentProgram;
	}


	/**
	 * Sets the parent program.
	 *
	 * @param parentProgram the new parent program
	 */
	public void setParentProgram(Opportunity parentProgram) {
		this.parentProgram = parentProgram;
	}


	/**
	 * Gets the linked events.
	 *
	 * @return the linked events
	 */
	//bi-directional many-to-one association to Opportunity
	@OneToMany(mappedBy="parentProgram")
	public List<Opportunity> getLinkedEvents() {
		return linkedEvents;
	}


	/**
	 * Sets the linked events.
	 *
	 * @param linkedEvents the new linked events
	 */
	public void setLinkedEvents(List<Opportunity> linkedEvents) {
		this.linkedEvents = linkedEvents;
	}

	/**
	 * Adds the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity
	 */
	public Opportunity addOpportunity(Opportunity opportunity) {
		getLinkedEvents().add(opportunity);
		opportunity.setParentProgram(this);

		return opportunity;
	}


	/**
	 * Removes the opportunity.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity
	 */
	public Opportunity removeOpportunity(Opportunity opportunity) {
		getLinkedEvents().remove(opportunity);
		opportunity.setParentProgram(null);

		return opportunity;
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
	 * Gets the contact person.
	 *
	 * @return the contact person
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="contactPerson")
	public User getContactPerson() {
		return contactPerson;
	}


	/**
	 * Sets the contact person.
	 *
	 * @param contactPerson the new contact person
	 */
	public void setContactPerson(User contactPerson) {
		this.contactPerson = contactPerson;
	}


	/**
	 * Gets the created by party.
	 *
	 * @return the created by party
	 */
	//bi-directional many-to-one association to Party
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="createdByParty")
	public Party getCreatedByParty() {
		return createdByParty;
	}


	/**
	 * Sets the created by party.
	 *
	 * @param createdByParty the new created by party
	 */
	public void setCreatedByParty(Party createdByParty) {
		this.createdByParty = createdByParty;
	}


	/**
	 * Gets the opportunity cause xrefs.
	 *
	 * @return the opportunity cause xrefs
	 */
	//bi-directional many-to-one association to OpportunityCauseXref
	@OneToMany(mappedBy="opportunityBean")
	public List<OpportunityCauseXref> getOpportunityCauseXrefs() {
		return this.opportunityCauseXrefs;
	}

	


	/**
	 * Sets the opportunity cause xrefs.
	 *
	 * @param opportunityCauseXrefs the new opportunity cause xrefs
	 */
	public void setOpportunityCauseXrefs(List<OpportunityCauseXref> opportunityCauseXrefs) {
		this.opportunityCauseXrefs = opportunityCauseXrefs;
	}

	/**
	 * Adds the opportunity cause xref.
	 *
	 * @param opportunityCauseXref the opportunity cause xref
	 * @return the opportunity cause xref
	 */
	public OpportunityCauseXref addOpportunityCauseXref(OpportunityCauseXref opportunityCauseXref) {
		getOpportunityCauseXrefs().add(opportunityCauseXref);
		opportunityCauseXref.setOpportunityBean(this);

		return opportunityCauseXref;
	}

	/**
	 * Removes the opportunity cause xref.
	 *
	 * @param opportunityCauseXref the opportunity cause xref
	 * @return the opportunity cause xref
	 */
	public OpportunityCauseXref removeOpportunityCauseXref(OpportunityCauseXref opportunityCauseXref) {
		getOpportunityCauseXrefs().remove(opportunityCauseXref);
		opportunityCauseXref.setOpportunityBean(null);

		return opportunityCauseXref;
	}


	/**
	 * Gets the opportunity job types.
	 *
	 * @return the opportunity job types
	 */
	//bi-directional many-to-one association to OpportunityJobType
	@OneToMany(mappedBy="opportunityBean")
	public List<OpportunityJobType> getOpportunityJobTypes() {
		return this.opportunityJobTypes;
	}

	/**
	 * Sets the opportunity job types.
	 *
	 * @param opportunityJobTypes the new opportunity job types
	 */
	public void setOpportunityJobTypes(List<OpportunityJobType> opportunityJobTypes) {
		this.opportunityJobTypes = opportunityJobTypes;
	}

	/**
	 * Adds the opportunity job type.
	 *
	 * @param opportunityJobType the opportunity job type
	 * @return the opportunity job type
	 */
	public OpportunityJobType addOpportunityJobType(OpportunityJobType opportunityJobType) {
		getOpportunityJobTypes().add(opportunityJobType);
		opportunityJobType.setOpportunityBean(this);

		return opportunityJobType;
	}

	/**
	 * Removes the opportunity job type.
	 *
	 * @param opportunityJobType the opportunity job type
	 * @return the opportunity job type
	 */
	public OpportunityJobType removeOpportunityJobType(OpportunityJobType opportunityJobType) {
		getOpportunityJobTypes().remove(opportunityJobType);
		opportunityJobType.setOpportunityBean(null);

		return opportunityJobType;
	}


	/**
	 * Gets the party participations.
	 *
	 * @return the party participations
	 */
	//bi-directional many-to-one association to PartyParticipation
	@OneToMany(mappedBy="opportunityBean")
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
		partyParticipation.setOpportunityBean(this);

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
		partyParticipation.setOpportunityBean(null);

		return partyParticipation;
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

}