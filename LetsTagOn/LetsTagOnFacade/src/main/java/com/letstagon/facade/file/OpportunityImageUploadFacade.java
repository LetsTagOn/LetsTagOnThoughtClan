package com.letstagon.facade.file;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.letstagon.facade.dto.OpportunityDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface OpportunityImageUploadFacade.
 */
public interface OpportunityImageUploadFacade {

	/**
	 * Upload banner image.
	 *
	 * @param opportunity the opportunity
	 * @param file the file
	 * @return the string
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public String uploadBannerImage(OpportunityDTO opportunity, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException;

}
