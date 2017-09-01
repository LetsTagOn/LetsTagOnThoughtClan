package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.PartyCauseXref;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.PartyCauseXrefDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyCauseXrefModalConverter.
 */
@Component
public class PartyCauseXrefModalConverter implements Converter<PartyCauseXref, PartyCauseXrefDTO> {
	
	/** The cause modal converter. */
	@Autowired
	private Converter<Cause, CauseDTO> causeModalConverter;
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public PartyCauseXrefDTO convert(PartyCauseXref source) {
		PartyCauseXrefDTO causeXrefDTO = new PartyCauseXrefDTO();
		causeXrefDTO.setId(source.getId());
		causeXrefDTO.setCauseBean(causeModalConverter.convert(source.getCauseBean()));
		causeXrefDTO.setAddedOn(source.getAddedOn());
		causeXrefDTO.setStatus(source.isStatus());
		return causeXrefDTO;
	}

}
