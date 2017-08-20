package com.letstagon.facade.file.impl;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Optional;

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
import com.letstagon.dao.model.User;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.file.UserProfilePicFacade;
import com.letstagon.service.UserService;
import com.letstagon.service.file.UserProfilePicUploadService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserProfilePicFacadeImpl.
 */
@Component
public class UserProfilePicFacadeImpl implements UserProfilePicFacade {

	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(UserProfilePicFacadeImpl.class);

	/** The user profile pic upload service. */
	@Resource(name = "userProfilePicUploadService")
	private UserProfilePicUploadService userProfilePicUploadService;

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/** The user service. */
	@Autowired
	private UserService userService;

	/* (non-Javadoc)
	 * @see com.letstagon.facade.file.UserProfilePicFacade#uploadUserProfilePicture(com.letstagon.facade.dto.UserDTO, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String uploadUserProfilePicture(UserDTO user, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		Optional<User> userObj = userService.getUserById(user.getId());
		if (!userObj.isPresent()) {

			// invalid userid
			throw new InvalidParameterException("Invalid user ID");

		}

		User volunteerModel = mapper.map(user, User.class);

		String key = userProfilePicUploadService.uploadUserProfilePicture(volunteerModel, file);

		User userFromDb = userObj.get();

		if (StringUtils.isNotBlank(userFromDb.getProfilePicture())) {
			LOG.trace("Deleting previous current profile pic.");
			userProfilePicUploadService.deleteFile(userProfilePicUploadService.getProfileImagesS3BucketName(),
					userFromDb.getProfilePicture());
		}
		userFromDb.setProfilePicture(key);
		userService.saveUser(userFromDb);

		return key;

	}

}
