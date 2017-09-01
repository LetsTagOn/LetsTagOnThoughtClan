package com.letstagon.facade.dto;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The DTO class for the Acheivements core entity.
 * 
 */

public class AcheivementDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The description. */
	private String description;
	
	/** The status. */
	private String status;
	
	/** The title. */
	private String title;
	
	/** The type. */
	private String type;
	
	/** The user experience bean. */
	private UserExperienceDTO userExperienceBean;

	/**
	 * Instantiates a new acheivement DTO.
	 */
	public AcheivementDTO() {
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * Gets the user experience bean.
	 *
	 * @return the user experience bean
	 */
	public UserExperienceDTO getUserExperienceBean() {
		return this.userExperienceBean;
	}

	/**
	 * Sets the user experience bean.
	 *
	 * @param userExperienceBean the new user experience bean
	 */
	public void setUserExperienceBean(UserExperienceDTO userExperienceBean) {
		this.userExperienceBean = userExperienceBean;
	}

}
