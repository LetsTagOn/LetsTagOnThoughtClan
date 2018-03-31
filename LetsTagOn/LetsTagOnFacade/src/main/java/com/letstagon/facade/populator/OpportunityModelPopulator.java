package com.letstagon.facade.populator;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.service.PartyService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityModelPopulator.
 */
@Component
public class OpportunityModelPopulator implements LtoPopulator<OpportunityDTO, Opportunity> {

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private PartyService partyService;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.populator.LtoPopulator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Opportunity populate(OpportunityDTO source, Opportunity dest) {
		if (source == null) {
			return null;
		}

		if (dest == null) {
			dest = new Opportunity();
		}

		dest.setId(source.getId());
		dest.setBannerImage(source.getBannerImage());
		dest.setDateStart(source.getDateStart());
		dest.setDateEnd(source.getDateEnd());
		dest.setDescription(source.getDescription());
		dest.setName(source.getName());
		dest.setType(source.getType());
		dest.setLatLong(source.getLatLong());
		if (source.getAddressBean() != null) {
			dest.setAddressBean(mapper.map(source.getAddressBean(), Address.class));
		}

		if (source.getCreatedBy() != null) {
			User user = this.mapper.map(source.getCreatedBy(), User.class);
			dest.setCreatedByParty(partyService.findByUserBean(user));
		}

		if (source.getParentProgram() != null) {
			dest.setParentProgram(new Opportunity(source.getParentProgram().getId()));
		}

		return dest;
	}

}
