package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.Party;
import com.letstagon.facade.dto.NotificationDTO;
import com.letstagon.facade.dto.PartyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationModalConverter.
 */
@Component
public class NotificationModalConverter implements Converter<Notification, NotificationDTO> {

	/** The party converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public NotificationDTO convert(Notification source) {
		if (source == null) {
			return null;
		}

		NotificationDTO notificationDTO = new NotificationDTO();
		notificationDTO.setId(source.getId());
		notificationDTO.setContent(source.getContent());
		notificationDTO.setParams(source.getParams());
		notificationDTO.setSentOn(source.getSentOn());
		notificationDTO.setStatus(source.getStatus());
		notificationDTO.setRead(source.getIsRead());
		notificationDTO.setThumbnailUrl(source.getThumbnailUrl());
		notificationDTO.setType(source.getType());
		notificationDTO.setUrl(source.getUrl());
		notificationDTO.setPartyBean(partyConverter.convert(source.getPartyBean()));
		return notificationDTO;
	}
}
