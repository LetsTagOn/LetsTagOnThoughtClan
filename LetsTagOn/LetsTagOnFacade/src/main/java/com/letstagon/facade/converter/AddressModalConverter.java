package com.letstagon.facade.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Address;
import com.letstagon.facade.dto.AddressDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressModalConverter.
 */
@Component
public class AddressModalConverter implements Converter<Address, AddressDTO> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public AddressDTO convert(Address source) {
		if (source == null) {
			return null;
		}

		AddressDTO dto = new AddressDTO();
		dto.setCity(source.getCity());
		dto.setCountry(source.getCountry());
		dto.setId(source.getId());
		dto.setPostalCode(source.getPostalCode());
		dto.setState(source.getState());
		dto.setStreet(source.getStreet());
		dto.setFormattedAddress(source.getFormattedAddress());
		return dto;
	}

}
