package com.letstagon.facade.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.JobType;
import com.letstagon.facade.dto.JobTypeDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class JobTypeModelConverter.
 */
@Component
public class JobTypeModelConverter implements Converter<JobType, JobTypeDTO> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public JobTypeDTO convert(JobType model) {

		if (model == null)
			return null;

		JobTypeDTO jobTypeDTO = new JobTypeDTO();
		jobTypeDTO.setId(model.getId());
		jobTypeDTO.setName(model.getName());
		jobTypeDTO.setDescription(model.getDescription());
		jobTypeDTO.setStatus(model.getStatus());
		return jobTypeDTO;
	}

}
