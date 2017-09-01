package com.letstagon.facade.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Cause;
import com.letstagon.facade.dto.CauseDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class CauseModalConverter.
 */
@Component
public class CauseModalConverter implements Converter<Cause, CauseDTO> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public CauseDTO convert(Cause model) {
		if (model == null)
			return null;

		CauseDTO causeDTO = new CauseDTO();
		causeDTO.setId(model.getId());
		causeDTO.setName(model.getName());
		causeDTO.setDescription(model.getDescription());
		causeDTO.setActive(model.getActive());
		return causeDTO;
	}

}
