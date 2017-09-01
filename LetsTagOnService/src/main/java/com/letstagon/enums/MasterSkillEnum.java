package com.letstagon.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum MasterSkillEnum.
 */
public enum MasterSkillEnum {
	
	/** The all. */
	ALL(1, "ALL", "All"), 
 /** The animal welfare. */
 ANIMAL_WELFARE(2, "ANIMAL_WELFARE", "Animal Welfare"), 
 /** The arts. */
 ARTS(3,
			"ARTS", "Arts"), 
 /** The cultural. */
 CULTURAL(4, "CULTURAL", "Cultural"), 
 /** The sport. */
 SPORT(5, "SPORT", "Sports"), 
 /** The service. */
 SERVICE(6, "SERVICE", "Service");
	
	/** The id. */
	private final long id;
	
	/** The name. */
	private final String name;
	
	/** The description. */
	private final String description;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Instantiates a new master skill enum.
	 *
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 */
	private MasterSkillEnum(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Gets the skills.
	 *
	 * @param name the name
	 * @return the skills
	 */
	public static MasterSkillEnum getSkills(String name){
		MasterSkillEnum masterSkillEnum = null;
		for (MasterSkillEnum value : MasterSkillEnum.values()) {
			if (value.name.equalsIgnoreCase(name)) {
				masterSkillEnum = value;
				break;
			}
		}
		return masterSkillEnum;
	}
}
