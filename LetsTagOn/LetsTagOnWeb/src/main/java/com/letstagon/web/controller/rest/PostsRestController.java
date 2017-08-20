package com.letstagon.web.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.facade.PostFacade;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PostDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class PostsRestController.
 */
@RestController
public class PostsRestController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PostsRestController.class);

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/** The post facade. */
	@Autowired
	private PostFacade postFacade;

	/**
	 * Gets the post by ID.
	 *
	 * @param postId the post id
	 * @return the post by ID
	 */
	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	PostDTO getPostByID(@PathVariable(value = "postId") long postId) {

		LOG.trace("Get post with id : " + postId);

		PostDTO post = postFacade.getPostByID(postId);
		return post;
	}

	/**
	 * Post for party.
	 *
	 * @param post the post
	 * @param postToParty the post to party
	 * @return the post DTO
	 */
	@RequestMapping(value = "/post/party/{postToParty}", method = RequestMethod.POST)
	PostDTO postForParty(@RequestBody PostDTO post, @PathVariable(value = "postToParty") long postToParty) {

		LOG.trace("Posting to party : " + postToParty);

		post.setPostedByParty(this.ltoSessionService.findLoggedInParty(0));
		post = postFacade.postForParty(post, new PartyDTO(postToParty));

		return post;
	}

	/**
	 * Post for opportunity.
	 *
	 * @param post the post
	 * @param postedForOpportunity the posted for opportunity
	 * @return the post DTO
	 */
	@RequestMapping(value = "/post/opportunity/{postedForOpportunity}", method = RequestMethod.POST)
	PostDTO postForOpportunity(@RequestBody PostDTO post,
			@PathVariable(value = "postedForOpportunity") long postedForOpportunity) {

		LOG.trace("Posting for opportunity : " + postedForOpportunity);
		post.setPostedByParty(this.ltoSessionService.findLoggedInParty(0));
		post = postFacade.postForOpportunity(post, new OpportunityDTO(postedForOpportunity));

		return post;
	}

	/**
	 * Find all by posted for party.
	 *
	 * @param postedForParty the posted for party
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/post/findAllByPostedForParty/{postedForParty}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByPostedForParty(@PathVariable(value = "postedForParty") long postedForParty,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PaginatedResponseDTO result = postFacade.findAllByPostedForParty(new PartyDTO(postedForParty),
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "postedOn")));
		return result;
	}

	/**
	 * Find all by posted by party.
	 *
	 * @param postedByParty the posted by party
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/post/findAllByPostedByParty/{postedByParty}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByPostedByParty(@PathVariable(value = "postedByParty") long postedByParty,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PaginatedResponseDTO result = postFacade.findAllByPostedByParty(new PartyDTO(postedByParty),
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "postedOn")));
		return result;
	}

	/**
	 * Find all by posted for opportunity.
	 *
	 * @param postedForOpportunity the posted for opportunity
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/post/findAllByPostedForOpportunity/{postedForOpportunity}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllByPostedForOpportunity(
			@PathVariable(value = "postedForOpportunity") long postedForOpportunity,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PaginatedResponseDTO result = postFacade.findAllByPostedForOpportunity(new OpportunityDTO(postedForOpportunity),
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "postedOn")));
		return result;
	}

	/**
	 * Find directed and opportunity based posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/post/findDirectedAndOpportunityBasedPostsForUser/{partyBean}", method = RequestMethod.GET)
	public PaginatedResponseDTO findDirectedAndOpportunityBasedPostsForUser(
			@PathVariable(value = "partyBean") long partyBean,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PartyDTO party = (partyBean == 0) ? this.ltoSessionService.findLoggedInParty(0) : new PartyDTO(partyBean);
		PaginatedResponseDTO result = postFacade.findDirectedAndOpportunityBasedPostsForUser(party,
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "postedOn")));
		return result;
	}

	/**
	 * Find all posts for user.
	 *
	 * @param partyBean the party bean
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/post/findAllPostsForUser/{partyBean}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllPostsForUser(@PathVariable(value = "partyBean") long partyBean,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PartyDTO party = (partyBean == 0) ? this.ltoSessionService.findLoggedInParty(0) : new PartyDTO(partyBean);
		PaginatedResponseDTO result = postFacade.findAllPostsForUser(party,
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "postedOn")));
		return result;
	}

	/**
	 * Find all posts for type.
	 *
	 * @param type the type
	 * @param page the page
	 * @param size the size
	 * @return the paginated response DTO
	 */
	@RequestMapping(value = "/post/findAllPostsForType/{type}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllPostsForType(@PathVariable(value = "type") String type,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		PaginatedResponseDTO result = postFacade.findAllPostsForType(type,
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "postedOn")));
		return result;
	}

}
