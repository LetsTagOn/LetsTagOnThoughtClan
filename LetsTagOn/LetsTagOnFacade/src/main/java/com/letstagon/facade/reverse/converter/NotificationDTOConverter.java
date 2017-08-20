package com.letstagon.facade.reverse.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.Party;
import com.letstagon.facade.dto.NotificationDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationDTOConverter.
 */
@Component
public class NotificationDTOConverter implements Converter<NotificationDTO, Notification> {

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Notification convert(NotificationDTO source) {
		if (source == null) {
			return null;
		}
		Notification note = new Notification();
		note.setId(source.getId());
		note.setContent(source.getContent());
		note.setParams(source.getParams());
		note.setSentOn(source.getSentOn());
		note.setStatus(source.getStatus());
		note.setThumbnailUrl(source.getThumbnailUrl());
		note.setType(source.getType());
		note.setUrl(source.getUrl());
		if (source.getPartyBean() != null) {
			note.setPartyBean(this.mapper.map(source.getPartyBean(), Party.class));
		}

		return note;
	}

}
