package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the CauseDTO core entity.
 * 
 */

public class CauseDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The active. */
	private Boolean active;
	
	/** The description. */
	private String description;
	
	/** The name. */
	private String name;
	
	/** The opportunity cause xrefs. */
	private List<OpportunityCauseXrefDTO> opportunityCauseXrefs;
	
	/** The party cause xrefs. */
	private List<PartyCauseXrefDTO> partyCauseXrefs;

	/**
	 * Instantiates a new cause DTO.
	 */
	public CauseDTO() {
	}

	/**
	 * Instantiates a new cause DTO.
	 *
	 * @param id the id
	 */
	public CauseDTO(long id) {
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
	 * Gets the active.
	 *
	 * @return the active
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(Boolean active) {
		this.active = active;
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
		opportunityCauseXref.setCauseBean(this);

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
		opportunityCauseXref.setCauseBean(null);

		return opportunityCauseXref;
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
		partyCauseXref.setCauseBean(this);

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
		partyCauseXref.setCauseBean(null);

		return partyCauseXref;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CauseDTO [id=" + id + ", active=" + active + ", description=" + description + ", name=" + name + "]";
	}

}
