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

import com.letstagon.dao.model.Entityy;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.UserPost;
import com.letstagon.facade.UserStatusFacade;
import com.letstagon.facade.dto.EntityyDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserPostDTO;
import com.letstagon.service.UserStatusService;

@Component
public class UserStatusFacadeImpl implements UserStatusFacade {

	@Autowired
	private UserStatusService statusService;
	
	@Autowired 
	private DozerBeanMapper mapper;
	
	@Autowired
	private Converter<Entityy, EntityyDTO> entityConverter;

	@Override
	public UserPostDTO saveStatus(UserPostDTO userPostDTO) {

		UserPost userPost = mapper.map(userPostDTO, UserPost.class);
		userPost = statusService.saveStatus(userPost);
		userPostDTO = mapper.map(userPost, UserPostDTO.class);

		return userPostDTO;
	}

	@Override
	public PaginatedResponseDTO findAllConnectionStatus(PartyDTO party, Pageable pageable) {
		Party partyModel = mapper.map(party, Party.class);
		PaginatedSearchResponseModel result = statusService.findAllConnectionStatus(partyModel, pageable);
		return new PaginatedResponseDTO(pageable.getPageNumber(), pageable.getPageSize(), result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}
	
	private List<EntityyDTO> convertToDTOList(List<? extends Object> searchResult) {

		if (CollectionUtils.isEmpty(searchResult)) {
			return Collections.emptyList();
		}

		List<EntityyDTO> dtoList = new ArrayList<EntityyDTO>();

		for (Object model : searchResult) {
			dtoList.add(entityConverter.convert((Entityy) model));
		}

		return dtoList;
	}

}
