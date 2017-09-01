package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Cause database table.
 * 
 */
@Entity
@NamedQuery(name="Cause.findAll", query="SELECT c FROM Cause c")
public class Cause implements Serializable {
	
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
	private List<OpportunityCauseXref> opportunityCauseXrefs;
	
	/** The party cause xrefs. */
	private List<PartyCauseXref> partyCauseXrefs;

	/**
	 * Instantiates a new cause.
	 */
	public Cause() {
	}


	
	/**
	 * Instantiates a new cause.
	 *
	 * @param id the id
	 */
	public Cause(long id) {
		super();
		this.id = id;
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
	//bi-directional many-to-one association to OpportunityCauseXref
	@OneToMany(mappedBy="causeBean")
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
		opportunityCauseXref.setCauseBean(this);

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
		opportunityCauseXref.setCauseBean(null);

		return opportunityCauseXref;
	}


	/**
	 * Gets the party cause xrefs.
	 *
	 * @return the party cause xrefs
	 */
	//bi-directional many-to-one association to PartyCauseXref
	@OneToMany(mappedBy="causeBean")
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
		partyCauseXref.setCauseBean(this);

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
		partyCauseXref.setCauseBean(null);

		return partyCauseXref;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cause [id=" + id + ", active=" + active + ", description=" + description + ", name=" + name + "]";
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
		Cause other = (Cause) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}