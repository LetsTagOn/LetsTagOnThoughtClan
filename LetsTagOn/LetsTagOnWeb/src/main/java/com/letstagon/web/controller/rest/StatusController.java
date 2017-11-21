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

import com.letstagon.dao.model.Entityy;
import com.letstagon.facade.UserStatusFacade;
import com.letstagon.facade.dto.EntityyDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserPostDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.session.LtoSessionService;

@RestController
public class StatusController {

	private static final Logger LOG = LoggerFactory.getLogger(StatusController.class);
	
	@Autowired
	private UserStatusFacade statusFacade;
	
	@Autowired
	private LtoSessionService ltoSessionService;
	
	@RequestMapping(value = "/status/save", method = RequestMethod.POST)
	public UserPostDTO saveStatus(@RequestBody UserPostDTO userPost){
		
		LOG.info("In Status controller - saveStatus");

		System.out.println("content: "+userPost.getContent());
		EntityyDTO entityDto = new EntityyDTO();
		entityDto.setUser(new UserDTO(ltoSessionService.getLoggedInUser().getId()));
		userPost.setEntity(entityDto);
		statusFacade.saveStatus(userPost);
		
		return userPost;
	}
	
	@RequestMapping(value = "/status/findAllConnectionStatus/{partyBean}", method = RequestMethod.GET)
	public PaginatedResponseDTO findAllConnectionStatus(
			@PathVariable(value = "partyBean") long partyBean,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {

		LOG.info("in findAllConnectionStatus");
		
		PartyDTO party = (partyBean == 0) ? this.ltoSessionService.findLoggedInParty(0) : new PartyDTO(partyBean);
		PaginatedResponseDTO result = statusFacade.findAllConnectionStatus(party,
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "userPost.postedOn")));
		
		return result;
	}
	
}
