package com.letstagon.service.file.impl;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.letstagon.dao.model.User;
import com.letstagon.service.file.UserProfilePicUploadService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserProfilePicUploadServiceImpl.
 */
@Component(value="userProfilePicUploadService")
public class UserProfilePicUploadServiceImpl extends LtoFileServiceImpl implements UserProfilePicUploadService {

	/** The Constant PROF_PIC_PATH. */
	private static final String PROF_PIC_PATH = "/ProfilePic/";

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.UserProfilePicUploadService#uploadUserProfilePicture(com.letstagon.dao.model.User, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String uploadUserProfilePicture(User user, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		String keyName=this.generateKeyName();
		String key = this.generateFolderName(user) + keyName;

		super.uploadFile(super.getProfileImagesS3BucketName(), this.generateFolderName(user), keyName,
				file);

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
	 * @param user the user
	 * @return the string
	 */
	private String generateFolderName(User user) {

		if (user == null || user.getId() < 1) {
			throw new InvalidParameterException("User cannot be null");

		}

		return user.getId() + UserProfilePicUploadServiceImpl.PROF_PIC_PATH;

	}

}
