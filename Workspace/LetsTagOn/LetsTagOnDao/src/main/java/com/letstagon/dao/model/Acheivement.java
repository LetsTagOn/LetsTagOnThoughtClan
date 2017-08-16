package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Acheivements database table.
 * 
 */
@Entity
@Table(name="Acheivements")
@NamedQuery(name="Acheivement.findAll", query="SELECT a FROM Acheivement a")
public class Acheivement implements Serializable {
	
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
	private UserExperience userExperienceBean;

	/**
	 * Instantiates a new acheivement.
	 */
	public Acheivement() {
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
	//bi-directional many-to-one association to UserExperience
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userExperience")
	public UserExperience getUserExperienceBean() {
		return this.userExperienceBean;
	}

	/**
	 * Sets the user experience bean.
	 *
	 * @param userExperienceBean the new user experience bean
	 */
	public void setUserExperienceBean(UserExperience userExperienceBean) {
		this.userExperienceBean = userExperienceBean;
	}

}