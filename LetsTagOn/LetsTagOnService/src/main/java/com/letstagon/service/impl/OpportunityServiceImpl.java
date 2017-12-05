package com.letstagon.service.impl;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityCauseXref;
import com.letstagon.dao.model.OpportunityJobType;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.AddressRepository;
import com.letstagon.dao.repository.OpportunityCauseRepository;
import com.letstagon.dao.repository.OpportunityJobTypeRepository;
import com.letstagon.dao.repository.OpportunityRepository;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.service.OpportunityService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityServiceImpl.
 */
@Component
public class OpportunityServiceImpl implements OpportunityService {

	/** The opportunity repository. */
	@Autowired
	private OpportunityRepository opportunityRepository;

	/** The opportunity job type repository. */
	@Autowired
	private OpportunityJobTypeRepository opportunityJobTypeRepository;

	/** The opportunity cause repository. */
	@Autowired
	private OpportunityCauseRepository opportunityCauseRepository;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;
	
	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#createOpportunity(com.letstagon.dao.model.Opportunity)
	 */
	@Override
	public Opportunity createOpportunity(Opportunity opportunity) {

		// save address before saving opportunity
		if (opportunity.getAddressBean() != null) {
			this.addressRepository.save(opportunity.getAddressBean());
		}

		if (opportunity.getCreatedByParty() == null) {
			throw new InvalidParameterException("No created by party sent.");
		}
		opportunity.setModifiedDate(new Date());
		return opportunityRepository.save(opportunity);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#deleteOpportunity(com.letstagon.dao.model.Opportunity)
	 */
	@Override
	public void deleteOpportunity(Opportunity opportunity) {

		opportunityRepository.delete(opportunity);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#getAllRecentOpportunities()
	 */
	@Override
	public List<Opportunity> getAllRecentOpportunities() {

		Sort orderBy = new Sort(Sort.Direction.DESC, "dateStart");
		return (List<Opportunity>) opportunityRepository.findAll(orderBy);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#getOpportunityDetails(long)
	 */
	@Override
	public Opportunity getOpportunityDetails(long opportunityID) {

		return this.opportunityRepository.findOne(opportunityID);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#editOpportunity(com.letstagon.dao.model.Opportunity)
	 */
	@Override
	public Opportunity editOpportunity(Opportunity opportunity) {
		// save address before saving opportunity
		if (opportunity.getAddressBean() != null) {
			this.addressRepository.save(opportunity.getAddressBean());
		}
		opportunity.setModifiedDate(new Date());
		return opportunityRepository.save(opportunity);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#setContactPerson(com.letstagon.dao.model.Opportunity, com.letstagon.dao.model.User)
	 */
	@Override
	public Opportunity setContactPerson(Opportunity opportunity, User contactperson) {

		Opportunity oppFromDB = this.getOpportunityDetails(opportunity.getId());
		oppFromDB.setContactPerson(contactperson);
		oppFromDB.setModifiedDate(new Date());
		return opportunityRepository.save(oppFromDB);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#addEventToProgram(com.letstagon.dao.model.Opportunity)
	 */
	@Override
	public Opportunity addEventToProgram(Opportunity opportunity) {
		Opportunity oppFromDB = this.getOpportunityDetails(opportunity.getId());
		oppFromDB.setParentProgram(opportunity.getParentProgram());
		oppFromDB.setModifiedDate(new Date());
		return opportunityRepository.save(oppFromDB);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#mapRoleToOpportunity(com.letstagon.dao.model.OpportunityJobType)
	 */
	@Override
	public Opportunity mapRoleToOpportunity(OpportunityJobType opportunityJobType) {

		if (opportunityJobType == null || opportunityJobType.getOpportunityBean() == null
				|| opportunityJobType.getOpportunityBean().getId() <= 0 || opportunityJobType.getJobTypeBean() == null
				|| opportunityJobType.getJobTypeBean().getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}
		OpportunityJobType oppJobType = this.opportunityJobTypeRepository.findOneByJobTypeBeanAndOpportunityBean(
				opportunityJobType.getJobTypeBean(), opportunityJobType.getOpportunityBean());

		if (oppJobType == null) {
			oppJobType = this.opportunityJobTypeRepository.save(opportunityJobType);
		}
		oppJobType.setNumberOfPositions(opportunityJobType.getNumberOfPositions());
		oppJobType.setSelectionCriteria(opportunityJobType.getSelectionCriteria());
		oppJobType.setStatus(opportunityJobType.getStatus());
		this.opportunityJobTypeRepository.save(oppJobType);

		updateModifiedDate(oppJobType.getOpportunityBean().getId());

		return this.getOpportunityDetails(oppJobType.getOpportunityBean().getId());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#updateModifiedDate(long)
	 */
	@Override
	public void updateModifiedDate(long id) {
		Opportunity oppFromDB = opportunityRepository.findOne(id);
		if (oppFromDB == null) {
			return;
		}
		oppFromDB.setModifiedDate(new Date());
		this.opportunityRepository.save(oppFromDB);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#changeOpportunityJobStatus(com.letstagon.dao.model.Opportunity, com.letstagon.dao.model.OpportunityJobType)
	 */
	@Override
	public Opportunity changeOpportunityJobStatus(Opportunity opportunity, OpportunityJobType opportunityJobType) {
		OpportunityJobType oppJobType = this.opportunityJobTypeRepository.findOne(opportunityJobType.getId());
		oppJobType.setStatus(opportunityJobType.getStatus());
		this.opportunityJobTypeRepository.save(oppJobType);

		this.updateModifiedDate(opportunity.getId());

		return this.getOpportunityDetails(opportunity.getId());

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#mapCauseToOpportunity(com.letstagon.dao.model.OpportunityCauseXref)
	 */
	@Override
	public Opportunity mapCauseToOpportunity(OpportunityCauseXref causeXref) {

		if (causeXref == null || causeXref.getOpportunityBean() == null || causeXref.getOpportunityBean().getId() <= 0
				|| causeXref.getCauseBean() == null || causeXref.getCauseBean().getId() <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}
		OpportunityCauseXref causeXrefFromDB = this.opportunityCauseRepository
				.findOneByCauseBeanAndOpportunityBean(causeXref.getCauseBean(), causeXref.getOpportunityBean());

		if (causeXrefFromDB == null) {
			causeXrefFromDB = this.opportunityCauseRepository.save(causeXref);
		}

		causeXrefFromDB.setCauseBean(causeXref.getCauseBean());
		causeXrefFromDB.setOpportunityBean(causeXref.getOpportunityBean());
		this.opportunityCauseRepository.save(causeXref);

		this.updateModifiedDate(causeXref.getOpportunityBean().getId());

		return this.getOpportunityDetails(causeXref.getOpportunityBean().getId());
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#getOpportunitiesCreatedByParty(com.letstagon.dao.model.Party)
	 */
	@Override
	public List<Opportunity> getOpportunitiesCreatedByParty(Party party) {

		if (party == null || party.getId() < 1) {
			throw new InvalidParameterException("Invalid party passed");
		}

		return this.opportunityRepository.findAllByCreatedByParty(party);
	}
	
	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#getOpportunities
	 */
	@Override
	public List<Opportunity> getOpportunities(long limit, int offset) {
		
		String selectQuery = "SELECT DISTINCT(o) from Opportunity o ";
		Query query = this.entityManager.createQuery(selectQuery);
		
		query.setFirstResult((int) limit);
		query.setMaxResults(offset);

		List<Opportunity> oppList = query.getResultList();

		return oppList;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#deleteCause(long, long)
	 */
	@Override
	public void deleteCause(long opportunityID, long causeId) {
		OpportunityCauseXref cause = opportunityCauseRepository.findOneByCauseBeanAndOpportunityBean(new Cause(causeId),
				new Opportunity(opportunityID));
		this.opportunityCauseRepository.delete(cause);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.OpportunityService#saveOrUpdateLatLongForOpportunity(long, java.lang.String)
	 */
	@Override
	public Opportunity saveOrUpdateLatLongForOpportunity(long oppId,String latLong) throws InvalidPreferenceException {
		Opportunity opp = opportunityRepository.findOne(oppId);
		if(opp == null){
			throw new InvalidPreferenceException("Opportunity not found");
		}
		opp.setLatLong(latLong);
		return opportunityRepository.save(opp);
		
	}
	

	/* (non-Javadoc)
	 * @see com.letstagon.service.PartyParticipationService#findAllByPartyBeanAndStatusAndAfterDateStart(com.letstagon.dao.model.Party, java.lang.Boolean, java.util.Date, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public PaginatedSearchResponseModel findAllByPartyBeanAndStatusAndAfterDateStart(long partyDTO,
			Date dateStart, PageRequest pageRequest) {

		if (partyDTO <= 0) {
			throw new InvalidParameterException("Invalid parameter, not enough input");
		}

		Page<Opportunity> result = this.opportunityRepository.findAllByPartyBeanAndStatusAndStartDateAfter(new Party(partyDTO), dateStart, pageRequest);

		return new PaginatedSearchResponseModel(result.getContent(), pageRequest.getPageNumber(), result.getSize(),
				result.getTotalElements());
	}



}
