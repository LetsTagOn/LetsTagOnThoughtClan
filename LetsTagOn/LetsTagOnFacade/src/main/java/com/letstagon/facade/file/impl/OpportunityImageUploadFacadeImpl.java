package com.letstagon.facade.file.impl;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.file.OpportunityImageUploadFacade;
import com.letstagon.service.OpportunityService;
import com.letstagon.service.file.OpportunityImageUploadService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityImageUploadFacadeImpl.
 */
@Component
public class OpportunityImageUploadFacadeImpl implements OpportunityImageUploadFacade {

	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(OpportunityImageUploadFacadeImpl.class);

	/** The image upload service. */
	@Resource(name = "opportunityImageUploadService")
	private OpportunityImageUploadService imageUploadService;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The opportunity service. */
	@Autowired
	private OpportunityService opportunityService;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.file.OpportunityImageUploadFacade#uploadBannerImage(com.letstagon.facade.dto.OpportunityDTO, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String uploadBannerImage(OpportunityDTO opportunity, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		if (opportunity == null) {

			// invalid opportunity
			throw new InvalidParameterException("Invalid input.");

		}

		Opportunity oppFromDb = opportunityService.getOpportunityDetails(opportunity.getId());
		if (oppFromDb == null) {

			// invalid opportunity
			throw new InvalidParameterException("Invalid Opportunity id");

		}

		Opportunity oppModel = mapper.map(opportunity, Opportunity.class);

		String key = imageUploadService.uploadBannerImage(oppModel, file);

		if (StringUtils.isNotBlank(oppFromDb.getBannerImage())) {
			LOG.trace("Deleting previous current profile pic.");
			this.imageUploadService.deleteFile(this.imageUploadService.getOpportunityImagesBucketName(),
					oppFromDb.getBannerImage());
		}
		oppFromDb.setBannerImage(key);
		this.opportunityService.editOpportunity(oppFromDb);

		return key;

	};

}
