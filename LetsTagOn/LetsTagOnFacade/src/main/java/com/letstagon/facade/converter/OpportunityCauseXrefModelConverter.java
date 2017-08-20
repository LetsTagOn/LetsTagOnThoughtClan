package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.OpportunityCauseXref;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.OpportunityCauseXrefDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityCauseXrefModelConverter.
 */
@Component
public class OpportunityCauseXrefModelConverter  implements Converter<OpportunityCauseXref, OpportunityCauseXrefDTO> {

	/** The cause model converter. */
	@Autowired
	private Converter<Cause, CauseDTO> causeModelConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OpportunityCauseXrefDTO convert(OpportunityCauseXref source) {

		if (source == null) {
			return null;
		}
		OpportunityCauseXrefDTO dest = new OpportunityCauseXrefDTO();
		dest.setId(source.getId());
		dest.setCauseBean(this.causeModelConverter.convert(source.getCauseBean()));

		return dest;
	}

}

