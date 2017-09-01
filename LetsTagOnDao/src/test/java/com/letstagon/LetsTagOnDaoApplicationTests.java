package com.letstagon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.letstagon.dao.model.User;
import com.letstagon.dao.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class LetsTagOnDaoApplicationTests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnDaoApplication.class)
public class LetsTagOnDaoApplicationTests {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Context loads.
	 */
	@Test
	public void contextLoads() {
	}

	/**
	 * Test load user.
	 */
	@Test
	public void testLoadUser() {
		User user = userRepository.findOneByUserName("Test");
		System.out.println(user);
		

	}

}
