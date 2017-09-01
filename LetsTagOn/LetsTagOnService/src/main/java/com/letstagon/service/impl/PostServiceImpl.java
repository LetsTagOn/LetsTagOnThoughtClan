package com.letstagon.service.impl;

import java.security.InvalidParameterException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.enums.PostTypeEnum;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.Post;
import com.letstagon.dao.repository.PostRepository;
import com.letstagon.service.PostService;

// TODO: Auto-generated Javadoc
/**
 * The Class PostServiceImpl.
 */
@Component
public class PostServiceImpl implements PostService {

	/** The post repository. */
	@Autowired
	private PostRepository postRepository;

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#getPostByID(long)
	 */
	@Override
	public Post getPostByID(long postId) {
		return postRepository.findOne(postId);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#postForParty(com.letstagon.dao.model.Post, com.letstagon.dao.model.Party)
	 */
	@Override
	public Post postForParty(Post post, Party postToParty) {

		if (post == null || post.getPostedByParty() == null || StringUtils.isBlank(post.getContent())) {
			throw new InvalidParameterException("Insufficient and invalid input.");
		}

		post.setPostedForParty(postToParty);
		post.setType(PostTypeEnum.POST_TO_PARTY.toString());
		post.setPostedOn(new Date());
		post = this.postRepository.save(post);

		return post;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#postForOpportunity(com.letstagon.dao.model.Post, com.letstagon.dao.model.Opportunity)
	 */
	@Override
	public Post postForOpportunity(Post post, Opportunity opportunity) {

		if (post == null || StringUtils.isBlank(post.getContent())) {
			throw new InvalidParameterException("Insufficient and invalid input.");
		}

		post.setPostedForOpportunity(opportunity);
		post.setType(PostTypeEnum.POST_TO_OPP.toString());
		post.setPostedOn(new Date());
		post = this.postRepository.save(post);

		return post;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#findAllByPostedForParty(com.letstagon.dao.model.Party, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByPostedForParty(Party postedForParty, Pageable page) {

		Page<Post> result = this.postRepository.findAllByPostedForParty(postedForParty, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#findAllByPostedByParty(com.letstagon.dao.model.Party, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByPostedByParty(Party postedByParty, Pageable page) {

		Page<Post> result = this.postRepository.findAllByPostedByParty(postedByParty, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#findAllByPostedForOpportunity(com.letstagon.dao.model.Opportunity, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByPostedForOpportunity(Opportunity postedForOpportunity, Pageable page) {

		Page<Post> result = this.postRepository.findAllByPostedForOpportunity(postedForOpportunity, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#findDirectedAndOpportunityBasedPostsForUser(com.letstagon.dao.model.Party, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findDirectedAndOpportunityBasedPostsForUser(Party partyBean, Pageable page) {

		Page<Post> result = this.postRepository.findDirectedAndOpportunityBasedPostsForUser(partyBean, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#findAllPostsForUser(com.letstagon.dao.model.Party, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllPostsForUser(Party partyBean, Pageable page) {

		Page<Post> result = this.postRepository.findAllPostsForUser(partyBean, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PostService#findAllPostsForType(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllPostsForType(String type, Pageable page) {

		Page<Post> result = this.postRepository.findAllByType(type, page);

		return new PaginatedSearchResponseModel(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalElements());

	}

}
