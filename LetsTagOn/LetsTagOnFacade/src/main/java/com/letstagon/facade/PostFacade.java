package com.letstagon.facade;

import org.springframework.data.domain.Pageable;

import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PostDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostFacade.
 */
public interface PostFacade {

	/**
	 * Gets the post by ID.
	 *
	 * @param postId the post id
	 * @return the post by ID
	 */
	PostDTO getPostByID(long postId);

	/**
	 * Post for party.
	 *
	 * @param post the post
	 * @param postToParty the post to party
	 * @return the post DTO
	 */
	PostDTO postForParty(PostDTO post, PartyDTO postToParty);

	/**
	 * Post for opportunity.
	 *
	 * @param post the post
	 * @param opportunity the opportunity
	 * @return the post DTO
	 */
	PostDTO postForOpportunity(PostDTO post, OpportunityDTO opportunity);

	/**
	 * Find all by posted for party.
	 *
	 * @param postedForParty the posted for party
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByPostedForParty(PartyDTO postedForParty, Pageable page);

	/**
	 * Find all by posted by party.
	 *
	 * @param postedByParty the posted by party
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByPostedByParty(PartyDTO postedByParty, Pageable page);

	/**
	 * Find all by posted for opportunity.
	 *
	 * @param postedForOpportunity the posted for opportunity
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllByPostedForOpportunity(OpportunityDTO postedForOpportunity, Pageable page);

	/**
	 * Find directed and opportunity based posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findDirectedAndOpportunityBasedPostsForUser(PartyDTO partyBean, Pageable page);

	/**
	 * Find all posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllPostsForUser(PartyDTO partyBean, Pageable page);

	/**
	 * Find all posts for type.
	 *
	 * @param type the type
	 * @param page the page
	 * @return the paginated response DTO
	 */
	PaginatedResponseDTO findAllPostsForType(String type, Pageable page);

}
