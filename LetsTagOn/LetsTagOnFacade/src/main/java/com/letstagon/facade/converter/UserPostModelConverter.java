package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.UserPost;
import com.letstagon.facade.dto.UserPostDTO;

@Component
public class UserPostModelConverter implements Converter<UserPost,UserPostDTO> {
	
	@Autowired
	private UserModelConverter userModelConverter; 
	
	@Autowired
	private EntityyModelConverter entityModelConverter;
	
	@Override
	public UserPostDTO convert(UserPost source) {
		
		if(source == null){
			return null;
		}
		
		UserPostDTO userPostDTO = new UserPostDTO();
		userPostDTO.setContent(source.getContent());
		userPostDTO.setPostedOn(source.getPostedOn());
		userPostDTO.setUrl(source.getUrl());
		//userPostDTO.setEntity(entityModelConverter.convert(source.getEntity()));
		
		return userPostDTO;
	}

}
