package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.Post;
import com.letstagon.facade.PostFacade;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PostDTO;
import com.letstagon.service.PostService;

// TODO: Auto-generated Javadoc
/**
 * The Class PostFacadeImpl.
 */
@Component
public class PostFacadeImpl implements PostFacade {

	/** The post service. */
	@Autowired
	private PostService postService;

	/** The post converter. */
	@Autowired
	private Converter<Post, PostDTO> postConverter;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#getPostByID(long)
	 */
	@Override
	public PostDTO getPostByID(long postId) {

		return this.postConverter.convert(this.postService.getPostByID(postId));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#postForParty(com.letstagon.facade.dto.PostDTO, com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public PostDTO postForParty(PostDTO postDTO, PartyDTO postToParty) {

		Post model = this.mapper.map(postDTO, Post.class);
		Party partyModel = mapper.map(postToParty, Party.class);

		Post post = this.postService.postForParty(model, partyModel);

		return this.postConverter.convert(post);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#postForOpportunity(com.letstagon.facade.dto.PostDTO, com.letstagon.facade.dto.OpportunityDTO)
	 */
	@Override
	public PostDTO postForOpportunity(PostDTO postDTO, OpportunityDTO opportunity) {

		Post model = this.mapper.map(postDTO, Post.class);
		Opportunity oppModel = mapper.map(opportunity, Opportunity.class);

		Post post = this.postService.postForOpportunity(model, oppModel);

		return this.postConverter.convert(post);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#findAllByPostedForParty(com.letstagon.facade.dto.PartyDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByPostedForParty(PartyDTO postedForParty, Pageable page) {
		Party partyModel = mapper.map(postedForParty, Party.class);
		PaginatedSearchResponseModel result = this.postService.findAllByPostedForParty(partyModel, page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/**
	 * Convert to DTO list.
	 *
	 * @param searchResult the search result
	 * @return the list
	 */
	private List<PostDTO> convertToDTOList(List<? extends Object> searchResult) {

		if (CollectionUtils.isEmpty(searchResult)) {
			return Collections.emptyList();
		}

		List<PostDTO> dtoList = new ArrayList<PostDTO>();

		for (Object model : searchResult) {
			dtoList.add(this.postConverter.convert((Post) model));
		}

		return dtoList;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#findAllByPostedByParty(com.letstagon.facade.dto.PartyDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByPostedByParty(PartyDTO postedByParty, Pageable page) {
		Party partyModel = mapper.map(postedByParty, Party.class);
		PaginatedSearchResponseModel result = this.postService.findAllByPostedByParty(partyModel, page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#findAllByPostedForOpportunity(com.letstagon.facade.dto.OpportunityDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByPostedForOpportunity(OpportunityDTO postedForOpportunity, Pageable page) {
		Opportunity opportunityModel = this.mapper.map(postedForOpportunity, Opportunity.class);
		PaginatedSearchResponseModel result = this.postService.findAllByPostedForOpportunity(opportunityModel, page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#findDirectedAndOpportunityBasedPostsForUser(com.letstagon.facade.dto.PartyDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findDirectedAndOpportunityBasedPostsForUser(PartyDTO partyBean, Pageable page) {
		Party partyModel = mapper.map(partyBean, Party.class);
		PaginatedSearchResponseModel result = this.postService.findDirectedAndOpportunityBasedPostsForUser(partyModel,
				page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#findAllPostsForUser(com.letstagon.facade.dto.PartyDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllPostsForUser(PartyDTO partyBean, Pageable page) {
		Party partyModel = mapper.map(partyBean, Party.class);
		PaginatedSearchResponseModel result = this.postService.findDirectedAndOpportunityBasedPostsForUser(partyModel,
				page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PostFacade#findAllPostsForType(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllPostsForType(String type, Pageable page) {
		PaginatedSearchResponseModel result = this.postService.findAllPostsForType(type, page);
		return new PaginatedResponseDTO(page.getPageNumber(), page.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

}
