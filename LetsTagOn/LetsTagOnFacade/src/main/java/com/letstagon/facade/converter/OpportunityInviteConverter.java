package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.OpportunityInvite;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.OpportunityInviteDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityInviteConverter.
 */
@Component
public class OpportunityInviteConverter implements Converter<OpportunityInvite, OpportunityInviteDTO> {

	/** The party converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OpportunityInviteDTO convert(OpportunityInvite source) {

		if (source == null) {
			return null;
		}

		OpportunityInviteDTO dest = new OpportunityInviteDTO();

		dest.setId(source.getId());
		dest.setAccepted(source.getAccepted());
		dest.setInviteAcceptedDate(source.getInviteAcceptedDate());
		dest.setStatus(source.getStatus());
		dest.setInvitedDate(source.getInvitedDate());

		if (source.getOpportunityBean() != null) {
			OpportunityDTO oppDto = new OpportunityDTO();
			oppDto.setId(source.getOpportunityBean().getId());
			oppDto.setName(source.getOpportunityBean().getName());
			oppDto.setDescription(source.getOpportunityBean().getDescription());
			oppDto.setDateStart(source.getOpportunityBean().getDateStart());
			oppDto.setDateEnd(source.getOpportunityBean().getDateEnd());

			if (source.getOpportunityBean().getContactPerson() != null) {
				UserDTO contactPerson = new UserDTO();
				User model = source.getOpportunityBean().getContactPerson();

				contactPerson.setId(model.getId());
				contactPerson.setFirstName(model.getFirstName());
				contactPerson.setLastName(model.getLastName());
				oppDto.setContactPerson(contactPerson);

			}

			dest.setOpportunityBean(oppDto);

			dest.setInvitedByParty(this.partyConverter.convert(source.getInvitedByParty()));
			dest.setInvitedParty(this.partyConverter.convert(source.getInvitedParty()));

		}

		return dest;
	}

}
