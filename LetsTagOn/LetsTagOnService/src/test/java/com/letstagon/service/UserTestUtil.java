package com.letstagon.service;

import java.util.UUID;

import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserType;

// TODO: Auto-generated Javadoc
/**
 * The Class UserTestUtil.
 */
public abstract class UserTestUtil {

	/** The default address id. */
	public static long DEFAULT_ADDRESS_ID = 123789220L;

	/**
	 * Gets the user.
	 *
	 * @param address the address
	 * @param userName the user name
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param emailAddress the email address
	 * @return the user
	 */
	public static User getUser(Address address, String userName,String firstName,String lastName,String emailAddress) {

		User user = new User();
		user.setId(111L);
		user.setUserName(userName);
		user.setEmailAddress(emailAddress);
		user.setPassword(UUID.randomUUID().toString());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		UserType userTypeBean = new UserType();
		userTypeBean.setId(1);
		user.setUserTypeBean(userTypeBean);
		user.setUserRole("Vlntr");
		user.setAddressBean(address);

		return user;

	}

	/**
	 * Gets the default address.
	 *
	 * @return the default address
	 */
	public static Address getDefaultAddress() {

		Address address = new Address();
		address.setId(DEFAULT_ADDRESS_ID);
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setState("KA");
		return address;
	}

}
