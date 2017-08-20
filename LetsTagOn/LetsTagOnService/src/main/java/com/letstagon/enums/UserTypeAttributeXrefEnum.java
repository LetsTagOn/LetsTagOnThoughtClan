package com.letstagon.enums;


// TODO: Auto-generated Javadoc
/**
 * The Enum UserTypeAttributeXrefEnum.
 */
public enum UserTypeAttributeXrefEnum { 
	
	/** The event volunteer. */
	EVENT_VOLUNTEER(1,"1", "2"), 
 /** The micro volunteer. */
 MICRO_VOLUNTEER(2,"2",
			"3"), 
 /** The short term volunteer. */
 SHORT_TERM_VOLUNTEER(3,"3", "4"), 
 /** The volunteer. */
 VOLUNTEER(4,"4", "5"), 
 /** The emergency response. */
 EMERGENCY_RESPONSE(5,
							"5", "6"), 
 /** The special events. */
 SPECIAL_EVENTS(6,"6",
									"7"), 
 /** The general volunteering. */
 GENERAL_VOLUNTEERING(7,"7",
											"8"), 
 /** The sponsor an event. */
 SPONSOR_AN_EVENT(8,"8", "9");
	  
  	/** The id. */
  	private final long id;
	  
  	/** The additional attribute. */
  	private final String[] additionalAttribute;
	  
	  /**
  	 * Instantiates a new user type attribute xref enum.
  	 *
  	 * @param id the id
  	 * @param additionalAttribute the additional attribute
  	 */
  	private UserTypeAttributeXrefEnum(int id, String...additionalAttribute) {
			this.id = id;
			this.additionalAttribute = additionalAttribute;
		}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the additional attribute.
	 *
	 * @return the additional attribute
	 */
	public String[] getAdditionalAttribute() {
		return additionalAttribute;
	}
	
	/**
	 * Gets the details.
	 *
	 * @param id the id
	 * @return the details
	 */
	public static UserTypeAttributeXrefEnum getDetails(long id) {
		UserTypeAttributeXrefEnum attributeEnum = null;
		for (UserTypeAttributeXrefEnum value : UserTypeAttributeXrefEnum.values()) {
			if (value.id == id) {
				attributeEnum = value;
				break;
			}
		}
		return attributeEnum;
	}
	} 
