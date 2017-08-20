package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.Post;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.PostDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostModelConverter.
 */
@Component
public class PostModelConverter implements Converter<Post, PostDTO> {

	/** The party converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public PostDTO convert(Post source) {
		if (source == null) {
			return null;
		}
		PostDTO dest = new PostDTO();
		dest.setId(source.getId());
		dest.setTitle(source.getTitle());
		dest.setContent(source.getContent());
		dest.setPostedOn(source.getPostedOn());
		dest.setStatus(source.getStatus());
		dest.setThumbnailUrl(source.getThumbnailUrl());
		dest.setType(source.getType());
		dest.setUrl(source.getUrl());

		dest.setPostedByParty(partyConverter.convert(source.getPostedByParty()));
		dest.setPostedForParty(partyConverter.convert(source.getPostedForParty()));

		if (source.getPostedForOpportunity() != null) {
			Opportunity opp = source.getPostedForOpportunity();
			OpportunityDTO dto = new OpportunityDTO(opp.getId());

			dto.setName(opp.getName());
			dto.setBannerImage(opp.getBannerImage());
			dto.setDateStart(opp.getDateStart());
			dto.setDateEnd(opp.getDateEnd());
			dto.setDescription(opp.getDescription());
			dto.setName(opp.getName());
			dto.setType(opp.getType());

			dest.setPostedForOpportunity(dto);

		}

		return dest;
	}

}
