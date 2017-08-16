package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the JobType database table.
 * 
 */
@Entity
@Table(name="JobType")
@NamedQuery(name="JobType.findAll", query="SELECT j FROM JobType j")
public class JobType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The description. */
	private String description;
	
	/** The name. */
	private String name;
	
	/** The status. */
	private Boolean status;
	
	/** The opportunity job types. */
	private List<OpportunityJobType> opportunityJobTypes;
	
	/** The party job type xrefs. */
	private List<PartyJobTypeXref> partyJobTypeXrefs;
	
	/** The party participations. */
	private List<PartyParticipation> partyParticipations;

	/**
	 * Instantiates a new job type.
	 */
	public JobType() {
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}


	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}


	/**
	 * Gets the opportunity job types.
	 *
	 * @return the opportunity job types
	 */
	//bi-directional many-to-one association to OpportunityJobType
	@OneToMany(mappedBy="jobTypeBean")
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
		opportunityJobType.setJobTypeBean(this);

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
		opportunityJobType.setJobTypeBean(null);

		return opportunityJobType;
	}


	/**
	 * Gets the party job type xrefs.
	 *
	 * @return the party job type xrefs
	 */
	//bi-directional many-to-one association to PartyJobTypeXref
	@OneToMany(mappedBy="jobTypeBean")
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
		partyJobTypeXref.setJobTypeBean(this);

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
		partyJobTypeXref.setJobTypeBean(null);

		return partyJobTypeXref;
	}


	/**
	 * Gets the party participations.
	 *
	 * @return the party participations
	 */
	//bi-directional many-to-one association to PartyParticipation
	@OneToMany(mappedBy="jobTypeBean")
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
		partyParticipation.setJobTypeBean(this);

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
		partyParticipation.setJobTypeBean(null);

		return partyParticipation;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobType [id=" + id + ", description=" + description + ", name=" + name + ", status=" + status + "]";
	}
	
	

}