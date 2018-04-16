package com.letstagon.service.impl;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyParticipation;
import com.letstagon.dao.repository.OpportunityRepository;
import com.letstagon.dao.repository.PartyParticipationRepository;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.service.PartyParticipationService;
import com.letstagon.service.PartyService;
import com.letstagon.service.event.OppApplicationStatusChangeEvent;
import com.letstagon.service.event.OppAttendanceChangeEvent;
import com.letstagon.service.event.OppFeedbackChangeEvent;
import com.letstagon.service.event.OpportunityApplicationApplyEvent;
import com.letstagon.service.event.OpportunityApplicationSentEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyParticipationServiceImpl.
 */
@Component
public class PartyParticipationServiceImpl implements PartyParticipationService {

	/** The party service. */
	@Autowired
	private PartyService partyService;

	/** The party participation repository. */
	@Autowired
	private PartyParticipationRepository partyParticipationRepository;

	/** The opportunity repository. */
	@Autowired
	private OpportunityRepository opportunityRepository;

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;

	/** The event publisher. */
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#applyForOpportunity(com.letstagon.dao.model.Opportunity, com.letstagon.dao.model.Party, com.letstagon.dao.model.JobType)
	 */
	@Override
	public PartyParticipation applyForOpportunity(Opportunity opportunity, Party applyingParty, JobType jobType) {

		applyingParty = this.partyService.findParty(applyingParty);

		PartyParticipation partyParticipation = this.findPartyParticipation(opportunity, applyingParty, jobType);

		if (partyParticipation == null) {
			partyParticipation = new PartyParticipation();
			partyParticipation.setJobTypeBean(jobType);
			partyParticipation.setOpportunityBean(opportunity);
			partyParticipation.setPartyBean(applyingParty);
			partyParticipation = this.partyParticipationRepository.save(partyParticipation);
		}

		Date date = new Date();
		Opportunity opp = opportunityRepository.findOne(partyParticipation.getOpportunityBean().getId());
		OpportunityApplicationSentEvent applicationSentEvent = new OpportunityApplicationSentEvent(partyParticipation, opp,
				partyRepository.findOne(partyParticipation.getPartyBean().getId()), date);
		OpportunityApplicationApplyEvent applicationApplyEvent = new OpportunityApplicationApplyEvent(partyParticipation, opp, applyingParty, date);
		eventPublisher.publishEvent(applicationSentEvent);
		eventPublisher.publishEvent(applicationApplyEvent);

		return partyParticipation;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findPartyParticipation(com.letstagon.dao.model.Opportunity, com.letstagon.dao.model.Party, com.letstagon.dao.model.JobType)
	 */
	@Override
	public PartyParticipation findPartyParticipation(Opportunity opportunity, Party applyingParty, JobType jobType) {
		if (jobType == null || opportunity == null || applyingParty == null || opportunity.getId() <= 0
				|| jobType.getId() <= 0 || applyingParty.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		PartyParticipation partyParticipation = this.partyParticipationRepository
				.findOneByJobTypeBeanAndOpportunityBeanAndPartyBean(jobType, opportunity, applyingParty);

		return partyParticipation;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#removeApplicationForOpportunity(com.letstagon.dao.model.Opportunity, com.letstagon.dao.model.Party, com.letstagon.dao.model.JobType)
	 */
	@Override
	public PartyParticipation removeApplicationForOpportunity(Opportunity opportunity, Party appliedParty,
			JobType jobType) {

		PartyParticipation partyParticipation = findPartyParticipation(opportunity, appliedParty, jobType);

		if (partyParticipation != null) {
			this.partyParticipationRepository.delete(partyParticipation);
		}

		return partyParticipation;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#modifyApplicationStatus(com.letstagon.dao.model.PartyParticipation, boolean)
	 */
	@Override
	public PartyParticipation modifyApplicationStatus(PartyParticipation participation, boolean accept) {

		if (participation == null || participation.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not a valid participation object.");
		}

		PartyParticipation partyParticipation = this.partyParticipationRepository.findOne(participation.getId());

		if (partyParticipation == null) {
			throw new InvalidParameterException("Invalid parameter, not a valid participation object.");
		}

		partyParticipation.setStatus(accept);
		partyParticipation = this.partyParticipationRepository.save(partyParticipation);

		OppApplicationStatusChangeEvent applicationStatusChangeEvent = new OppApplicationStatusChangeEvent(
				partyParticipation, opportunityRepository.findOne(partyParticipation.getOpportunityBean().getId()),
				partyRepository.findOne(partyParticipation.getPartyBean().getId()), new Date());
		eventPublisher.publishEvent(applicationStatusChangeEvent);

		return partyParticipation;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#modifyAttendance(com.letstagon.dao.model.PartyParticipation, boolean)
	 */
	@Override
	public PartyParticipation modifyAttendance(PartyParticipation participation, boolean attended) {

		if (participation == null || participation.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not a valid participation object.");
		}

		PartyParticipation partyParticipation = this.partyParticipationRepository.findOne(participation.getId());

		if (partyParticipation == null) {
			throw new InvalidParameterException("Invalid parameter, not a valid participation object.");
		}

		partyParticipation.setAttendance(attended);
		partyParticipation = this.partyParticipationRepository.save(partyParticipation);
		OppAttendanceChangeEvent oppAttendanceChangeEvent = new OppAttendanceChangeEvent(partyParticipation,
				opportunityRepository.findOne(partyParticipation.getOpportunityBean().getId()),
				partyRepository.findOne(partyParticipation.getPartyBean().getId()), new Date());
		eventPublisher.publishEvent(oppAttendanceChangeEvent);
		return partyParticipation;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#modifyFeedbackAndRating(com.letstagon.dao.model.PartyParticipation)
	 */
	@Override
	public PartyParticipation modifyFeedbackAndRating(PartyParticipation participation) {

		if (participation == null || participation.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not a valid participation object.");
		}

		PartyParticipation partyParticipation = this.partyParticipationRepository.findOne(participation.getId());

		if (partyParticipation == null) {
			throw new InvalidParameterException("Invalid parameter, not a valid participation object.");
		}

		partyParticipation.setRating(participation.getRating());
		partyParticipation.setReview(participation.getReview());

		this.partyParticipationRepository.save(partyParticipation);
		OppFeedbackChangeEvent oppFeedbackChangeEvent = new OppFeedbackChangeEvent(partyParticipation,
				opportunityRepository.findOne(partyParticipation.getOpportunityBean().getId()),
				partyRepository.findOne(partyParticipation.getPartyBean().getId()), new Date());
		eventPublisher.publishEvent(oppFeedbackChangeEvent);

		// re-calculate score
		this.partyParticipationRepository.setAggregateRatingOfParty(partyParticipation.getPartyBean());

		return partyParticipation;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findPartyParticipations(com.letstagon.dao.model.Opportunity, com.letstagon.dao.model.Party)
	 */
	@Override
	public List<PartyParticipation> findPartyParticipations(Opportunity opportunity, Party applyingParty) {
		if (opportunity == null || applyingParty == null || opportunity.getId() <= 0 || applyingParty.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		List<PartyParticipation> partyParticipations = this.partyParticipationRepository
				.findAllByOpportunityBeanAndPartyBean(opportunity, applyingParty);

		return partyParticipations;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findAllByOpportunityBean(com.letstagon.dao.model.Opportunity, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByOpportunityBean(Opportunity opportunityBean, Pageable pageableData) {
		if (opportunityBean == null || opportunityBean.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		Page<PartyParticipation> result = this.partyParticipationRepository.findAllByOpportunityBean(opportunityBean,
				pageableData);

		return new PaginatedSearchResponseModel(result.getContent(), pageableData.getPageNumber(), result.getSize(),
				result.getTotalElements());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findAllByOpportunityBeanAndStatus(com.letstagon.dao.model.Opportunity, java.lang.Boolean, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByOpportunityBeanAndStatus(Opportunity opportunityBean, Boolean status,
			Pageable pageableData) {
		if (opportunityBean == null || opportunityBean.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		Page<PartyParticipation> result = this.partyParticipationRepository
				.findAllByOpportunityBeanAndStatus(opportunityBean, status, pageableData);

		return new PaginatedSearchResponseModel(result.getContent(), pageableData.getPageNumber(), result.getSize(),
				result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findAllByOpportunityBeanAndAttendance(com.letstagon.dao.model.Opportunity, java.lang.Boolean, org.springframework.data.domain.Pageable)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByOpportunityBeanAndAttendance(Opportunity opportunityBean,
			Boolean attendance, Pageable pageableData) {
		if (opportunityBean == null || opportunityBean.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		Page<PartyParticipation> result = this.partyParticipationRepository
				.findAllByOpportunityBeanAndAttendance(opportunityBean, attendance, pageableData);

		return new PaginatedSearchResponseModel(result.getContent(), pageableData.getPageNumber(), result.getSize(),
				result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findAllByPartyBeanAndStatus(com.letstagon.dao.model.Party, java.lang.Boolean, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByPartyBeanAndStatus(Party partyModel, Boolean status,
			PageRequest pageRequest) {

		if (partyModel == null || partyModel.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		Page<PartyParticipation> result = this.partyParticipationRepository.findAllByPartyBeanAndStatus(partyModel,
				status, pageRequest);

		return new PaginatedSearchResponseModel(result.getContent(), pageRequest.getPageNumber(), result.getSize(),
				result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findAllByPartyBeanAndStatusAndAfterDateStart(com.letstagon.dao.model.Party, java.lang.Boolean, java.util.Date, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByPartyBeanAndStatusAndAfterDateStart(Party partyModel, Boolean status,
			Date dateStart, PageRequest pageRequest) {

		if (partyModel == null || partyModel.getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		Page<PartyParticipation> result = this.partyParticipationRepository
				.findAllByPartyBeanAndStatusAndStartDateAfter(partyModel, status, dateStart, pageRequest);

		return new PaginatedSearchResponseModel(result.getContent(), pageRequest.getPageNumber(), result.getSize(),
				result.getTotalElements());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findOpportunitiesListOfParty(com.letstagon.dao.model.PartyParticipation)
	 */
	@Override
	public List<PartyParticipation> findOpportunitiesListOfParty(PartyParticipation partyParticipation) {
		return partyParticipationRepository.findByPartyBeanAndStatusAndAttendance(partyParticipation.getPartyBean(),
				partyParticipation.getStatus(), partyParticipation.getAttendance());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findByPartyBean(com.letstagon.dao.model.Party)
	 */
	@Override
	public PartyParticipation findByPartyBean(Party partyBean) {
		return partyParticipationRepository.findByPartyBean(partyBean);
	}

}
