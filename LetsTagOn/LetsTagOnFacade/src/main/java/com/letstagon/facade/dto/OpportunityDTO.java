package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the OpportunityDTO core entity.
 * 
 */

public class OpportunityDTO implements Serializable {
	
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
	private OpportunityDTO parentProgram;
	
	/** The linked events. */
	private List<OpportunityDTO> linkedEvents;
	
	/** The address bean. */
	private AddressDTO addressBean;
	
	/** The contact person. */
	private UserDTO contactPerson;
	
	/** The created by. */
	private PartyDTO createdBy;
	
	/** The opportunity cause xrefs. */
	private List<OpportunityCauseXrefDTO> opportunityCauseXrefs;
	
	/** The opportunity job types. */
	private List<OpportunityJobTypeDTO> opportunityJobTypes;
	
	/** The party participations. */
	private List<PartyParticipationDTO> partyParticipations;

	
	
	/**
	 * Instantiates a new opportunity DTO.
	 */
	public OpportunityDTO() {
	}

	/**
	 * Instantiates a new opportunity DTO.
	 *
	 * @param id the id
	 */
	public OpportunityDTO(long id) {
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
	public OpportunityDTO getParentProgram() {
		return parentProgram;
	}

	/**
	 * Sets the parent program.
	 *
	 * @param parentProgram the new parent program
	 */
	public void setParentProgram(OpportunityDTO parentProgram) {
		this.parentProgram = parentProgram;
	}

	/**
	 * Gets the linked events.
	 *
	 * @return the linked events
	 */
	public List<OpportunityDTO> getLinkedEvents() {
		return linkedEvents;
	}

	/**
	 * Sets the linked events.
	 *
	 * @param linkedEvents the new linked events
	 */
	public void setLinkedEvents(List<OpportunityDTO> linkedEvents) {
		this.linkedEvents = linkedEvents;
	}

	/**
	 * Adds the linked event.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity DTO
	 */
	public OpportunityDTO addLinkedEvent(OpportunityDTO opportunity) {
		getLinkedEvents().add(opportunity);
		opportunity.setParentProgram(this);

		return opportunity;
	}

	/**
	 * Removes the linked event.
	 *
	 * @param opportunity the opportunity
	 * @return the opportunity DTO
	 */
	public OpportunityDTO removeLinkedEvent(OpportunityDTO opportunity) {
		getLinkedEvents().remove(opportunity);
		opportunity.setParentProgram(null);

		return opportunity;
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
	 * Gets the contact person.
	 *
	 * @return the contact person
	 */
	public UserDTO getContactPerson() {
		return contactPerson;
	}

	/**
	 * Sets the contact person.
	 *
	 * @param contactPerson the new contact person
	 */
	public void setContactPerson(UserDTO contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public PartyDTO getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(PartyDTO createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the opportunity cause xrefs.
	 *
	 * @return the opportunity cause xrefs
	 */
	public List<OpportunityCauseXrefDTO> getOpportunityCauseXrefs() {
		return this.opportunityCauseXrefs;
	}

	/**
	 * Sets the opportunity cause xrefs.
	 *
	 * @param opportunityCauseXrefs the new opportunity cause xrefs
	 */
	public void setOpportunityCauseXrefs(List<OpportunityCauseXrefDTO> opportunityCauseXrefs) {
		this.opportunityCauseXrefs = opportunityCauseXrefs;
	}

	/**
	 * Adds the opportunity cause xref.
	 *
	 * @param opportunityCauseXref the opportunity cause xref
	 * @return the opportunity cause xref DTO
	 */
	public OpportunityCauseXrefDTO addOpportunityCauseXref(OpportunityCauseXrefDTO opportunityCauseXref) {
		getOpportunityCauseXrefs().add(opportunityCauseXref);
		opportunityCauseXref.setOpportunityBean(this);

		return opportunityCauseXref;
	}

	/**
	 * Removes the opportunity cause xref.
	 *
	 * @param opportunityCauseXref the opportunity cause xref
	 * @return the opportunity cause xref DTO
	 */
	public OpportunityCauseXrefDTO removeOpportunityCauseXref(OpportunityCauseXrefDTO opportunityCauseXref) {
		getOpportunityCauseXrefs().remove(opportunityCauseXref);
		opportunityCauseXref.setOpportunityBean(null);

		return opportunityCauseXref;
	}

	/**
	 * Gets the opportunity job types.
	 *
	 * @return the opportunity job types
	 */
	public List<OpportunityJobTypeDTO> getOpportunityJobTypes() {
		return this.opportunityJobTypes;
	}

	/**
	 * Sets the opportunity job types.
	 *
	 * @param opportunityJobTypes the new opportunity job types
	 */
	public void setOpportunityJobTypes(List<OpportunityJobTypeDTO> opportunityJobTypes) {
		this.opportunityJobTypes = opportunityJobTypes;
	}

	/**
	 * Adds the opportunity job type.
	 *
	 * @param opportunityJobType the opportunity job type
	 * @return the opportunity job type DTO
	 */
	public OpportunityJobTypeDTO addOpportunityJobType(OpportunityJobTypeDTO opportunityJobType) {
		getOpportunityJobTypes().add(opportunityJobType);
		opportunityJobType.setOpportunityBean(this);

		return opportunityJobType;
	}

	/**
	 * Removes the opportunity job type.
	 *
	 * @param opportunityJobType the opportunity job type
	 * @return the opportunity job type DTO
	 */
	public OpportunityJobTypeDTO removeOpportunityJobType(OpportunityJobTypeDTO opportunityJobType) {
		getOpportunityJobTypes().remove(opportunityJobType);
		opportunityJobType.setOpportunityBean(null);

		return opportunityJobType;
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
		partyParticipation.setOpportunityBean(this);

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
		partyParticipation.setOpportunityBean(null);

		return partyParticipation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OpportunityDTO [id=" + id + ", dateEnd=" + dateEnd + ", dateStart=" + dateStart + ", description="
				+ description + ", latLong=" + latLong + ", name=" + name + ", type=" + type + "]";
	}

}
