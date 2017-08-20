package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyParticipation;
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PartyParticipationDTO;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class PartyParticipationConverter.
 */
@Component
public class PartyParticipationConverter implements Converter<PartyParticipation, PartyParticipationDTO> {

	/** The party converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyConverter;

	/** The job type modal converter. */
	@Autowired
	private Converter<JobType, JobTypeDTO> jobTypeModalConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public PartyParticipationDTO convert(PartyParticipation source) {

		if (source == null) {
			return null;
		}

		PartyParticipationDTO dest = new PartyParticipationDTO();

		dest.setId(source.getId());
		dest.setDateStart(source.getDateStart());
		dest.setDateEnd(source.getDateEnd());
		dest.setAttendance(source.getAttendance());
		dest.setJobTypeBean(this.jobTypeModalConverter.convert(source.getJobTypeBean()));		
		dest.setRating(source.getRating());
		dest.setReview(source.getReview());
		dest.setPartyBean(this.partyConverter.convert(source.getPartyBean()));
		dest.setStatus(source.getStatus());

		if (source.getOpportunityBean() != null) {
			OpportunityDTO oppDto = new OpportunityDTO();
			oppDto.setId(source.getOpportunityBean().getId());
			oppDto.setName(source.getOpportunityBean().getName());
			oppDto.setDescription(source.getOpportunityBean().getDescription());
			oppDto.setDateStart(source.getOpportunityBean().getDateStart());
			oppDto.setDateEnd(source.getOpportunityBean().getDateEnd());
			
			if(source.getOpportunityBean().getContactPerson()!=null){
				UserDTO contactPerson=new UserDTO();
				User model=source.getOpportunityBean().getContactPerson();
				
				contactPerson.setId(model.getId());
				contactPerson.setFirstName(model.getFirstName());
				contactPerson.setLastName(model.getLastName());
				oppDto.setContactPerson(contactPerson);
				
			}
			
			dest.setOpportunityBean(oppDto);
			
			
		}
		
		

		return dest;
	}

}
