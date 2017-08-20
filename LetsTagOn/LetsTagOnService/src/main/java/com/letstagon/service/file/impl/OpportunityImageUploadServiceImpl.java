package com.letstagon.service.file.impl;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.service.file.OpportunityImageUploadService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityImageUploadServiceImpl.
 */
@Component(value = "opportunityImageUploadService")
public class OpportunityImageUploadServiceImpl extends LtoFileServiceImpl implements OpportunityImageUploadService {

	/** The Constant BANNER_IMG_PATH. */
	private static final String BANNER_IMG_PATH = "/BannerImage/";

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.OpportunityImageUploadService#uploadBannerImage(com.letstagon.dao.model.Opportunity, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String uploadBannerImage(Opportunity opportunity, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		String keyName = this.generateKeyName();
		String key = this.generateFolderName(opportunity) + keyName;

		super.uploadFile(super.getOpportunityImagesBucketName(), this.generateFolderName(opportunity), keyName, file);

		return key;
	}

	/**
	 * Generate key name.
	 *
	 * @return the string
	 */
	private String generateKeyName() {

		return UUID.randomUUID().toString();

	}

	/**
	 * Generate folder name.
	 *
	 * @param opportunity the opportunity
	 * @return the string
	 */
	private String generateFolderName(Opportunity opportunity) {

		if (opportunity == null || opportunity.getId() < 1) {
			throw new InvalidParameterException("User cannot be null");

		}

		return opportunity.getId() + OpportunityImageUploadServiceImpl.BANNER_IMG_PATH;

	}

}
