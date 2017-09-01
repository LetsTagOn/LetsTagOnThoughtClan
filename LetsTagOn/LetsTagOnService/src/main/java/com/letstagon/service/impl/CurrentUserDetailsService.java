package com.letstagon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.User;
import com.letstagon.service.UserService;
import com.letstagon.service.security.CurrentUser;

// TODO: Auto-generated Javadoc
/**
 * The Class CurrentUserDetailsService.
 */
@Component
public class CurrentUserDetailsService implements UserDetailsService {
    
	/** The Constant LOG. */
	private static final Logger LOG=LoggerFactory.getLogger(CurrentUserDetailsService.class);
	
	/** The user service. */
	@Autowired
	private UserService userService;


    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public CurrentUser loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
    	LOG.info("Attempting to login user with username : "+userName);
        User user = userService.getUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with userName=%s was not found", userName)));      
        return new CurrentUser(user);
    }
}
