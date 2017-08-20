package com.letstagon.facade.converter;

import java.util.Calendar;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.enums.UserExperienceTypeEnum;
import com.letstagon.facade.dto.UserExperienceDTO;
import com.letstagon.facade.dto.linkedin.LinkedInDateDTO;
import com.letstagon.facade.dto.linkedin.LinkedinUserExperienceDTO;
// TODO: Auto-generated Javadoc

/**
 * The Class UserProfessionalExperienceDTOConverter.
 */
@Component
public class UserProfessionalExperienceDTOConverter  implements Converter<LinkedinUserExperienceDTO, UserExperienceDTO> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public UserExperienceDTO convert(LinkedinUserExperienceDTO source) {
		// TODO Auto-generated method stub
		UserExperienceDTO experience = new UserExperienceDTO();
		if(source.getCompany() != null){
			experience.setOrganizationName(source.getCompany().getName());
		}
		if(source.getLocation() != null){
			experience.setLocation(source.getLocation().getName());
		}
		experience.setTitle(source.getTitle());
		experience.setDescription(source.getSummary());
		experience.setType(UserExperienceTypeEnum.PROFESSIONAL.getExperienceType());
		if(source.getStartDate() != null){
			experience.setStartDate(calendarToDate(source.getStartDate()));
		}
		if(source.getEndDate() != null){
			experience.setEndDate(calendarToDate(source.getEndDate()));
		}		
		return experience;
	}

	/**
	 * Calendar to date.
	 *
	 * @param date the date
	 * @return the date
	 */
	private Date calendarToDate(LinkedInDateDTO date){
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.YEAR, date.getYear());
			calendar1.set(Calendar.MONTH, date.getMonth());
			Date returningDate = calendar1.getTime();
			return returningDate;
	}

	

}
