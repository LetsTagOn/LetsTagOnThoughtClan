package com.letstagon.facade.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Connection;
import com.letstagon.dao.model.Party;
import com.letstagon.facade.dto.ConnectionDTO;
import com.letstagon.facade.dto.PartyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionModelConverter.
 */
@Component
public class ConnectionModelConverter implements Converter<Connection, ConnectionDTO> {

	/** The party model converter. */
	@Autowired
	private Converter<Party, PartyDTO> partyModelConverter;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public ConnectionDTO convert(Connection model) {

		if (model == null) {
			return null;
		}

		ConnectionDTO dto = new ConnectionDTO();
		dto.setId(model.getId());
		dto.setConnected(model.getConnected());
		dto.setInitiatedOn(model.getInitiatedOn());
		dto.setConnectedOn(model.getConnectedOn());
		dto.setConnectionType(model.getConnectionType());
		dto.setParty1(this.partyModelConverter.convert(model.getParty1()));
		dto.setParty2(this.partyModelConverter.convert(model.getParty2()));

		return dto;

	}

}
