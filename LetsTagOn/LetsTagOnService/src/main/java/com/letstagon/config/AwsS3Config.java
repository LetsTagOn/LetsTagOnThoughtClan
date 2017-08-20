package com.letstagon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.transfer.TransferManager;

// TODO: Auto-generated Javadoc
/**
 * The Class AwsS3Config.
 */
@Configuration
public class AwsS3Config {

	/** The access key. */
	@Value("${aws.accessKeyId}")
	private String accessKey;

	/** The secret key. */
	@Value("${aws.secretKey}")
	private String secretKey;

	/**
	 * Gets the s 3 credentials.
	 *
	 * @return the s 3 credentials
	 */
	@Bean
	public BasicAWSCredentials getS3Credentials() {
		return new BasicAWSCredentials(this.accessKey, this.secretKey);
	}

	/**
	 * Gets the transaction manager.
	 *
	 * @return the transaction manager
	 */
	@Bean
	public TransferManager getTransactionManager() {
		return new TransferManager(this.getS3Credentials());
	}

	/**
	 * Gets the s 3 client.
	 *
	 * @return the s 3 client
	 */
	@Bean
	public AmazonS3Client getS3Client() {
		return new AmazonS3Client(this.getS3Credentials());
	}

}
