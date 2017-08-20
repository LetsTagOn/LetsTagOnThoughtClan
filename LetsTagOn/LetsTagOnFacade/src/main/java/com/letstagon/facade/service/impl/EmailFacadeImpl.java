package com.letstagon.facade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.letstagon.exception.InvalidTokenException;
import com.letstagon.exception.LinkExpiredException;
import com.letstagon.facade.service.EmailFacade;
import com.letstagon.service.EmailService;
import com.letstagon.service.ForgotPasswordService;
import com.letstagon.service.ResetPasswordService;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailFacadeImpl.
 */
@Component
public class EmailFacadeImpl implements EmailFacade {

	/** The email service. */
	@Autowired
	private EmailService emailService;
	
	/** The forgot pwd service. */
	@Autowired
	private ForgotPasswordService forgotPwdService;
	
	/** The reset pwd service. */
	@Autowired
	private ResetPasswordService resetPwdService;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.service.EmailFacade#sendLTONotification(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendLTONotification(String name, String email) throws Exception {
		// calling email service to send email for user after registration
		emailService.sendLTONotification(name, email);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.service.EmailFacade#forgotPassword(java.lang.String)
	 */
	// throw exception related to finding the specific user.
	@Override
	public void forgotPassword(String userName) throws UsernameNotFoundException, Exception {
		// to send forgot password link to the user
		forgotPwdService.forgotPassword(userName);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.service.EmailFacade#resetPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void resetPassword(String user, String token, String password)
			throws LinkExpiredException, InvalidTokenException {
		// to send reset password notification to the user
		resetPwdService.resetPassword(user, token, password);
	}

}
