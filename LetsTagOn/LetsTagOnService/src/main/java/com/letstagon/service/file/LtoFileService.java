package com.letstagon.service.file;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;

// TODO: Auto-generated Javadoc
/**
 * The Interface LtoFileService.
 */
public interface LtoFileService {

	/**
	 * Gets the profile images S 3 bucket name.
	 *
	 * @return the profile images S 3 bucket name
	 */
	String getProfileImagesS3BucketName();

	/**
	 * Gets the generic bucket.
	 *
	 * @return the generic bucket
	 */
	String getGenericBucket();

	/**
	 * Upload file.
	 *
	 * @param bucketName the bucket name
	 * @param folderName the folder name
	 * @param s3FileName the s 3 file name
	 * @param file the file
	 * @throws InterruptedException the interrupted exception
	 */
	void uploadFile(String bucketName, String folderName, String s3FileName, File file) throws InterruptedException;

	/**
	 * Upload file.
	 *
	 * @param bucketName the bucket name
	 * @param folderName the folder name
	 * @param s3FileName the s 3 file name
	 * @param file the file
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	void uploadFile(String bucketName, String folderName, String s3FileName, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException;

	/**
	 * Download file.
	 *
	 * @param bucket the bucket
	 * @param key the key
	 * @return the file
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws InterruptedException the interrupted exception
	 */
	File downloadFile(String bucket, String key)
			throws AmazonServiceException, AmazonClientException, InterruptedException;

	/**
	 * Download file via S 3 client.
	 *
	 * @param bucket the bucket
	 * @param key the key
	 * @return the s 3 object
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws InterruptedException the interrupted exception
	 */
	S3Object downloadFileViaS3Client(String bucket, String key)
			throws AmazonServiceException, AmazonClientException, InterruptedException;

	/**
	 * Generate pre signed url.
	 *
	 * @param bucket the bucket
	 * @param key the key
	 * @return the string
	 */
	String generatePreSignedUrl(String bucket, String key);

	/**
	 * Delete file.
	 *
	 * @param bucketName the bucket name
	 * @param key the key
	 * @return true, if successful
	 */
	boolean deleteFile(String bucketName, String key);

	/**
	 * Gets the opportunity images bucket name.
	 *
	 * @return the opportunity images bucket name
	 */
	String getOpportunityImagesBucketName();

}
