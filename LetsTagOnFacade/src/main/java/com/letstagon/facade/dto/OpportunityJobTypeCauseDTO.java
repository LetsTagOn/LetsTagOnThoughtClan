package com.letstagon.facade.dto;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityJobTypeCauseDTO.
 */
public class OpportunityJobTypeCauseDTO {

	/** The opportunity DTO. */
	private OpportunityDTO opportunityDTO;
	
	/** The cause DTO. */
	private List<CauseDTO> causeDTO;
	
	/** The job type DTO. */
	private List<JobTypeDTO> jobTypeDTO;
	
	
	/**
	 * Gets the opportunity DTO.
	 *
	 * @return the opportunity DTO
	 */
	public OpportunityDTO getOpportunityDTO() {
		return opportunityDTO;
	}
	
	/**
	 * Sets the opportunity DTO.
	 *
	 * @param opportunityDTO the new opportunity DTO
	 */
	public void setOpportunityDTO(OpportunityDTO opportunityDTO) {
		this.opportunityDTO = opportunityDTO;
	}
	
	/**
	 * Gets the cause DTO.
	 *
	 * @return the cause DTO
	 */
	public List<CauseDTO> getCauseDTO() {
		return causeDTO;
	}
	
	/**
	 * Sets the cause DTO.
	 *
	 * @param causeDTO the new cause DTO
	 */
	public void setCauseDTO(List<CauseDTO> causeDTO) {
		this.causeDTO = causeDTO;
	}
	
	/**
	 * Gets the job type DTO.
	 *
	 * @return the job type DTO
	 */
	public List<JobTypeDTO> getJobTypeDTO() {
		return jobTypeDTO;
	}
	
	/**
	 * Sets the job type DTO.
	 *
	 * @param jobTypeDTO the new job type DTO
	 */
	public void setJobTypeDTO(List<JobTypeDTO> jobTypeDTO) {
		this.jobTypeDTO = jobTypeDTO;
	}
	
	
	
	
	
	
	
}
