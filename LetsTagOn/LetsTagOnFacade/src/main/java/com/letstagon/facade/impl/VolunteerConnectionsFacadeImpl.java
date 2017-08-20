package com.letstagon.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Connection;
import com.letstagon.dao.model.PaginatedSearchResponseModel;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;
import com.letstagon.facade.UserFacade;
import com.letstagon.facade.VolunteerConnectionsFacade;
import com.letstagon.facade.dto.ConnectionDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.service.VolunteerConnectionsService;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerConnectionsFacadeImpl.
 */
@Component
public class VolunteerConnectionsFacadeImpl implements VolunteerConnectionsFacade {

	/** The volunteer connections service. */
	@Autowired
	private VolunteerConnectionsService volunteerConnectionsService;

	/** The user facade. */
	@Autowired
	private UserFacade userFacade;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The connection model converter. */
	@Autowired
	private Converter<Connection, ConnectionDTO> connectionModelConverter;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#connect(com.letstagon.facade.dto.UserDTO, com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public ConnectionDTO connect(UserDTO party, UserDTO connectWithUser) {

		User partyModel = mapper.map(party, User.class);
		User connectWithUserModel = mapper.map(connectWithUser, User.class);
		Connection connection = this.volunteerConnectionsService.connect(partyModel, connectWithUserModel);

		return this.connectionModelConverter.convert(connection);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#removeConnection(com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public ConnectionDTO removeConnection(PartyDTO party, PartyDTO removeConnectionWithParty) {
		Party partyModel = mapper.map(party, Party.class);
		Party removeConnectionWithPartyModel = mapper.map(removeConnectionWithParty, Party.class);
		Connection connection = this.volunteerConnectionsService.removeConnection(partyModel,
				removeConnectionWithPartyModel);
		return this.connectionModelConverter.convert(connection);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#listOpenConnections(com.letstagon.facade.dto.UserDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO listOpenConnections(UserDTO volunteer, int page, int size) {

		User volunteerModel = mapper.map(volunteer, User.class);
		PaginatedSearchResponseModel result = volunteerConnectionsService.listOpenConnections(volunteerModel, page,
				size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#listConnectionSuggestions(com.letstagon.facade.dto.UserDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO listConnectionSuggestions(UserDTO volunteer, int page, int size) {

		User volunteerModel = mapper.map(volunteer, User.class);
		PaginatedSearchResponseModel result = volunteerConnectionsService.listOpenConnections(volunteerModel, page,
				size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#listConnectedVolunteers(com.letstagon.facade.dto.UserDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO listConnectedVolunteers(UserDTO volunteer, int page, int size) {

		User volunteerModel = mapper.map(volunteer, User.class);
		PaginatedSearchResponseModel result = volunteerConnectionsService.listConnectedVolunteers(volunteerModel, page,
				size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#listConnectedOrganizations(com.letstagon.facade.dto.UserDTO, int, int)
	 */
	@Override
	public PaginatedResponseDTO listConnectedOrganizations(UserDTO volunteer, int page, int size) {

		User volunteerModel = mapper.map(volunteer, User.class);
		PaginatedSearchResponseModel result = volunteerConnectionsService.listConnectedOrganizations(volunteerModel,
				page, size);
		return new PaginatedResponseDTO(result.getPage(), size, result.getTotalCount(),
				this.convertToDTOList(result.getSearchResult()));
	}

	/**
	 * Convert to DTO list.
	 *
	 * @param modelList the model list
	 * @return the list
	 */
	public List<ConnectionDTO> convertToDTOList(List<? extends Object> modelList) {
		if (CollectionUtils.isEmpty(modelList)) {
			return Collections.emptyList();
		}

		ArrayList<ConnectionDTO> dtoList = new ArrayList<ConnectionDTO>();
		for (Object model : modelList) {
			Connection connection = (Connection) model;
			dtoList.add(this.connectionModelConverter.convert(connection));
		}

		return dtoList;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#acceptConnection(com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public ConnectionDTO acceptConnection(PartyDTO party, PartyDTO acceptConnectionWithParty) {
		Party partyModel = mapper.map(party, Party.class);
		Party connectWithPartyModel = mapper.map(acceptConnectionWithParty, Party.class);
		Connection connection = this.volunteerConnectionsService.acceptConnection(partyModel, connectWithPartyModel);
		return this.connectionModelConverter.convert(connection);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#rejectConnection(com.letstagon.facade.dto.PartyDTO, com.letstagon.facade.dto.PartyDTO)
	 */
	@Override
	public ConnectionDTO rejectConnection(PartyDTO party, PartyDTO removeConnectionWithParty) {
		Party partyModel = mapper.map(party, Party.class);
		Party removeConnectionWithPartyModel = mapper.map(removeConnectionWithParty, Party.class);
		Connection connection = this.volunteerConnectionsService.rejectConnection(partyModel,
				removeConnectionWithPartyModel);
		return this.connectionModelConverter.convert(connection);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#getConnectionListOfUser(long)
	 */
	@Override
	public List<ConnectionDTO> getConnectionListOfUser(long userId) {
		List<Connection> list = volunteerConnectionsService.getConnectionListOfUser(userId);
		List<ConnectionDTO> connectionDTOsList = new ArrayList<ConnectionDTO>();
		if (list != null) {
			for (Connection connection : list) {
				connectionDTOsList.add(this.connectionModelConverter.convert(connection));
			}
			return connectionDTOsList;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#acceptConnection(com.letstagon.facade.dto.UserDTO, com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public ConnectionDTO acceptConnection(UserDTO party, UserDTO acceptConnectionWithParty) {

		PartyDTO party1 = userFacade.getUserPartyDetails(party);
		PartyDTO party2 = userFacade.getUserPartyDetails(acceptConnectionWithParty);
		return this.acceptConnection(party1, party2);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#rejectConnection(com.letstagon.facade.dto.UserDTO, com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public ConnectionDTO rejectConnection(UserDTO party, UserDTO removeConnectionWithParty) {
		PartyDTO party1 = userFacade.getUserPartyDetails(party);
		PartyDTO party2 = userFacade.getUserPartyDetails(removeConnectionWithParty);
		return this.rejectConnection(party1, party2);
	}

	/* (non-Javadoc)
	 * @see com.letstagon.facade.VolunteerConnectionsFacade#checkIfConnected(com.letstagon.facade.dto.UserDTO, com.letstagon.facade.dto.UserDTO)
	 */
	@Override
	public ConnectionDTO checkIfConnected(UserDTO party, UserDTO connectWith) {

		User partyModel = mapper.map(party, User.class);
		User connectWithUserModel = mapper.map(connectWith, User.class);
		Connection connection = this.volunteerConnectionsService.checkIfConnected(partyModel, connectWithUserModel);

		return this.connectionModelConverter.convert(connection);
	}

}
