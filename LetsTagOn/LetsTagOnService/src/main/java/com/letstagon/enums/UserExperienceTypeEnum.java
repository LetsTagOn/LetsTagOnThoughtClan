package com.letstagon.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum UserExperienceTypeEnum.
 */
public enum UserExperienceTypeEnum {
	
	/** The volunteer. */
	VOLUNTEER("VLNTREXP"), 
 /** The education. */
 EDUCATION("EDUEXP"), 
 /** The professional. */
 PROFESSIONAL("PRFEXP");

	/** The experience type. */
	private final String experienceType;

	/**
	 * Gets the experience type.
	 *
	 * @return the experience type
	 */
	public String getExperienceType() {
		return experienceType;
	}

	/**
	 * Instantiates a new user experience type enum.
	 *
	 * @param experienceType the experience type
	 */
	private UserExperienceTypeEnum(String experienceType) {
		this.experienceType = experienceType;
	}

}
