package com.letstagon.facade;

import org.springframework.data.domain.Pageable;

import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserPostDTO;

public interface UserStatusFacade {

	public UserPostDTO saveStatus(UserPostDTO userPost);
	
	public PaginatedResponseDTO findAllConnectionStatus(PartyDTO party, Pageable pageable);
	
}
