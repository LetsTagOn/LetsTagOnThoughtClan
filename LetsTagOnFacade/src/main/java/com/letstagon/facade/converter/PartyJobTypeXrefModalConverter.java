package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.PartyJobTypeXref;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.PartyJobTypeXrefDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyJobTypeXrefModalConverter.
 */
@Component
public class PartyJobTypeXrefModalConverter implements Converter<PartyJobTypeXref, PartyJobTypeXrefDTO> {
	
	/** The job type modal converter. */
	@Autowired
	private Converter<JobType, JobTypeDTO> jobTypeModalConverter;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public PartyJobTypeXrefDTO convert(PartyJobTypeXref source) {
		PartyJobTypeXrefDTO jobTypeXrefDTO = new PartyJobTypeXrefDTO();
		jobTypeXrefDTO.setId(source.getId());
		jobTypeXrefDTO.setJobTypeBean(jobTypeModalConverter.convert(source.getJobTypeBean()));
		jobTypeXrefDTO.setAddedOn(source.getAddedOn());
		jobTypeXrefDTO.setStatus(source.getStatus());
		jobTypeXrefDTO.setComments(source.getComments());
		return jobTypeXrefDTO;
	}

}