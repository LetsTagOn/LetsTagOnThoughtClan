package com.letstagon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class AwsS3Config.
 */
@Configuration
public class AwsS3Config {

	
	/**
	 * Gets the transaction manager.
	 *
	 * @return the transaction manager
	 */
	@Bean
	public TransferManager getTransactionManager() {
		return TransferManagerBuilder.standard().withS3Client(this.getS3Client()).build();
	}

	/**
	 * Gets the s 3 client.
	 *
	 * @return the s 3 client
	 */
	@Bean
	public AmazonS3 getS3Client() {
		
		return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
	}

}
