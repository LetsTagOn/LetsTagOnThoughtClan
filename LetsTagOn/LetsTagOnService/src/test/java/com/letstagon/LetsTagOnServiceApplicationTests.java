package com.letstagon;

import java.util.Optional;

import org.junit.Before;
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
 * The Class LetsTagOnServiceApplicationTests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnServiceApplication.class)
@WebAppConfiguration

public class LetsTagOnServiceApplicationTests {

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
	private Address address;
	
	/**
	 * Setup.
	 */
	@Before
	public void setup(){
		
		System.out.println("Setup of test case");
		
		address=new Address();
		address.setId(1237890L);
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setState("KA");
		addressRepository.save(address);
		
		
		User user=new User();
		user.setId(111L);
		user.setUserName("Test2");
		user.setEmailAddress("few");
		user.setPassword("vwe");
		UserType userTypeBean=new UserType();
		userTypeBean.setId(1);
		user.setUserTypeBean(userTypeBean);
		user.setUserRole("Vlntr");
		user.setAddressBean(address);
		userRepository.save(user);
		
		System.out.println("Added user : "+user);
	}
	
	/**
	 * Context loads.
	 */
	@Test
	public void contextLoads() {

		System.out.println("Starting test");
		Optional<User> user = userService.getUserByUserName("Test2");

		if (user.isPresent()){
			System.out.println("user : " + user.get());
			userRepository.delete(user.get());
			addressRepository.delete(address);
			
		}
		else
			System.out.println("No such user");

	}

}
