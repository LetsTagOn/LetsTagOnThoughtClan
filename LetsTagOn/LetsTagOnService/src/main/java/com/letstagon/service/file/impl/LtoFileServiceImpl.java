package com.letstagon.service.file.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.letstagon.service.file.LtoFileService;

// TODO: Auto-generated Javadoc
/**
 * File upload service using Amazon S3.
 *
 * @author ThoughtClan
 */
@Component("ltoFileService")
public class LtoFileServiceImpl implements LtoFileService {

	/** The profile images S 3 bucket name. */
	@Value("${aws.s3.profilePicBucketName}")
	private String profileImagesS3BucketName;

	/** The opportunity images bucket name. */
	@Value("${aws.s3.opportunityImagesBucketName}")
	private String opportunityImagesBucketName;

	/** The generic bucket. */
	@Value("${aws.s3.genericBucketName}")
	private String genericBucket;

	/** The temp folder. */
	@Value("${lto.tmpFolder}")
	private String tempFolder;

	/** The transfer manager. */
	@Autowired
	private TransferManager transferManager;

	/** The amazon S 3 client. */
	@Autowired
	private AmazonS3Client amazonS3Client;

	/** The tika. */
	@Autowired
	private Tika tika;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LtoFileServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#uploadFile(java.lang.String, java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public void uploadFile(String bucketName, String folderName, String s3FileName, File file)
			throws InterruptedException {

		LOG.trace("Initiating upload : bucket name->" + bucketName + " , folderName->" + folderName + " , s3FileName->"
				+ s3FileName + " , fileName->" + file.getName());
		// TransferManager processes all transfers asynchronously,
		// so this call will return immediately.
		ObjectMetadata objectMetaData = new ObjectMetadata();
		objectMetaData.addUserMetadata("fileName", file.getName());
		// objectMetaData.setContentType(tika.detect(file));
		Upload upload = this.transferManager.upload(profileImagesS3BucketName, folderName + "/" + s3FileName, file);
		try {
			// Or you can block and wait for the upload to finish
			upload.waitForCompletion();
			LOG.trace("Upload complete. Bytes transferred : " + upload.getProgress().getBytesTransferred());
		} catch (AmazonClientException amazonClientException) {
			LOG.error("Unable to upload file, upload was aborted.  bucket name->" + bucketName + " , folderName->"
					+ folderName + " , s3FileName->" + s3FileName + " , fileName->" + file.getName());
			LOG.error("Message : " + amazonClientException.getLocalizedMessage());
			throw amazonClientException;
		} catch (InterruptedException e) {
			LOG.error("Unable to upload file, upload was aborted..  bucket name->" + bucketName + " , folderName->"
					+ folderName + " , s3FileName->" + s3FileName + " , fileName->" + file.getName());
			LOG.error("Message : " + e.getLocalizedMessage());
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#uploadFile(java.lang.String, java.lang.String, java.lang.String, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public void uploadFile(String bucketName, String folderName, String s3FileName, MultipartFile file)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		LOG.trace("Initiating upload : bucket name->" + bucketName + " , folderName->" + folderName + " , s3FileName->"
				+ s3FileName);

		ObjectMetadata objectMetaData = new ObjectMetadata();
		objectMetaData.addUserMetadata("fileName", file.getOriginalFilename());
		objectMetaData.setContentType(tika.detect(file.getInputStream()));
		objectMetaData.setContentLength(file.getSize());
		// TransferManager processes all transfers asynchronously,
		// so this call will return immediately.
		Upload upload = this.transferManager.upload(bucketName, folderName + s3FileName,
				file.getInputStream(), objectMetaData);

		try {
			// Or you can block and wait for the upload to finish
			upload.waitForCompletion();
			LOG.trace("Upload complete. Bytes transferred : " + upload.getProgress().getBytesTransferred());
		} catch (AmazonClientException amazonClientException) {
			LOG.error("Unable to upload file, upload was aborted.  bucket name->" + bucketName + " , folderName->"
					+ folderName + " , s3FileName->" + s3FileName + " , fileName->" + file.getName());
			LOG.error("Message : " + amazonClientException.getLocalizedMessage());
			throw amazonClientException;
		} catch (InterruptedException e) {
			LOG.error("Unable to upload file, upload was aborted..  bucket name->" + bucketName + " , folderName->"
					+ folderName + " , s3FileName->" + s3FileName + " , fileName->" + file.getName());
			LOG.error("Message : " + e.getLocalizedMessage());
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#downloadFile(java.lang.String, java.lang.String)
	 */
	@Override
	public File downloadFile(String bucket, String key)
			throws AmazonServiceException, AmazonClientException, InterruptedException {

		File file = new File(tempFolder + "/" + key);
		Download download = this.transferManager.download(bucket, key, file);
		download.waitForCompletion();
		String fileName = (String) download.getObjectMetadata().getRawMetadataValue("fileName");
		LOG.trace("File name : " + fileName);
		return file;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#downloadFileViaS3Client(java.lang.String, java.lang.String)
	 */
	@Override
	public S3Object downloadFileViaS3Client(String bucket, String key)
			throws AmazonServiceException, AmazonClientException, InterruptedException {

		LOG.trace("Attempting to download file from bucket : " + bucket + " , key name : " + key);
		S3Object object = this.amazonS3Client.getObject(bucket, key);

		String fileName = (String) object.getObjectMetadata().getUserMetaDataOf("fileName");
		LOG.trace("File name : " + fileName);
		return object;

	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#generatePreSignedUrl(java.lang.String, java.lang.String)
	 */
	@Override
	public String generatePreSignedUrl(String bucket, String key) {

		java.util.Date expiration = new java.util.Date();
		long msec = expiration.getTime();
		msec += 1000 * 60 * 60; // 1 hour.
		expiration.setTime(msec);

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key);
		generatePresignedUrlRequest.setMethod(HttpMethod.GET); // Default.
		generatePresignedUrlRequest.setExpiration(expiration);

		URL url = this.amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#deleteFile(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteFile(String bucketName, String key) {
		this.amazonS3Client.deleteObject(bucketName, key);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#getProfileImagesS3BucketName()
	 */
	@Override
	public String getProfileImagesS3BucketName() {
		return profileImagesS3BucketName;
	}

	/**
	 * Sets the profile images S 3 bucket name.
	 *
	 * @param profileImagesS3BucketName the new profile images S 3 bucket name
	 */
	public void setProfileImagesS3BucketName(String profileImagesS3BucketName) {
		this.profileImagesS3BucketName = profileImagesS3BucketName;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#getGenericBucket()
	 */
	@Override
	public String getGenericBucket() {
		return genericBucket;
	}

	/**
	 * Sets the generic bucket.
	 *
	 * @param genericBucket the new generic bucket
	 */
	public void setGenericBucket(String genericBucket) {
		this.genericBucket = genericBucket;
	}

	/* (non-Javadoc)
	 * @see com.letstagon.service.file.LtoFileService#getOpportunityImagesBucketName()
	 */
	@Override
	public String getOpportunityImagesBucketName() {
		return opportunityImagesBucketName;
	}

	/**
	 * Sets the opportunity images bucket name.
	 *
	 * @param opportunityImagesBucketName the new opportunity images bucket name
	 */
	public void setOpportunityImagesBucketName(String opportunityImagesBucketName) {
		this.opportunityImagesBucketName = opportunityImagesBucketName;
	}

}
