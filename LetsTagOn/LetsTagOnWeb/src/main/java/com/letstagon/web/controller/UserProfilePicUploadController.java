package com.letstagon.web.controller;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.file.UserProfilePicFacade;
import com.letstagon.web.controller.rest.LTOFileController;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserProfilePicUploadController.
 */
@Controller
public class UserProfilePicUploadController extends LTOFileController {

	/** The user profile pic facade. */
	@Autowired
	private UserProfilePicFacade userProfilePicFacade;

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/**
	 * Upload file handler.
	 *
	 * @param request the request
	 * @return the ajax response DTO
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@RequestMapping(value = "/userProfile/uploadPic", method = RequestMethod.POST)
	public @ResponseBody AjaxResponseDTO uploadFileHandler(MultipartHttpServletRequest request)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {
		
		Iterator<String> itr=request.getFileNames();
		AjaxResponseDTO ajaxResponseDTO = new AjaxResponseDTO();
        MultipartFile uploadfile=request.getFile(itr.next());

		if (uploadfile.isEmpty()) {
			throw new InvalidParameterException();
		}

		User user = ltoSessionService.getLoggedInUser();
		UserDTO userDto;
		if (user == null) {
			throw new SecurityException("No user logged in, cant upload pic.");

		}
		userDto = new UserDTO(user.getId());
		String response =  userProfilePicFacade.uploadUserProfilePicture(userDto, uploadfile);
		ajaxResponseDTO.setData(response);
		return ajaxResponseDTO;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.web.controller.rest.LTOFileController#redirectToFileUrl(javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@RequestMapping(value = "/userProfile/getProfilePic", method = RequestMethod.GET)
	public String redirectToFileUrl(HttpServletResponse response, @RequestParam("key") String key)
			throws IOException, AmazonServiceException, AmazonClientException, InterruptedException {

		return "redirect:"
				+ super.fileService.generatePreSignedUrl(super.fileService.getProfileImagesS3BucketName(), key);

	}

}
