package com.letstagon.service;

import org.springframework.data.domain.Pageable;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.Post;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostService.
 */
public interface PostService {

	/**
	 * Gets the post by ID.
	 *
	 * @param postId the post id
	 * @return the post by ID
	 */
	Post getPostByID(long postId);

	/**
	 * Post for party.
	 *
	 * @param post the post
	 * @param postToParty the post to party
	 * @return the post
	 */
	Post postForParty(Post post, Party postToParty);

	/**
	 * Post for opportunity.
	 *
	 * @param post the post
	 * @param opportunity the opportunity
	 * @return the post
	 */
	Post postForOpportunity(Post post, Opportunity opportunity);

	/**
	 * Find all by posted for party.
	 *
	 * @param postedForParty the posted for party
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByPostedForParty(Party postedForParty, Pageable page);

	/**
	 * Find all by posted by party.
	 *
	 * @param postedByParty the posted by party
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByPostedByParty(Party postedByParty, Pageable page);

	/**
	 * Find all by posted for opportunity.
	 *
	 * @param postedForOpportunity the posted for opportunity
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllByPostedForOpportunity(Opportunity postedForOpportunity, Pageable page);

	/**
	 * Find directed and opportunity based posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findDirectedAndOpportunityBasedPostsForUser(Party partyBean, Pageable page);

	/**
	 * Find all posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllPostsForUser(Party partyBean, Pageable page);

	/**
	 * Find all posts for type.
	 *
	 * @param type the type
	 * @param page the page
	 * @return the paginated search response model
	 */
	PaginatedSearchResponseModel findAllPostsForType(String type, Pageable page);

}
