package com.letstagon;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.UserType;
import com.letstagon.dao.repository.AddressRepository;
import com.letstagon.dao.repository.UserRepository;
import com.letstagon.service.UserService;


// TODO: Auto-generated Javadoc
/**
 * The Class UserRegistrationTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnServiceApplication.class)
@WebAppConfiguration

public class UserRegistrationTest {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;
	
	/** The address. */
	Address address = new Address();
	
	/** The user. */
	User user = new User();
	
	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		System.out.println("Setup of test case");

		
		// address.setId(1237890L);
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setState("KA");
		addressRepository.save(address);
		System.out.println(address.getId());

		
		
		user.setUserName("hhhhh");
		user.setEmailAddress("fff");
		user.setPassword("vwe");
		UserType userTypeBean = new UserType();
		userTypeBean.setId(1);
		user.setUserTypeBean(userTypeBean);
		user.setUserRole("Vlntr");
		user.setAddressBean(address);
		user = userRepository.save(user);
		// System.out.println(user.getId() + " " +
		// user.getAddressBean().getId());
	}

	
	/**
	 * Test get user by id.
	 */
	@Test
	public void TestGetUserById(){
		Optional<User> userById = userService.getUserById(user.getId());
		assertEquals(user.getId(), userById.get().getId());
		
	}

	/**
	 * Delete set up.
	 */
	@After
	public void deleteSetUp() {
		//userRepository.delete(user);
		//addressRepository.delete(address);
		
	}

}
