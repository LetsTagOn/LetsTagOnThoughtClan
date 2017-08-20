package com.letstagon.facade.reverse.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.OpportunityJobType;
import com.letstagon.facade.dto.OpportunityJobTypeDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityJobTypeDTOConverter.
 */
@Component
public class OpportunityJobTypeDTOConverter implements Converter<OpportunityJobTypeDTO, OpportunityJobType> {

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OpportunityJobType convert(OpportunityJobTypeDTO source) {

		if (source == null) {
			return null;
		}

		OpportunityJobType dest = this.mapper.map(source, OpportunityJobType.class);

		return dest;
	}

}
