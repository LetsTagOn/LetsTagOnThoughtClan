package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Message;
import com.letstagon.dao.model.Party;
import com.letstagon.facade.dto.MessageDTO;
import com.letstagon.facade.dto.PartyDTO;
// TODO: Auto-generated Javadoc

/**
 * The Class MessageModelConverter.
 */
@Component
public class MessageModelConverter implements Converter<Message, MessageDTO> {
	
	/** The party converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public MessageDTO convert(Message source) {
		if (source == null) {
			return null;
		}
		MessageDTO msgDTO = new MessageDTO(source.getId());
		msgDTO.setId(source.getId());
		msgDTO.setAttachmentUrl(source.getAttachmentUrl());
		msgDTO.setIsRead(source.getIsRead());
		msgDTO.setMessageText(source.getmessageText());
		msgDTO.setReadTime(source.getReadTime());
		msgDTO.setReceivedPartyStatus(source.getReceivedPartyStatus());
		msgDTO.setSentPartyStatus(source.getSentPartyStatus());
		msgDTO.setSentTime(source.getSentTime());
		msgDTO.setUrl(source.getUrl());
		
		
		msgDTO.setToParty(partyConverter.convert(source.getToParty()));
		msgDTO.setFromParty(partyConverter.convert(source.getFromParty()));
		return msgDTO;
	}

}
