package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyParticipation;
import com.letstagon.facade.PartyParticipationFacade;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PartyParticipationDTO;
import com.letstagon.service.PartyParticipationService;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyParticipationFacadeImpl.
 */
@Component
public class PartyParticipationFacadeImpl implements PartyParticipationFacade {

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The party participation service. */
	@Autowired
	private PartyParticipationService partyParticipationService;

	/** The participation model converter. */
	@Autowired
	private Converter<PartyParticipation, PartyParticipationDTO> participationModelConverter;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#applyForOpportunity(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.JobTypeDTO)
	 */
	@Override
	public PartyParticipationDTO applyForOpportunity(OpportunityDTO opportunity, PartyDTO applyingPartyDTO,
			JobTypeDTO jobType) {
		Opportunity opportunityModel = this.mapper.map(opportunity, Opportunity.class);
		Party partyModel = this.mapper.map(applyingPartyDTO, Party.class);
		JobType jobTypeModel = this.mapper.map(jobType, JobType.class);
		PartyParticipation participation = partyParticipationService.applyForOpportunity(opportunityModel, partyModel,
				jobTypeModel);

		return participationModelConverter.convert(participation);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#removeApplicationForOpportunity(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.JobTypeDTO)
	 */
	@Override
	public PartyParticipationDTO removeApplicationForOpportunity(OpportunityDTO opportunity, PartyDTO appliedPartyDTO,
			JobTypeDTO jobType) {
		Opportunity opportunityModel = this.mapper.map(opportunity, Opportunity.class);
		Party partyModel = this.mapper.map(appliedPartyDTO, Party.class);
		JobType jobTypeModel = this.mapper.map(jobType, JobType.class);
		PartyParticipation participation = partyParticipationService.removeApplicationForOpportunity(opportunityModel,
				partyModel, jobTypeModel);

		return participationModelConverter.convert(participation);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#modifyApplicationStatus(com.letstagon.facade.dto.PartyParticipationDTO, boolean)
	 */
	@Override
	public PartyParticipationDTO modifyApplicationStatus(PartyParticipationDTO participation, boolean accept) {

		PartyParticipation participationModel = new PartyParticipation(participation.getId());
		participationModel = partyParticipationService.modifyApplicationStatus(participationModel, accept);

		return participationModelConverter.convert(participationModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#modifyAttendance(com.letstagon.facade.dto.PartyParticipationDTO, boolean)
	 */
	@Override
	public PartyParticipationDTO modifyAttendance(PartyParticipationDTO participation, boolean attended) {

		PartyParticipation participationModel = new PartyParticipation(participation.getId());
		participationModel = partyParticipationService.modifyAttendance(participationModel, attended);

		return participationModelConverter.convert(participationModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#modifyFeedbackAndRating(com.letstagon.facade.dto.PartyParticipationDTO)
	 */
	@Override
	public PartyParticipationDTO modifyFeedbackAndRating(PartyParticipationDTO participation) {

		PartyParticipation participationModel = new PartyParticipation(participation.getId());
		participationModel.setRating(participation.getRating());
		participationModel.setReview(participation.getReview());
		participationModel = partyParticipationService.modifyFeedbackAndRating(participationModel);

		return participationModelConverter.convert(participationModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#findPartyParticipationDTO(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.JobTypeDTO)
	 */
	@Override
	public PartyParticipationDTO findPartyParticipationDTO(OpportunityDTO opportunity, PartyDTO applyingPartyDTO,
			JobTypeDTO jobType) {

		Opportunity opportunityModel = this.mapper.map(opportunity, Opportunity.class);
		Party partyModel = this.mapper.map(applyingPartyDTO, Party.class);
		JobType jobTypeModel = this.mapper.map(jobType, JobType.class);
		PartyParticipation participation = partyParticipationService.findPartyParticipation(opportunityModel,
				partyModel, jobTypeModel);

		return participationModelConverter.convert(participation);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#findPartyParticipationDTO(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public List<PartyParticipationDTO> findPartyParticipationDTO(OpportunityDTO opportunityDTO,
			PartyDTO loggedInParty) {

		Opportunity opportunityModel = this.mapper.map(opportunityDTO, Opportunity.class);
		Party partyModel = this.mapper.map(loggedInParty, Party.class);

		List<PartyParticipation> participationList = partyParticipationService.findPartyParticipations(opportunityModel,
				partyModel);

		return this.convertParticipationList(participationList);
	}

	/**
	 * Convert participation list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	private List<PartyParticipationDTO> convertParticipationList(List<PartyParticipation> modelList) {

		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		List<PartyParticipationDTO> list = new ArrayList<PartyParticipationDTO>();
		for (PartyParticipation model : modelList) {
			list.add(this.participationModelConverter.convert(model));
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#findAllByOpportunityBean(com.letstagon.facade.dto.OpportunityDTO, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByOpportunityBean(OpportunityDTO opportunityBean, Pageable pageableData) {

		Opportunity opportunityModel = this.mapper.map(opportunityBean, Opportunity.class);

		PaginatedSearchResponseModel result = partyParticipationService.findAllByOpportunityBean(opportunityModel,
				pageableData);

		return new PaginatedResponseDTO(result.getPage(), pageableData.getPageSize(), result.getTotalCount(),
				this.convertParticipationListPaginated(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#findAllByOpportunityBeanAndStatus(com.letstagon.facade.dto.OpportunityDTO, java.lang.Boolean, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByOpportunityBeanAndStatus(OpportunityDTO opportunityBean, Boolean status,
			Pageable pageableData) {

		Opportunity opportunityModel = this.mapper.map(opportunityBean, Opportunity.class);

		PaginatedSearchResponseModel result = partyParticipationService
				.findAllByOpportunityBeanAndStatus(opportunityModel, status, pageableData);

		return new PaginatedResponseDTO(result.getPage(), pageableData.getPageSize(), result.getTotalCount(),
				this.convertParticipationListPaginated(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#findAllByOpportunityBeanAndAttendance(com.letstagon.facade.dto.OpportunityDTO, java.lang.Boolean, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedResponseDTO findAllByOpportunityBeanAndAttendance(OpportunityDTO opportunityBean,
			Boolean attendance, Pageable pageableData) {

		Opportunity opportunityModel = this.mapper.map(opportunityBean, Opportunity.class);

		PaginatedSearchResponseModel result = partyParticipationService
				.findAllByOpportunityBeanAndAttendance(opportunityModel, attendance, pageableData);

		return new PaginatedResponseDTO(result.getPage(), pageableData.getPageSize(), result.getTotalCount(),
				this.convertParticipationListPaginated(result.getSearchResult()));
	}

	/**
	 * Convert participation list paginated.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	private List<PartyParticipationDTO> convertParticipationListPaginated(List<? extends Object> modelList) {

		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		List<PartyParticipationDTO> list = new ArrayList<PartyParticipationDTO>();
		for (Object model : modelList) {
			list.add(this.participationModelConverter.convert((PartyParticipation) model));
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#findAllByPartyBeanAndStatus(com.letstagon.facade.dto.PartyDTO, java.lang.Boolean, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public PaginatedResponseDTO findAllByPartyBeanAndStatus(PartyDTO applyingPartyDTO, Boolean status,
			PageRequest pageRequest) {

		Party partyModel = this.mapper.map(applyingPartyDTO, Party.class);
		PaginatedSearchResponseModel result = partyParticipationService.findAllByPartyBeanAndStatus(partyModel, status,
				pageRequest);

		return new PaginatedResponseDTO(result.getPage(), pageRequest.getPageSize(), result.getTotalCount(),
				this.convertParticipationListPaginated(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.PartyParticipationFacade#findAllByPartyBeanAndStatusAndAfterDateStart(com.letstagon.facade.dto.PartyDTO, java.lang.Boolean, java.util.Date, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public PaginatedResponseDTO findAllByPartyBeanAndStatusAndAfterDateStart(PartyDTO applyingPartyDTO, Boolean status,
			Date dateStart, PageRequest pageRequest) {

		Party partyModel = this.mapper.map(applyingPartyDTO, Party.class);
		PaginatedSearchResponseModel result = partyParticipationService
				.findAllByPartyBeanAndStatusAndAfterDateStart(partyModel, status, dateStart, pageRequest);

		return new PaginatedResponseDTO(result.getPage(), pageRequest.getPageSize(), result.getTotalCount(),
				this.convertParticipationListPaginated(result.getSearchResult()));
	}

}
