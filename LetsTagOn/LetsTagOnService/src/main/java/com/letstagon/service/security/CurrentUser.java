package com.letstagon.service.security;


import org.springframework.security.core.authority.AuthorityUtils;

import com.letstagon.dao.model.User;
// TODO: Auto-generated Javadoc

/**
 * The Class CurrentUser.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user. */
	private User user;

    /**
     * Instantiates a new current user.
     *
     * @param user the user
     */
    public CurrentUser(User user) {
        super(user.getEmailAddress(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserRole()));
        this.user = user;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return user.getId();
    }


}

