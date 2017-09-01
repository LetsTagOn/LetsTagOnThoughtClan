package com.letstagon.web.controller;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.OpportunityDTO;
import com.letstagon.facade.file.OpportunityImageUploadFacade;
import com.letstagon.web.controller.rest.LTOFileController;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityBannerImageUploadController.
 */
@Controller
public class OpportunityBannerImageUploadController extends LTOFileController {

	/** The opportunity image upload facade. */
	@Autowired
	private OpportunityImageUploadFacade opportunityImageUploadFacade;

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/**
	 * Upload file handler.
	 *
	 * @param oppID the opp ID
	 * @param request the request
	 * @return the ajax response DTO
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@RequestMapping(value = "/opportunity/uploadBannerImage/{oppID}", method = RequestMethod.POST)
	public @ResponseBody AjaxResponseDTO uploadFileHandler(@PathVariable(value = "oppID") long oppID,
			MultipartHttpServletRequest request)
					throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		Iterator<String> itr = request.getFileNames();
		AjaxResponseDTO ajaxResponseDTO = new AjaxResponseDTO();
		MultipartFile uploadfile = request.getFile(itr.next());

		if (uploadfile.isEmpty()) {
			throw new InvalidParameterException();
		}

		User user = ltoSessionService.getLoggedInUser();
		if (user == null) {
			throw new SecurityException("No user logged in, cant upload pic.");

		}
		String response = opportunityImageUploadFacade.uploadBannerImage(new OpportunityDTO(oppID), uploadfile);
		ajaxResponseDTO.setData(response);
		return ajaxResponseDTO;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.web.controller.rest.LTOFileController#redirectToFileUrl(javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@RequestMapping(value = "/opportunity/getBannerImage", method = RequestMethod.GET)
	public String redirectToFileUrl(HttpServletResponse response, @RequestParam("key") String key)
			throws IOException, AmazonServiceException, AmazonClientException, InterruptedException {

		return "redirect:"
				+ super.fileService.generatePreSignedUrl(super.fileService.getOpportunityImagesBucketName(), key);

	}

}
