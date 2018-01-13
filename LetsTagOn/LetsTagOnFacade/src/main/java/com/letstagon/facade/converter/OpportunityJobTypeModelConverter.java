package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.OpportunityCauseXref;
import com.letstagon.dao.model.OpportunityJobType;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.OpportunityCauseXrefDTO;
import com.letstagon.facade.dto.OpportunityJobTypeDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityJobTypeModelConverter.
 */
@Component
public class OpportunityJobTypeModelConverter implements Converter<OpportunityJobType, OpportunityJobTypeDTO> {

	/** The job type converter. */
	@Autowired
	private Converter<JobType, JobTypeDTO> jobTypeConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OpportunityJobTypeDTO convert(OpportunityJobType source) {

		if (source == null) {
			return null;
		}
		OpportunityJobTypeDTO dest = new OpportunityJobTypeDTO();
		dest.setId(source.getId());
		dest.setJobTypeBean(this.jobTypeConverter.convert(source.getJobTypeBean()));
		dest.setSelectionCriteria(source.getSelectionCriteria());
		dest.setNumberOfPositions(source.getNumberOfPositions());
		dest.setHours(source.getHours());
		dest.setStatus(source.getStatus());

		return dest;
	}

}