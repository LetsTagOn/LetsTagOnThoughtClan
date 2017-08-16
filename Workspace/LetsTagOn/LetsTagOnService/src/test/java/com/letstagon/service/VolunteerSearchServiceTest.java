package com.letstagon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.letstagon.LetsTagOnServiceApplication;
import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.Cause;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.PartyCauseXref;
import com.letstagon.dao.model.User;
import com.letstagon.dao.model.VolunteerSearchModel;
import com.letstagon.dao.repository.AddressRepository;
import com.letstagon.dao.repository.PartyCauseRepository;
import com.letstagon.dao.repository.PartyRepository;
import com.letstagon.dao.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class VolunteerSearchServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnServiceApplication.class)
@WebAppConfiguration
public class VolunteerSearchServiceTest {

	/** The Constant LOG. */
	public static final Logger LOG = LoggerFactory.getLogger(VolunteerSearchServiceTest.class);

	/** The search service. */
	@Autowired
	private VolunteerSearchService searchService;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/** The party cause repository. */
	@Autowired
	private PartyCauseRepository partyCauseRepository;

	/** The party repository. */
	@Autowired
	private PartyRepository partyRepository;

	/** The user list. */
	private List<User> userList = new ArrayList<User>();
	
	/** The party list. */
	private List<Party> partyList = new ArrayList<Party>();
	
	/** The party cause list. */
	private List<PartyCauseXref> partyCauseList = new ArrayList<PartyCauseXref>();

	/** The address. */
	private Address address;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {

		address = addressRepository.save(UserTestUtil.getDefaultAddress());

		userList.add(
				userRepository.save(UserTestUtil.getUser(address, "ramganesh1", "Ram", "Ganesh", "ram@gmail.com")));
		userList.add(userRepository
				.save(UserTestUtil.getUser(address, "ramganesh12", "Ramesh", "Ganesh", "ram2@gmail.com")));
		userList.add(userRepository
				.save(UserTestUtil.getUser(address, "ramganesh13", "Suresh", "Ganesh", "ram3@gmail.com")));
		userList.add(
				userRepository.save(UserTestUtil.getUser(address, "ramganesh14", "Karthik", "Reddy", "kr4@gmail.com")));

		User user1 = userList.get(0);
		Party party1 = new Party();
		party1.setUserBean(user1);
		party1 = partyRepository.save(party1);
		partyList.add(party1);

		User user2 = userList.get(1);
		Party party2 = new Party();
		party2.setUserBean(user2);
		party2 = partyRepository.save(party2);
		partyList.add(party2);

		PartyCauseXref pcx = new PartyCauseXref();
		pcx.setPartyBean(party1);
		Cause causeBean = new Cause(1);
		pcx.setCauseBean(causeBean);
		pcx = partyCauseRepository.save(pcx);
		partyCauseList.add(pcx);

		pcx = new PartyCauseXref();
		pcx.setPartyBean(party2);
		causeBean = new Cause(2);
		pcx.setCauseBean(causeBean);
		pcx = partyCauseRepository.save(pcx);
		partyCauseList.add(pcx);

	}

	/**
	 * Search by name dynamic.
	 */
	@Test
	public void searchByNameDynamic() {

		List<User> volunteers = (List<User>) searchService
				.searchDynamicByCriteria(new VolunteerSearchModel("gane", null, null, null), 0, 10).getSearchResult();

		Assert.assertNotNull(volunteers);
		if (volunteers != null) {
			LOG.info("Volunteers found : " + volunteers);
			Assert.assertTrue(volunteers.size() > 0);
		}

	}

	/**
	 * Search by name and cause dynamic.
	 */
	@Test
	public void searchByNameAndCauseDynamic() {

		Cause cause = new Cause(1);
		List<User> volunteers = (List<User>) searchService
				.searchDynamicByCriteria(new VolunteerSearchModel("gane", Arrays.asList(cause), null, null), 0, 10)
				.getSearchResult();

		Assert.assertNotNull(volunteers);
		if (volunteers != null) {
			LOG.info("Volunteers found : " + volunteers);
			Assert.assertTrue(volunteers.size() > 0);

		}

	}

	/**
	 * Search by name and multiple cause dynamic.
	 */
	@Test
	public void searchByNameAndMultipleCauseDynamic() {

		Cause cause = new Cause(1);
		Cause cause2 = new Cause(2);
		List<User> volunteers = (List<User>) searchService.searchDynamicByCriteria(
				new VolunteerSearchModel("gane", Arrays.asList(cause, cause2), null, null), 0, 10).getSearchResult();

		Assert.assertNotNull(volunteers);
		if (volunteers != null) {
			LOG.info("Volunteers found : " + volunteers);
			Assert.assertTrue(volunteers.size() == 2);

		}

	}

	/**
	 * Search by name and location.
	 */
	@Test
	public void searchByNameAndLocation() {

		List<User> volunteers = (List<User>) searchService
				.searchDynamicByCriteria(new VolunteerSearchModel("gane", null, null, "Bangalore"), 0, 10)
				.getSearchResult();

		Assert.assertNotNull(volunteers);
		if (volunteers != null) {
			LOG.info("Volunteers found : " + volunteers);
			Assert.assertTrue(volunteers.size() > 0);

		}

	}

	/**
	 * Clean up test data.
	 */
	@After
	public void cleanUpTestData() {
		LOG.info("Delete test data");
		partyCauseRepository.delete(partyCauseList);
		partyRepository.delete(partyList);
		userRepository.delete(userList);
		addressRepository.delete(address);
	}

}
