package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Entityy;
import com.letstagon.facade.dto.EntityyDTO;
import com.letstagon.facade.dto.UserPostDTO;

@Component
public class EntityyModelConverter implements Converter<Entityy, EntityyDTO> {

	@Autowired
	private UserModelConverter userModelConverter; 
	
	@Autowired
	private UserPostModelConverter UserPostModelConverter;
	
	@Override
	public EntityyDTO convert(Entityy source) {
		if(source == null){
			return null;
		}
		
		EntityyDTO entityyDTO = new EntityyDTO();
		entityyDTO.setId(source.getId());
		entityyDTO.setUser(userModelConverter.convert(source.getUser()));
		UserPostDTO userPostDTO = UserPostModelConverter.convert(source.getUserPost());
		userPostDTO.setEntity(entityyDTO);
		entityyDTO.setUserPost(userPostDTO);
		return entityyDTO;
	}

}
