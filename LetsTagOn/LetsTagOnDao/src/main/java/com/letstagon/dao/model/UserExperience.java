package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the UserExperience database table.
 * 
 */
@Entity
@Table(name="UserExperience")
@NamedQuery(name="UserExperience.findAll", query="SELECT u FROM UserExperience u")
public class UserExperience implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The description. */
	private String description;
	
	/** The end date. */
	private Date endDate;
	
	/** The location. */
	private String location;
	
	/** The start date. */
	private Date startDate;
	
	/** The title. */
	private String title;
	
	/** The type. */
	private String type;
	
	/** The acheivements. */
	private List<Acheivement> acheivements;
	
	/** The user bean. */
	private User userBean;
	
	/** The organization bean. */
	private Organization organizationBean;
	
	/** The degree. */
	private String degree;
	
	/** The course. */
	private String course;
	
	/** The cause. */
	private String cause;
	
	/** The organization name. */
	private String organizationName;

	
	/**
	 * Instantiates a new user experience.
	 */
	public UserExperience() {
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
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	 * Gets the degree.
	 *
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}


	/**
	 * Sets the degree.
	 *
	 * @param degree the new degree
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}


	/**
	 * Gets the course.
	 *
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}


	/**
	 * Sets the course.
	 *
	 * @param course the new course
	 */
	public void setCourse(String course) {
		this.course = course;
	}


	/**
	 * Gets the cause.
	 *
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}


	/**
	 * Sets the cause.
	 *
	 * @param cause the new cause
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}


	/**
	 * Gets the acheivements.
	 *
	 * @return the acheivements
	 */
	//bi-directional many-to-one association to Acheivement
	@OneToMany(mappedBy="userExperienceBean")
	public List<Acheivement> getAcheivements() {
		return this.acheivements;
	}

	/**
	 * Sets the acheivements.
	 *
	 * @param acheivements the new acheivements
	 */
	public void setAcheivements(List<Acheivement> acheivements) {
		this.acheivements = acheivements;
	}

	/**
	 * Adds the acheivement.
	 *
	 * @param acheivement the acheivement
	 * @return the acheivement
	 */
	public Acheivement addAcheivement(Acheivement acheivement) {
		getAcheivements().add(acheivement);
		acheivement.setUserExperienceBean(this);

		return acheivement;
	}

	/**
	 * Removes the acheivement.
	 *
	 * @param acheivement the acheivement
	 * @return the acheivement
	 */
	public Acheivement removeAcheivement(Acheivement acheivement) {
		getAcheivements().remove(acheivement);
		acheivement.setUserExperienceBean(null);

		return acheivement;
	}


	/**
	 * Gets the user bean.
	 *
	 * @return the user bean
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	public User getUserBean() {
		return this.userBean;
	}

	/**
	 * Sets the user bean.
	 *
	 * @param userBean the new user bean
	 */
	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}


	/**
	 * Gets the organization bean.
	 *
	 * @return the organization bean
	 */
	//bi-directional many-to-one association to Organization
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="organization")
	public Organization getOrganizationBean() {
		return this.organizationBean;
	}

	/**
	 * Sets the organization bean.
	 *
	 * @param organizationBean the new organization bean
	 */
	public void setOrganizationBean(Organization organizationBean) {
		this.organizationBean = organizationBean;
	}


	/**
	 * Gets the organization name.
	 *
	 * @return the organization name
	 */
	public String getOrganizationName() {
		return organizationName;
	}


	/**
	 * Sets the organization name.
	 *
	 * @param organizationName the new organization name
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}