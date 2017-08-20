package com.letstagon.web.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.NotificationManagerFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.controller.ControllerConstants;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationRestController.
 */
@RestController
public class NotificationRestController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OpportunityManagementRestController.class);

	/** The notification manager facade. */
	@Autowired
	private NotificationManagerFacade notificationManagerFacade;

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/**
	 * Mark notification as read.
	 *
	 * @param id the id
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/notification/notificationId/{id}/markNotification", method = RequestMethod.PUT)
	public AjaxResponseDTO markNotificationAsRead(@PathVariable(value="id") long id) {
		LOG.trace("mark notificatiopn as read:" + id);
		return new AjaxResponseDTO(this.notificationManagerFacade.markNotificationAsRead(id));
	}

	/**
	 * Gets the all unread notification.
	 *
	 * @param userID the user ID
	 * @param isRead the is read
	 * @param page the page
	 * @param size the size
	 * @return the all unread notification
	 */
	@RequestMapping(value = "/notification/party/{userID}/isRead", method = RequestMethod.GET)
	public PaginatedResponseDTO getAllUnreadNotification(@PathVariable(value = "userID") long userID,
			@RequestParam(name = "isRead",required = false, defaultValue = "false") Boolean isRead,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {
		PartyDTO partyBean = this.ltoSessionService.findPartyIdOfLoggedInUser(ltoSessionService.getLoggedInUser().getId());
		
		return notificationManagerFacade.getAllUnreadNotification(partyBean, isRead, page, size);

	}

	/**
	 * Gets the all notification.
	 *
	 * @param id the id
	 * @param page the page
	 * @param size the size
	 * @return the all notification
	 */
	@RequestMapping(value = "/notification/party/{id}", method = RequestMethod.GET)
	public PaginatedResponseDTO getAllNotification(@PathVariable(value = "id") long id,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PartyDTO partyBean = this.ltoSessionService.findPartyIdOfLoggedInUser(ltoSessionService.getLoggedInUser().getId());
		return notificationManagerFacade.getAllNotification(partyBean, page, size);

	}

	/**
	 * Delete all notification for user.
	 *
	 * @param partyID the party ID
	 */
	@RequestMapping(value = "/notification/party/{partyID}", method = RequestMethod.DELETE)
	public void deleteAllNotificationForUser(@PathVariable(value = "partyID") long partyID) {

		PartyDTO partyBean = this.ltoSessionService.findLoggedInParty(partyID);
		this.notificationManagerFacade.deleteAllNotificationForUser(partyBean);

	}
	
	/**
	 * Mark all notification as read for user.
	 *
	 * @param partyID the party ID
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/notification/markAll/read/party/{partyID}", method = RequestMethod.POST)
	public AjaxResponseDTO markAllNotificationAsReadForUser(@PathVariable(value = "partyID") long partyID) {

		PartyDTO partyBean = this.ltoSessionService.findLoggedInParty(partyID);
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			this.notificationManagerFacade.markAllAsRead(partyBean);
		} catch (InvalidPreferenceException e) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return responseDTO;

	}

}
