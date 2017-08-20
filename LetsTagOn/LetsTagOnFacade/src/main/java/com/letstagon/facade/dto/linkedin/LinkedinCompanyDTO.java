package com.letstagon.facade.dto.linkedin;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedinCompanyDTO.
 */
public class LinkedinCompanyDTO {
	/** The organization name. */
	private String name;
	
	/** The industry. */
	private String industry;
	
	/** The size. */
	private String size;
	
	/** The type. */
	private String type;
	
	/**
	 * Gets the name.
	 *
	 * @return the organizationName
	 */
	public String getName() {
		return name;
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
	 * Gets the industry.
	 *
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * Sets the industry.
	 *
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedinCompanyDTO [name=" + name + ", industry=" + industry + ", size=" + size
				+ ", type=" + type + "]";
	}

}
