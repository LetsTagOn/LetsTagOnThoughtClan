package com.letstagon.facade.file;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.letstagon.facade.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserProfilePicFacade.
 */
public interface UserProfilePicFacade {

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
	public String uploadUserProfilePicture(UserDTO user, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException;

}
