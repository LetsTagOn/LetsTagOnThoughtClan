package com.letstagon.service;

import java.util.List;
import java.util.Date;

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
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.OpportunityJobType;
import com.letstagon.dao.model.Party;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnServiceApplication.class)
@WebAppConfiguration
public class OpportunityTest {

	/** The Constant LOG. */
	public static final Logger LOG = LoggerFactory.getLogger(OpportunityTest.class);

	/** The opportunity service. */
	@Autowired
	private OpportunityService opportunityService;

	/**
	 * Creates the opportunity test.
	 */
	@Test
	public void createOpportunityTest() {
		Opportunity createdOpportunity = opportunityService.createOpportunity(getOpportunity());
		LOG.info("Created user opportunity : " + createdOpportunity);
	}

	/**
	 * Delete opportunity.
	 */
	@Test
	public void deleteOpportunity() {
		Opportunity opportunity = new Opportunity();
		opportunity.setId(2);
		opportunityService.deleteOpportunity(opportunity);
	}

	/**
	 * Find by ID.
	 */
	@Test
	public void findByID() {
		Opportunity opportunity = opportunityService.getOpportunityDetails((long) 2);
		LOG.info("search Result  : " + opportunity);
	}

	/**
	 * Gets the all recent opportunities.
	 *
	 * @return the all recent opportunities
	 */
	@Test
	public void getAllRecentOpportunities() {
		List<Opportunity> upcomingEvents = opportunityService.getAllRecentOpportunities();
		for (Opportunity opportunity : upcomingEvents) {
			System.out.println(opportunity);
		}
	}
	
	/**
	 * Change opportunities job status.
	 */
	@Test
	public void changeOpportunitiesJobStatus() {
		OpportunityJobType opportunityJobType = new OpportunityJobType();
		opportunityJobType.setId(4);
		opportunityJobType.setStatus(false);
		Opportunity opp= new Opportunity();
		opp.setId(3);
		Opportunity newopp=opportunityService.changeOpportunityJobStatus(opp, opportunityJobType);
	}

	/**
	 * Gets the opportunity.
	 *
	 * @return the opportunity
	 */
	private Opportunity getOpportunity() {
		Opportunity opportunity = new Opportunity();
		opportunity.setBannerImage("banner.jpg");
		opportunity.setDateEnd(new Date());
		opportunity.setDateStart(new Date());
		opportunity.setDescription("New Description 2");
		opportunity.setLatLong("12.9347893,77.6101299");
		opportunity.setName("Clean agara lake");
		opportunity.setContactPerson(new User(1));
		Address address = new Address();
		address.setId(1);
		opportunity.setAddressBean(address);
		opportunity.setCreatedByParty(new Party(1));
		return opportunity;
	}

}
