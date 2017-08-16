package com.letstagon.facade.reverse.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.OpportunityCauseXref;
import com.letstagon.facade.dto.OpportunityCauseXrefDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityCauseXrefDTOConverter.
 */
@Component
public class OpportunityCauseXrefDTOConverter implements Converter<OpportunityCauseXrefDTO, OpportunityCauseXref> {

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OpportunityCauseXref convert(OpportunityCauseXrefDTO source) {

		if (source == null) {
			return null;
		}

		OpportunityCauseXref dest = this.mapper.map(source, OpportunityCauseXref.class);

		return dest;
	}

}
