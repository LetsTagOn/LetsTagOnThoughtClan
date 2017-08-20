package com.letstagon.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Message;
import com.letstagon.dao.model.Party;

// TODO: Auto-generated Javadoc
/**
 * The Interface MessageRepository.
 */
public interface MessageRepository extends
		PagingAndSortingRepository<Message, Long> {

	/**
	 * Gets the all conversations between parties.
	 *
	 * @param toParty the to party
	 * @param fromParty the from party
	 * @param pageable the pageable
	 * @return the all conversations between parties
	 */
	@Query("SELECT m FROM Message m where (m.toParty=:Party1 and m.fromParty=:party2 and m.sentPartyStatus != false) or"
			+ " (m.toParty=:party2 and m.receivedPartyStatus != false and m.fromParty=:Party1)ORDER BY sentTime DESC ")
	Page<Message> getAllConversationsBetweenParties(
			@Param("Party1") Party toParty, @Param("party2") Party fromParty,
			Pageable pageable);

	/**
	 * Find party conversing with.
	 *
	 * @param partyBean the party bean
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("Select p from Party p where p in (Select m1.fromParty from Message m1 where m1.toParty = :partyBean and m1.receivedPartyStatus != false) OR "
			+ "p in (Select m2.toParty from Message m2 where m2.fromParty = :partyBean and m2.sentPartyStatus != false)")
	Page<Party> findPartyConversingWith(@Param("partyBean") Party partyBean,
			Pageable pageable);

	/**
	 * Find party suggestion list.
	 *
	 * @param partyBean the party bean
	 * @param name the name
	 * @param pageReq the page req
	 * @return the page
	 */
	@Query("Select p from Party p where p in (Select c1.party2 from Connection c1 join c1.party2 p2 join c1.party1 p1  "
			+ "where  c1.party1 = :partyBean  and c1.connected = true and (c1.party2.userBean is not null AND "
			+ " LOWER(CONCAT(c1.party2.userBean.firstName,' ',c1.party2.userBean.lastName)) LIKE LOWER(CONCAT('%',:name, '%'))))"
			+ " OR "
			+ " p in (Select c1.party1 from Connection c1 join c1.party1 p1 join c1.party2 p2  where  c1.party2 = :partyBean "
			+ " and c1.connected = true and (c1.party1.userBean is not null AND "
			+ " LOWER(CONCAT(c1.party1.userBean.firstName,' ',c1.party1.userBean.lastName)) LIKE LOWER(CONCAT('%',:name, '%'))))")
	Page<Party> findPartySuggestionList(@Param("partyBean") Party partyBean,
			@Param("name")String name, Pageable pageReq);

	

	/**
	 * Find all sent messages to party.
	 *
	 * @param fromParty the from party
	 * @param toParty the to party
	 * @return the list
	 */
	@Query("select m from Message m where (m.fromParty = :fromParty and m.toParty = :toParty) OR (m.fromParty = :toParty and m.toParty = :fromParty)")
	List<Message> findAllSentMessagesToParty(@Param("fromParty") Party fromParty,@Param("toParty") Party toParty);

	/**
	 * Delete message for party bean.
	 *
	 * @param messageId the message id
	 * @return the message
	 */
	@Query("select m FROM Message m where m.id = :messageId ")
	Message deleteMessageForPartyBean(long messageId);
	
	/**
	 * Gets the all unread messages for party.
	 *
	 * @param toParty the to party
	 * @param pageable the pageable
	 * @return the all unread messages for party
	 */
	@Query("select m FROM Message m where m.toParty = :toParty and m.isRead = false and m.receivedPartyStatus != false")
	Page<Message> getAllUnreadMessagesForParty(
			@Param("toParty") Party toParty,
			Pageable pageable);

}
