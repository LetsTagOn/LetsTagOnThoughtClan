package com.letstagon.facade.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityCauseXref;
import com.letstagon.dao.model.OpportunityJobType;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyParticipation;
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.AddressDTO;
import com.letstagon.facade.dto.OpportunityCauseXrefDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityJobTypeDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PartyParticipationDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityModelConverter.
 */
@Component
public class OpportunityModelConverter implements Converter<Opportunity, OpportunityDTO> {

	/** The address converter. */
	@Autowired
	private Converter<Address, AddressDTO> addressConverter;

	/** The user model converter. */
	@Autowired
	private Converter<User, UserDTO> userModelConverter;

	/** The party converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyConverter;

	/** The opportunity job type converter. */
	@Autowired
	private Converter<OpportunityJobType, OpportunityJobTypeDTO> opportunityJobTypeConverter;

	/** The opp cause xref converter. */
	@Autowired
	private Converter<OpportunityCauseXref, OpportunityCauseXrefDTO> oppCauseXrefConverter;

	/** The party participation converter. */
	@Autowired
	private Converter<PartyParticipation, PartyParticipationDTO> partyParticipationConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OpportunityDTO convert(Opportunity source) {
		if (source == null) {
			return null;
		}

		OpportunityDTO dest = new OpportunityDTO();

		dest.setId(source.getId());
		dest.setBannerImage(source.getBannerImage());
		dest.setDateStart(source.getDateStart());
		dest.setDateEnd(source.getDateEnd());
		dest.setDescription(source.getDescription());
		dest.setName(source.getName());
		dest.setType(source.getType());
		dest.setLatLong(source.getLatLong());
		if (source.getParentProgram() != null) {

			dest.setParentProgram(new OpportunityDTO(source.getParentProgram().getId()));
		}
		dest.setAddressBean(addressConverter.convert(source.getAddressBean()));

		if (source.getContactPerson() != null) {
			dest.setContactPerson(userModelConverter.convert(source.getContactPerson()));
		}

		if (source.getCreatedByParty() != null) {
			dest.setCreatedBy(this.partyConverter.convert(source.getCreatedByParty()));
		}

		dest.setOpportunityJobTypes(this.convertOppJobTypeList(source.getOpportunityJobTypes()));
		dest.setOpportunityCauseXrefs(this.convertCauseList(source.getOpportunityCauseXrefs()));
		dest.setPartyParticipations(this.convertParticipationList(source.getPartyParticipations()));
		dest.setLinkedEvents(this.convertEventList(source.getLinkedEvents()));

		return dest;
	}

	/**
	 * Convert event list.
	 *
	 * @param linkedEvents the linked events
	 * @return the list
	 */
	private List<OpportunityDTO> convertEventList(List<Opportunity> linkedEvents) {

		if (CollectionUtils.isEmpty(linkedEvents)) {
			return Collections.emptyList();
		}

		List<OpportunityDTO> list = new ArrayList<OpportunityDTO>();
		for (Opportunity model : linkedEvents) {
			list.add(this.convert(model));
		}

		return list;
	}

	/**
	 * Convert opp job type list.
	 *
	 * @param opportunityJobTypes the opportunity job types
	 * @return the list
	 */
	private List<OpportunityJobTypeDTO> convertOppJobTypeList(List<OpportunityJobType> opportunityJobTypes) {

		if (CollectionUtils.isEmpty(opportunityJobTypes)) {
			return Collections.emptyList();
		}

		List<OpportunityJobTypeDTO> list = new ArrayList<OpportunityJobTypeDTO>();
		for (OpportunityJobType opp : opportunityJobTypes) {
			list.add(opportunityJobTypeConverter.convert(opp));
		}

		return list;
	}

	/**
	 * Convert cause list.
	 *
	 * @param causeList the cause list
	 * @return the list
	 */
	private List<OpportunityCauseXrefDTO> convertCauseList(List<OpportunityCauseXref> causeList) {

		if (CollectionUtils.isEmpty(causeList)) {
			return Collections.emptyList();
		}

		List<OpportunityCauseXrefDTO> list = new ArrayList<OpportunityCauseXrefDTO>();
		for (OpportunityCauseXref cause : causeList) {
			list.add(oppCauseXrefConverter.convert(cause));
		}

		return list;
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
			list.add(this.partyParticipationConverter.convert(model));
		}

		return list;
	}

}
