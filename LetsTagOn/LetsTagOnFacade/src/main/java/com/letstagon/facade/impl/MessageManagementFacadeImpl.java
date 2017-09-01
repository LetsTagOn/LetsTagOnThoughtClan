package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Message;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.MessageManagementFacade;
import com.letstagon.facade.converter.PartyModelConverter;
import com.letstagon.facade.dto.MessageDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.service.MessageService;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageManagementFacadeImpl.
 */
@Component
public class MessageManagementFacadeImpl implements MessageManagementFacade {
	
	/** The message converter. */
	@Autowired
	private Converter<Message, MessageDTO> messageConverter;
	
	/** The dozer bean mapper. */
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/** The messaging service. */
	@Autowired
	private MessageService messagingService;
	
	/** The party model converter. */
	@Autowired
	private PartyModelConverter partyModelConverter;

	/**
	 * Convert to DTO list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	public List<MessageDTO> convertToDTOList(List<? extends Object> modelList) {
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		ArrayList<MessageDTO> dtoList = new ArrayList<MessageDTO>();
		for (Object model : modelList) {
			Message msg = (Message) model;
			dtoList.add(this.messageConverter.convert(msg));
		}

		return dtoList;

	}
	
	/**
	 * Convert party list to DTO list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	public List<PartyDTO> convertPartyListToDTOList(List<? extends Object> modelList) {
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		ArrayList<PartyDTO> dtoList = new ArrayList<PartyDTO>();
		for (Object model : modelList) {
			Party party = (Party) model;
			dtoList.add(this.partyModelConverter.convert(party));
		}

		return dtoList;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.MessageManagementFacade#sendMessage(com.letstagon.facade.dto.MessageDTO)
	 */
	@Override
	public MessageDTO sendMessage(MessageDTO messageDTO) throws InvalidPreferenceException {
		if (messageDTO.getToParty() == null && messageDTO.getMessageText() != null
				|| messageDTO.getToParty() == null && messageDTO.getMessageText() == null) {
			throw new InvalidPreferenceException("No receiver party is there");
		}
		Message message = messagingService.sendMessage(this.dozerBeanMapper.map(messageDTO, Message.class));

		return this.messageConverter.convert(message);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.MessageManagementFacade#getAllConversationsBetweenParties(com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.PartyDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO getAllConversationsBetweenParties(PartyDTO fromParty, PartyDTO toParty, int page,
			int size) throws InvalidPreferenceException {
		if (toParty == null || fromParty == null) {
			throw new InvalidPreferenceException("No sender or receiver party is there");
		}
		Party party2 = dozerBeanMapper.map(toParty, Party.class);
		Party party1 = dozerBeanMapper.map(fromParty, Party.class);
		PaginatedSearchResponseModel result = messagingService.getAllConversationsBetweenParties(party1, party2,
				page, size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.MessageManagementFacade#findPartyConversingWith(com.letstagon.facade.dto.PartyDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO findPartyConversingWith(PartyDTO fromParty,int page,
			int size) throws InvalidPreferenceException {
		if (fromParty == null) {
			throw new InvalidPreferenceException("No sender or receiver party is there");
		}
		Party party1 = dozerBeanMapper.map(fromParty, Party.class);
	
		PaginatedSearchResponseModel result = messagingService.findPartyConversingWith(party1,
				page, size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertPartyListToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.MessageManagementFacade#findPartySuggestionList(com.letstagon.facade.dto.PartyDTO, java.lang.String, int, int)
	 */
	@Override
	public PaginatedResponseDTO findPartySuggestionList(PartyDTO fromParty,
			String name,int page,int size) throws InvalidPreferenceException {
		if (fromParty == null) {
			throw new InvalidPreferenceException("invalid prefrence exception");
		}
		Party party = dozerBeanMapper.map(fromParty, Party.class);
		PaginatedSearchResponseModel result = messagingService.findPartySuggestionList(party, name,page,size);		
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertPartyListToDTOList(result.getSearchResult()));
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.facade.MessageManagementFacade#deleteMessageForPartyBean(com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public void deleteMessageForPartyBean(PartyDTO fromParty,PartyDTO toParty) {

		Party party1 = dozerBeanMapper.map(fromParty, Party.class);
		Party party2 = dozerBeanMapper.map(toParty, Party.class);
		this.messagingService.deleteMessageForPartyBean(party1,party2);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.MessageManagementFacade#getUnreadMessages(com.letstagon.facade.dto.PartyDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO getUnreadMessages(PartyDTO fromParty, int page,
			int size) throws InvalidPreferenceException {
		// Method to get unreadMessages
		Party party1 = dozerBeanMapper.map(fromParty, Party.class);
		PaginatedSearchResponseModel result = messagingService.getUnreadMessages(party1, page, size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.MessageManagementFacade#markMessageAsRead(com.letstagon.facade.dto.MessageDTO)
	 */
	@Override
	public MessageDTO markMessageAsRead(MessageDTO message) {
		Message mess = dozerBeanMapper.map(message, Message.class);
		mess = messagingService.markMessageAsRead(mess);
		return messageConverter.convert(mess);

	}

}
