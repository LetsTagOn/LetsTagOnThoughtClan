package com.letstagon.web.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.MessageManagementFacade;
import com.letstagon.facade.PartyFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.MessageDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.controller.ControllerConstants;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageRestController.
 */
@RestController
public class MessageRestController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MessageRestController.class);
	
	/** The party facade. */
	@Autowired
	PartyFacade partyFacade;

	/** The message management facade. */
	@Autowired
	private MessageManagementFacade messageManagementFacade;

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/**
	 * Gets the all conversations between parties.
	 *
	 * @param toPartyID the to party ID
	 * @param page the page
	 * @param size the size
	 * @return the all conversations between parties
	 */
	@RequestMapping(value = "/conversations/{toPartyID}", method = RequestMethod.GET)
	public PaginatedResponseDTO getAllConversationsBetweenParties(@PathVariable long toPartyID, @RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.MESSAGE_SIZE_DEFAULT_STRING) int size) {
		PaginatedResponseDTO result = null;
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			PartyDTO fromParty = ltoSessionService.findLoggedInParty(0);

			result = messageManagementFacade.getAllConversationsBetweenParties(fromParty,
					new PartyDTO(toPartyID), page, size);

		} catch (InvalidPreferenceException e) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return result;

	}
	
	/**
	 * Find conversations with party.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/conversationContactsList", method = RequestMethod.GET)
	public PaginatedResponseDTO findConversationsWithParty(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {
		PaginatedResponseDTO result = null;
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		PartyDTO fromParty = ltoSessionService.findLoggedInParty(0);

		try {
			result = messageManagementFacade.findPartyConversingWith(fromParty, page, size);
		} catch (InvalidPreferenceException e) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return result;

	}
	
	/**
	 * Find party suggestion list.
	 *
	 * @param name the name
	 * @param page the page
	 * @param size the size
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/conversation/contactsListSearch", method = RequestMethod.GET)
	public AjaxResponseDTO findPartySuggestionList(@RequestParam(name = "name", required = false) String name,@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {
		
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		PartyDTO fromParty = ltoSessionService.findLoggedInParty(0);

		try {
			responseDTO.setData(messageManagementFacade.findPartySuggestionList(fromParty, name, page, size));
		} catch (InvalidPreferenceException e) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return responseDTO;

	}
	
	
	/**
	 * Send message.
	 *
	 * @param message the message
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO sendMessage(@RequestBody MessageDTO message) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		LOG.trace("sending msj: " + message);
		try {
			message.setFromParty(ltoSessionService.findLoggedInParty(0));
			MessageDTO msj = this.messageManagementFacade.sendMessage(message);
			responseDTO.setData(msj);
		} catch (InvalidPreferenceException e) {

			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return responseDTO;
	}

	/**
	 * Delete message.
	 *
	 * @param withPartyId the with party id
	 */
	@RequestMapping(value = "/deleteConversation/withParty/{withPartyId}", method = RequestMethod.POST)
	public void deleteMessage(@PathVariable long withPartyId) {
		PartyDTO fromParty = (ltoSessionService.findLoggedInParty(0));
		PartyDTO toParty = new PartyDTO(withPartyId);
		messageManagementFacade.deleteMessageForPartyBean(fromParty,toParty);

	}
	
	/**
	 * Gets the unread messages.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the unread messages
	 * @throws InvalidPreferenceException the invalid preference exception
	 */
	@RequestMapping(value = "/getUnreadMessages", method = RequestMethod.GET)
	public PaginatedResponseDTO getUnreadMessages(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) throws InvalidPreferenceException {
		PartyDTO fromParty = (ltoSessionService.findLoggedInParty(0));
		PaginatedResponseDTO response = new PaginatedResponseDTO();
		response = messageManagementFacade.getUnreadMessages(fromParty,page,size);
		return response;
	}
	
	/**
	 * Mark message as read.
	 *
	 * @param messageId the message id
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/mark/message/read/{messageId}", method = RequestMethod.POST)
	public AjaxResponseDTO markMessageAsRead(@PathVariable long messageId) {
		AjaxResponseDTO response = new AjaxResponseDTO();
		MessageDTO message = new MessageDTO(messageId);
		response.setData(messageManagementFacade.markMessageAsRead(message));
		return response;
	}

	/**
	 * Mark all message as read for user.
	 *
	 * @param partyID the party ID
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/mark/message/read/{partyID}", method = RequestMethod.POST)
	public AjaxResponseDTO markAllMessageAsRead(@PathVariable(value = "partyID") long partyID) {

		PartyDTO partyBean = this.ltoSessionService.findLoggedInParty(partyID);
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			this.messageManagementFacade.markAllMessageAsRead(partyBean);
		} catch (InvalidPreferenceException e) {
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}
		return responseDTO;

	}
}
