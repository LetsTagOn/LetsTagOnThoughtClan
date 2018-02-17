package com.letstagon.dao.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.letstagon.dao.model.Notification;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Interface NotificationRepository.
 */
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {
	
	/**
	 * Find by party bean.
	 *
	 * @param partyBean the party bean
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Notification> findByPartyBean(@Param("partyBean") Party partyBean, Pageable pageable);

	/**
	 * Find by party bean.
	 *
	 * @param partyBean the party bean
	 * @return the list
	 */
	public List<Notification> findByPartyBean(@Param("partyBean") Party partyBean);

	/**
	 * Find unread notification by party bean and is read.
	 *
	 * @param partyBean the party bean
	 * @param isRead the is read
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Notification> findUnreadNotificationByPartyBeanAndIsRead(@Param("partyBean") Party partyBean,
			   @Param("isRead") Boolean isRead, Pageable pageable);
	
	/**
	 * Mark all notification as read for user.
	 *
	 * @param partyBean the party bean
	 * @param isRead the is read
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Notification notification set notification.isRead =:isRead, notification.status =:status where notification.partyBean=:partyBean")
	public void markAllNotificationAsReadForUser (@Param("partyBean") Party partyBean,  @Param("isRead") Boolean isRead, @Param("status") Boolean status);
}
