package com.letstagon.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum UserTypeEnum.
 */
public enum UserTypeEnum {
	
	/** The event volunteer. */
	EVENT_VOLUNTEER("Event Volunteering", "Available for an event", 1), 
 /** The micro volunteer. */
 MICRO_VOLUNTEER("Micro Volunteering",
			"Available for less than 3 hrs", 2), 
 /** The short term volunteer. */
 SHORT_TERM_VOLUNTEER("Short Term", "Available for few days",
					3), 
 /** The volunteer. */
 VOLUNTEER("Long Term", "Available for ongoing work", 4), 
 /** The emergency response. */
 EMERGENCY_RESPONSE(
							"Emergency Response", "Help in case of a disaster", 5), 
 /** The special events. */
 SPECIAL_EVENTS("Special Events",
									"Help out for special Events", 6), 
 /** The general volunteering. */
 GENERAL_VOLUNTEERING("General Volunteering",
											"Help if needed",
											7), 
 /** The sponsor an event. */
 SPONSOR_AN_EVENT("Sponsor an Event", "Interested to sponsor", 8);

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the discription.
	 *
	 * @return the discription
	 */
	public String getDiscription() {
		return discription;
	}

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return roleId;
	}

	/** The name. */
	private final String name;
	
	/** The discription. */
	private final String discription;
	
	/** The role id. */
	private final int roleId;

	/**
	 * Instantiates a new user type enum.
	 *
	 * @param name the name
	 * @param discription the discription
	 * @param roleId the role id
	 */
	private UserTypeEnum(String name, String discription, int roleId) {
		this.name = name;
		this.discription = discription;
		this.roleId = roleId;
	}
}
