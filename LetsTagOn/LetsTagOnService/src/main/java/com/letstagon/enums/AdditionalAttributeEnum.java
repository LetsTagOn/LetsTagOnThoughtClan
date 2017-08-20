package com.letstagon.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum AdditionalAttributeEnum.
 */
public enum AdditionalAttributeEnum {
	
	/** The transport. */
	TRANSPORT(1, "TRANSPORT", "Transport"), 
 /** The dl. */
 DL(2, "DRIVING_LICENSE", "Driving License Number"), 
 /** The shirt size. */
 SHIRT_SIZE(3,
			"SHIRT_SIZE", "T-Shirt Size"), 
 /** The title. */
 TITLE(4, "TITLE", "Every Day Title"), 
 /** The car. */
 CAR(5, "CAR",
					"Type of Car"), 
 /** The volunteered before. */
 VOLUNTEERED_BEFORE(6, "VOLUNTEERED_BEFORE", "Have you volunteered before?"), 
 /** The information session. */
 INFORMATION_SESSION(7, "INFORMATION_SESSION",
							"Have you attended an information?"), 
 /** The dietary requirements. */
 DIETARY_REQUIREMENTS(8, "DIETARY_REQUIREMENTS", "Dietary Requirements"), 
 /** The medical histroy. */
 MEDICAL_HISTROY(9, "MEDICAL_HISTROY", "Medical History");

	/** The id. */
	private final int id;
	
	/** The name. */
	private final String name;
	
	/** The description. */
	private final String description;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Instantiates a new additional attribute enum.
	 *
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 */
	private AdditionalAttributeEnum(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the details.
	 *
	 * @param name the name
	 * @return the details
	 */
	public static AdditionalAttributeEnum getDetails(String name) {
		AdditionalAttributeEnum attributeEnum = null;
		for (AdditionalAttributeEnum value : AdditionalAttributeEnum.values()) {
			if (value.name.equalsIgnoreCase(name)) {
				attributeEnum = value;
				break;
			}
		}
		return attributeEnum;

	}
	
	/**
	 * Gets the details by id.
	 *
	 * @param id the id
	 * @return the details by id
	 */
	public static AdditionalAttributeEnum getDetailsById(int id) {
		AdditionalAttributeEnum attributeEnum = null;
		for (AdditionalAttributeEnum value : AdditionalAttributeEnum.values()) {
			if (value.id == id) {
				attributeEnum = value;
				break;
			}
		}
		return attributeEnum;

	}

}
