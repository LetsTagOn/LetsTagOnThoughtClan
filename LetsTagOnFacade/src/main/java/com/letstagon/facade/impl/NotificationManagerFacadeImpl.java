package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.NotificationManagerFacade;
import com.letstagon.facade.dto.NotificationDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.service.NotificationService;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationManagerFacadeImpl.
 */
@Component
public class NotificationManagerFacadeImpl implements NotificationManagerFacade {
	
	/** The notification converter. */
	@Autowired
	private Converter<Notification, NotificationDTO> notificationConverter;

	/** The notification reverse converter. */
	@Autowired
	private Converter<NotificationDTO, Notification> notificationReverseConverter;
	
	/** The notification service. */
	@Autowired
	private NotificationService notificationService;
	
	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.NotificationManagerFacade#createNotification(com.letstagon.facade.dto.NotificationDTO)
	 */
	@Override
	public NotificationDTO createNotification(NotificationDTO notification) {
		Notification notificationModule = notificationService
				.createNotification(this.notificationReverseConverter.convert(notification));

		return this.notificationConverter.convert(notificationModule);

	}

	/**
	 * Convert to DTO list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	public List<NotificationDTO> convertToDTOList(List<? extends Object> modelList) {
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		ArrayList<NotificationDTO> dtoList = new ArrayList<NotificationDTO>();
		for (Object model : modelList) {
			Notification notification = (Notification) model;
			dtoList.add(this.notificationConverter.convert(notification));
		}

		return dtoList;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.NotificationManagerFacade#getAllNotification(com.letstagon.facade.dto.PartyDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO getAllNotification(PartyDTO partyBean, int page, int size) {
		Party party = mapper.map(partyBean, Party.class);
		PaginatedSearchResponseModel result = notificationService.getAllNotification(party, page, size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.NotificationManagerFacade#deleteNotification(com.letstagon.facade.dto.NotificationDTO)
	 */
	@Override
	public void deleteNotification(NotificationDTO notification) {
		Notification notification2 = mapper.map(notification, Notification.class);
		notificationService.deleteNotification(notification2);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.NotificationManagerFacade#getAllUnreadNotification(com.letstagon.facade.dto.PartyDTO, java.lang.Boolean, int, int)
	 */
	@Override
	public PaginatedResponseDTO getAllUnreadNotification(PartyDTO partyBean, Boolean read, int page, int size) {
		Party party = mapper.map(partyBean, Party.class);
		PaginatedSearchResponseModel result = notificationService.getAllUnreadNotification(party, read, page, size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.NotificationManagerFacade#markNotificationAsRead(long)
	 */
	@Override
	public NotificationDTO markNotificationAsRead(long notificationID) {
		Notification notification = notificationService.markNotificationAsRead(notificationID);
		return this.notificationConverter.convert(notification);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.NotificationManagerFacade#deleteAllNotificationForUser(com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public void deleteAllNotificationForUser(PartyDTO partyBean) {
		Party party = mapper.map(partyBean, Party.class);
		this.notificationService.deleteAllNotificationForUser(party);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.NotificationManagerFacade#markAllAsRead(com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public void markAllAsRead(PartyDTO party) throws InvalidPreferenceException {
	
		Party partyBean = mapper.map(party, Party.class);
		notificationService.markAllNotificationAsRead(partyBean);
	}

}
