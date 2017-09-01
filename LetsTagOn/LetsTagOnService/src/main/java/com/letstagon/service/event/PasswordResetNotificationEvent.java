package com.letstagon.service.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Class PasswordResetNotificationEvent.
 */
public class PasswordResetNotificationEvent extends ApplicationEvent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user. */
	private Party user;
	
	/** The time. */
	private Date time;

	/**
	 * Instantiates a new password reset notification event.
	 *
	 * @param source the source
	 */
	public PasswordResetNotificationEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new password reset notification event.
	 *
	 * @param source the source
	 * @param user the user
	 * @param time the time
	 */
	public PasswordResetNotificationEvent(Object source, Party user, Date time) {
		this(source);
		this.user = user;
		this.time = time;

	}

	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString() {
		return "message printed event raised with: ("
				+ user.getUserBean().getName() + " ) and (" + time + ")";
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Party getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Party user) {
		this.user = user;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(Date time) {
		this.time = time;
	}

}
