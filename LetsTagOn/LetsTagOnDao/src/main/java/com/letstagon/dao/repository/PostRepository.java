package com.letstagon.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.Post;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostRepository.
 */
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

	/**
	 * Find all by type.
	 *
	 * @param type the type
	 * @param page the page
	 * @return the page
	 */
	Page<Post> findAllByType(@Param("type") String type, Pageable page);

	/**
	 * Find all by posted for party.
	 *
	 * @param postedForParty the posted for party
	 * @param page the page
	 * @return the page
	 */
	Page<Post> findAllByPostedForParty(@Param("postedForParty") Party postedForParty, Pageable page);

	/**
	 * Find all by posted by party.
	 *
	 * @param postedByParty the posted by party
	 * @param page the page
	 * @return the page
	 */
	Page<Post> findAllByPostedByParty(@Param("postedByParty") Party postedByParty, Pageable page);

	/**
	 * Find all by posted for opportunity.
	 *
	 * @param postedForOpportunity the posted for opportunity
	 * @param page the page
	 * @return the page
	 */
	Page<Post> findAllByPostedForOpportunity(@Param("postedForOpportunity") Opportunity postedForOpportunity,
			Pageable page);

	/**
	 * Find directed and opportunity based posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @return the page
	 */
	@Query("SELECT ps FROM Post ps WHERE (ps.postedByParty=:partyBean) OR (ps.postedForParty=:partyBean) OR "
			+ " (ps.postedForOpportunity IS NOT NULL AND ps.postedForOpportunity.createdByParty=:partyBean) OR "
			+ "(ps.postedForOpportunity IS NOT NULL AND ps.postedForOpportunity IN (SELECT pp.opportunityBean"
			+ " FROM PartyParticipation pp where pp.partyBean=:partyBean))")

	Page<Post> findDirectedAndOpportunityBasedPostsForUser(@Param("partyBean") Party partyBean, Pageable page);

	/**
	 * Find all posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @return the page
	 */
	@Query("SELECT ps FROM Post ps WHERE (ps.postedByParty=:partyBean) OR (ps.postedForParty=:partyBean) OR "
			+ "(ps.postedForOpportunity IS NOT NULL AND ps.postedForOpportunity.createdByParty=:partyBean) OR "
			+ "(ps.postedForOpportunity IS NOT NULL AND ps.postedForOpportunity IN (SELECT pp.opportunityBean"
			+ " FROM PartyParticipation pp where pp.partyBean=:partyBean)) OR ( ps.type='POST_TO_PUBLIC' AND ( ps.postedByParty in "
			+ " (SELECT c1.party2 FROM Connection c1 where c1.connected= true AND c1.party1=:partyBean ) OR ps.postedByParty in"
			+ " (SELECT c2.party1 FROM Connection c2 where c2.connected= true AND c2.party2=:partyBean) " + "))")
	Page<Post> findAllPostsForUser(@Param("partyBean") Party partyBean, Pageable page);

}
