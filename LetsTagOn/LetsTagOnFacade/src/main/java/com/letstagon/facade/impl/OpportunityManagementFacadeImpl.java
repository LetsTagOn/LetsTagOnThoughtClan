package com.letstagon.facade.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityCauseXref;
import com.letstagon.dao.model.OpportunityJobType;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.exception.profile.InvalidPreferenceException;
import com.letstagon.facade.OpportunityManagementFacade;
import com.letstagon.facade.dto.OpportunityCauseXrefDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityJobTypeDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.populator.LtoPopulator;
import com.letstagon.service.OpportunityService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityManagementFacadeImpl.
 */
@Component
public class OpportunityManagementFacadeImpl implements OpportunityManagementFacade {

	/** The opp converter. */
	@Autowired
	private Converter<Opportunity, OpportunityDTO> oppConverter;

	/** The opp reverse converter. */
	@Autowired
	private Converter<OpportunityDTO, Opportunity> oppReverseConverter;

	/** The opp model populator. */
	@Autowired
	private LtoPopulator<OpportunityDTO, Opportunity> oppModelPopulator;

	/** The opp job type rev converter. */
	@Autowired
	private Converter<OpportunityJobTypeDTO, OpportunityJobType> oppJobTypeRevConverter;

	/** The opp cause rev converter. */
	@Autowired
	private Converter<OpportunityCauseXrefDTO, OpportunityCauseXref> oppCauseRevConverter;

	/** The opportunity service. */
	@Autowired
	private OpportunityService opportunityService;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#getOpportunityDetails(long)
	 */
	@Override
	public OpportunityDTO getOpportunityDetails(long opportunityID) {

		Opportunity opportunity = opportunityService.getOpportunityDetails(opportunityID);

		return this.oppConverter.convert(opportunity);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#createOpportunity(com.letstagon.facade.dto.OpportunityDTO)
	 */
	@Override
	public OpportunityDTO createOpportunity(OpportunityDTO opportunity) {
		Opportunity opportunityModel = opportunityService
				.createOpportunity(this.oppReverseConverter.convert(opportunity));

		return this.oppConverter.convert(opportunityModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#editOpportunity(com.letstagon.facade.dto.OpportunityDTO)
	 */
	@Override
	public OpportunityDTO editOpportunity(OpportunityDTO opportunity) {

		Opportunity opportunityModel = this.opportunityService.getOpportunityDetails(opportunity.getId());
		// its an edit so we need to use populators and modify only fields
		// required
		opportunityModel = opportunityService
				.editOpportunity(this.oppModelPopulator.populate(opportunity, opportunityModel));

		return this.oppConverter.convert(opportunityModel);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#setContactPerson(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public OpportunityDTO setContactPerson(OpportunityDTO opportunity, UserDTO contactperson) {

		if (opportunity == null || contactperson == null) {
			throw new InvalidParameterException("Invalid input.");
		}

		Opportunity opportunityModel = this.mapper.map(opportunity, Opportunity.class);
		User contactPerson = this.mapper.map(contactperson, User.class);

		opportunityModel = opportunityService.setContactPerson(opportunityModel, contactPerson);

		return this.oppConverter.convert(opportunityModel);

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#addEventToProgram(com.letstagon.facade.dto.OpportunityDTO)
	 */
	@Override
	public OpportunityDTO addEventToProgram(OpportunityDTO opportunity) {

		if (opportunity == null || opportunity.getParentProgram() == null) {
			throw new InvalidParameterException("Invalid input.");
		}

		Opportunity opportunityModel = opportunityService
				.addEventToProgram(this.oppReverseConverter.convert(opportunity));

		return this.oppConverter.convert(opportunityModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#mapRoleToOpportunity(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.OpportunityJobTypeDTO)
	 */
	@Override
	public OpportunityDTO mapRoleToOpportunity(OpportunityDTO opportunityDTO,
			OpportunityJobTypeDTO opportunityJobTypeDTO) {

		if (opportunityDTO == null) {
			throw new InvalidParameterException("Invalid input.");
		}

		opportunityJobTypeDTO.setOpportunityBean(opportunityDTO);
		Opportunity opportunityModel = opportunityService
				.mapRoleToOpportunity(this.oppJobTypeRevConverter.convert(opportunityJobTypeDTO));

		return this.oppConverter.convert(opportunityModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#mapCauseToOpportunity(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.OpportunityCauseXrefDTO)
	 */
	@Override
	public OpportunityDTO mapCauseToOpportunity(OpportunityDTO opportunityDTO,
			OpportunityCauseXrefDTO opportunityCauseXrefDTO) {

		if (opportunityDTO == null) {
			throw new InvalidParameterException("Invalid input.");
		}

		opportunityCauseXrefDTO.setOpportunityBean(opportunityDTO);
		Opportunity opportunityModel = opportunityService
				.mapCauseToOpportunity(this.oppCauseRevConverter.convert(opportunityCauseXrefDTO));

		return this.oppConverter.convert(opportunityModel);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#getOpportunitiesCreatedByParty(com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public List<OpportunityDTO> getOpportunitiesCreatedByParty(PartyDTO partyDTO) {
		
		List<Opportunity> oppList = this.opportunityService.getOpportunitiesCreatedByParty(new Party(partyDTO.getId()));
		return this.convertOppModelList(oppList);
		
	}

	/**
	 * Convert opp model list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	public List<OpportunityDTO> convertOppModelList(List<Opportunity> modelList) {
		
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}
		List<OpportunityDTO> dtoList = new ArrayList<OpportunityDTO>();
		for (Opportunity model : modelList) {
			dtoList.add(this.oppConverter.convert(model));
		}
		return dtoList;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#changeOpportunityJobStatus(com.letstagon.facade.dto.OpportunityDTO, com.letstagon.facade.dto.OpportunityJobTypeDTO)
	 */
	@Override
	public OpportunityDTO changeOpportunityJobStatus(OpportunityDTO opportunityDTO,OpportunityJobTypeDTO opportunityJobTypeDTO) {
		
		Opportunity opportunity =  this.opportunityService.changeOpportunityJobStatus(this.oppReverseConverter.convert(opportunityDTO), this.oppJobTypeRevConverter.convert(opportunityJobTypeDTO));
		return this.oppConverter.convert(opportunity);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.OpportunityManagementFacade#saveOrUpdateLatLongForOpportunity(long, java.lang.String)
	 */
	@Override
	public OpportunityDTO saveOrUpdateLatLongForOpportunity(long oppId,String latLong) throws InvalidPreferenceException {
		Opportunity opp = opportunityService.saveOrUpdateLatLongForOpportunity(oppId, latLong);
		return this.oppConverter.convert(opp);
	}

}
