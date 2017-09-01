package com.letstagon.service.impl;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.repository.NotificationRepository;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.service.NotificationService;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationServiceImpl.
 */
@Component
public class NotificationServiceImpl implements NotificationService {

	/** The notification repository. */
	@Autowired
	private NotificationRepository notificationRepository;

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#createNotification(com.letstagon.dao.model.Notification)
	 */
	@Override
	public Notification createNotification(Notification notification) {

		if (notification.getPartyBean() == null) {
			throw new InvalidParameterException("No prefrences are found");
		}
		return this.notificationRepository.save(notification);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#deleteNotification(com.letstagon.dao.model.Notification)
	 */
	@Override
	public void deleteNotification(Notification notification) {
		// TODO Auto-generated method stub
		notificationRepository.delete(notification);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#markNotificationAsRead(long)
	 */
	@Override
	public Notification markNotificationAsRead(long notificationID) {
		// TODO Auto-generated method stub
		Notification notification = notificationRepository.findOne(notificationID);
		notification.setIsRead(true);
		return this.notificationRepository.save(notification);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#deleteAllNotificationForUser(com.letstagon.dao.model.Party)
	 */
	@Override
	public void deleteAllNotificationForUser(Party partyBean) {

		List<Notification> resultList = notificationRepository.findByPartyBean(partyBean);
		notificationRepository.delete(resultList);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#markAllNotificationAsReadForUser(com.letstagon.dao.model.Party, java.lang.Boolean)
	 */
	@Override
	public void markAllNotificationAsReadForUser(Party partyBean, Boolean isRead) {
		
	 notificationRepository.markAllNotificationAsReadForUser(partyBean, isRead);

	}

	

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#getAllNotification(com.letstagon.dao.model.Party, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel getAllNotification(Party partyBean, int page, int size) {
		Pageable pageReq = new PageRequest(page, size);

		Page<Notification> resultList = notificationRepository.findByPartyBean(partyBean, pageReq);

		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#getAllUnreadNotification(com.letstagon.dao.model.Party, java.lang.Boolean, int, int)
	 */
	@Override
	public PaginatedSearchResponseModel getAllUnreadNotification(Party partyBean, Boolean read, int page, int size) {
		Pageable pageReq = new PageRequest(page, size);

		Page<Notification> resultList = notificationRepository.findUnreadNotificationByPartyBeanAndIsRead(partyBean, read,
				pageReq);

		return new PaginatedSearchResponseModel(resultList.getContent(), page, resultList.getSize(),
				resultList.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.NotificationService#markAllNotificationAsRead(com.letstagon.dao.model.Party)
	 */
	@Override
	public void markAllNotificationAsRead(Party party) throws InvalidPreferenceException {
		if(party == null){
			throw new InvalidPreferenceException("Required party details not found");
		}
		notificationRepository.markAllNotificationAsReadForUser(party, true);
		
	}

}
