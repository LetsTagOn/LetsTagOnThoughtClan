package com.letstagon.config.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// TODO: Auto-generated Javadoc
/**
 * The Class SolrImportScheduler.
 */
@Component
public class SolrImportScheduler {

	/** The user delta import. */
	@Value("${solr.user.deltaImport.Url}")
	private String userDeltaImport;

	/** The opportunity delta import. */
	@Value("${solr.opportunity.deltaImport.Url}")
	private String opportunityDeltaImport;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SolrImportScheduler.class);

	/**
	 * Import user details.
	 */
	@Scheduled(fixedRate = 5*60*1000)
	public void importUserDetails() {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(userDeltaImport, String.class);
		LOG.info("Imported User data into Solr");
	}

	/**
	 * Import opportunity details.
	 */
	@Scheduled(fixedRate = 5*60*1000)
	public void importOpportunityDetails() {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(opportunityDeltaImport, String.class);
		LOG.info("Imported opportunity data into Solr");
	}

}
