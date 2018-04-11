package com.letstagon.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Message;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.repository.LtoCustomRepository;
import com.letstagon.dao.repository.MessageRepository;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.service.MessageService;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageServiceImpl.
 */
@Component
public class MessageServiceImpl implements MessageService {
	
	/** The messaging repository. */
	@Autowired
	private MessageRepository messagingRepository;
	
	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;
	
	/** The lto custom repository. */
	@Autowired
	private LtoCustomRepository ltoCustomRepository;

	/* (non-Javadoc)
	 * @see com.letstagon.service.MessageService#sendMessage(com.letstagon.dao.model.Message)
	 */
	@Override
	public Message sendMessage(Message msg) throws InvalidPreferenceException {
		if (msg.getToParty() == null && msg.getmessageText() != null
				|| msg.getToParty() == null && msg.getmessageText() == null) {
			throw new InvalidPreferenceException("No receiver party is there");
		}
		msg.setSentTime(new Date());
		//Default status of recieved and sent party status is true
		msg.setReceivedPartyStatus(Boolean.TRUE);
		msg.setSentPartyStatus(Boolean.TRUE);
		msg.setIsRead(Boolean.FALSE);
		return messagingRepository.save(msg);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.MessageService#getAllConversationsBetweenParties(com.letstagon.dao.model.Party, com.letstagon.dao.model.Party, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel getAllConversationsBetweenParties(Party fromParty, Party toParty,
			int page, int size) throws InvalidPreferenceException {
		Pageable pageReq = new PageRequest(page, size);
		if (fromParty == null || toParty == null) {
			throw new InvalidPreferenceException("invalid prefrence exception");
		}
		Page<Message> resultList = messagingRepository.getAllConversationsBetweenParties(toParty, fromParty, pageReq);
		
		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.MessageService#findPartyConversingWith(com.letstagon.dao.model.Party, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel findPartyConversingWith(Party fromParty,
			int page, int size) throws InvalidPreferenceException {
		Pageable pageReq = new PageRequest(page, size);
		if (fromParty == null) {
			throw new InvalidPreferenceException("invalid prefrence exception");
		}
		Page<Party> resultList = messagingRepository.findPartyConversingWith(fromParty, pageReq);
		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.MessageService#findPartySuggestionList(com.letstagon.dao.model.Party, java.lang.String, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel findPartySuggestionList(Party fromParty,
			String name,int page,int size) throws InvalidPreferenceException {
		Pageable pageReq = new PageRequest(page, size);
		if (fromParty == null) {
			throw new InvalidPreferenceException("invalid preference exception");
		}
		Page<Party> resultList = messagingRepository.findPartySuggestionList(fromParty, name,pageReq);
		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.MessageService#deleteMessageForPartyBean(com.letstagon.dao.model.Party, com.letstagon.dao.model.Party)
	 */
	@Override
	public void deleteMessageForPartyBean(Party partyBean,Party toParty) {

		List<Message> conversations = messagingRepository.findAllSentMessagesToParty(partyBean, toParty);
		if(conversations != null){
			for (Message message : conversations) {
				if(message.getFromParty().equals(partyBean) && message.getToParty().equals(toParty) ){
					message.setSentPartyStatus(Boolean.FALSE);
				}
				else if(message.getFromParty().equals(toParty) && message.getToParty().equals(partyBean)){
					message.setReceivedPartyStatus(Boolean.FALSE);
				}			
				messagingRepository.save(message);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.MessageService#getUnreadMessages(com.letstagon.dao.model.Party, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel getUnreadMessages(Party fromParty,
			int page, int size) throws InvalidPreferenceException {
		
		// Method to get unread messages for logged in user
		Pageable pageReq = new PageRequest(page, size);
		if (fromParty == null) {
			throw new InvalidPreferenceException("invalid preference exception");
		}

		Page<Message> resultList = messagingRepository.getAllUnreadMessagesForParty(fromParty, pageReq);
		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.MessageService#markMessageAsRead(com.letstagon.dao.model.Message)
	 */
	@Override
	public Message markMessageAsRead(Message message) {
		Message mess = messagingRepository.findOne(message.getId());
		mess.setIsRead(Boolean.TRUE);
		mess.setReadTime(new Date());
		return messagingRepository.save(mess);
		 
	}

	@Override
	public void markAllNotificationAsRead(Party party) throws InvalidPreferenceException {
		if(party == null){
			throw new InvalidPreferenceException("Required party details not found");
		}
		Date readTime = Calendar.getInstance().getTime()
		messagingRepository.markAllNotificationAsReadForUser(party, true, readTime);
	}

}
