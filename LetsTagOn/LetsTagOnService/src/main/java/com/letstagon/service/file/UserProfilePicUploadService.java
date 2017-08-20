package com.letstagon.service.file;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserProfilePicUploadService.
 */
public interface UserProfilePicUploadService extends LtoFileService {

	/**
	 * Upload user profile picture.
	 *
	 * @param user the user
	 * @param file the file
	 * @return the string
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public String uploadUserProfilePicture(User user, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException;

}
