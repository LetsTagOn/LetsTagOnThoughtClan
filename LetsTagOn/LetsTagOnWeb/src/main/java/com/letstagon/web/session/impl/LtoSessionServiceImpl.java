package com.letstagon.web.session.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.facade.PartyFacade;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.service.security.CurrentUser;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class LtoSessionServiceImpl.
 */
@Component
public class LtoSessionServiceImpl implements LtoSessionService {

	/** The party facade. */
	@Autowired
	private PartyFacade partyFacade;

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;
	
	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	/* (non-Javadoc)
	 * @see com.letstagon.web.session.LtoSessionService#getLoggedInUser()
	 */
	@Override
	public User getLoggedInUser() {

		SecurityContext context = SecurityContextHolder.getContext();

		// check all negative cases
		if (context == null || context.getAuthentication() == null
				|| context.getAuthentication().isAuthenticated() == false
				|| context.getAuthentication().getPrincipal() == null
				|| !(context.getAuthentication().getPrincipal() instanceof CurrentUser)) {
			return null;
		}

		CurrentUser currentUser = (CurrentUser) context.getAuthentication().getPrincipal();
		if (!(currentUser.getUser() instanceof User)) {
			return null;
		}

		return (User) currentUser.getUser();
	}

	/* (non-Javadoc)
	 * @see com.letstagon.web.session.LtoSessionService#findLoggedInParty(long)
	 */
	@Override
	public PartyDTO findLoggedInParty(long applyingPartyID) {
		PartyDTO applyingPartyDTO = new PartyDTO(applyingPartyID);
		if (applyingPartyID == 0) {
			User loggedInUser = this.getLoggedInUser();
			if (loggedInUser == null) {
				throw new IllegalStateException("User not logged in, illegal state.");
			}
			applyingPartyDTO.setUserBean(new UserDTO(loggedInUser.getId()));
			applyingPartyDTO = this.partyFacade.findPartyDTO(applyingPartyDTO);

			if (applyingPartyDTO == null) {
				Party model = new Party(new User(loggedInUser.getId()));
				this.partyRepository.save(model);
				applyingPartyDTO = new PartyDTO();
				applyingPartyDTO.setUserBean(new UserDTO(loggedInUser.getId()));
				applyingPartyDTO = this.partyFacade.findPartyDTO(applyingPartyDTO);
			}

		}
		return applyingPartyDTO;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.web.session.LtoSessionService#findPartyIdOfLoggedInUser(long)
	 */
	@Override
	public PartyDTO findPartyIdOfLoggedInUser(long userID) {
		UserDTO user = new UserDTO(userID);
		return partyFacade.findByUserBean(user);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.web.session.LtoSessionService#reload()
	 */
	@Override
	public Authentication reload() {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getLoggedInUser().getUserName());
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,
				userDetails.getPassword(), userDetails.getAuthorities()));
		return SecurityContextHolder.getContext().getAuthentication();
		
	}

}
