package com.letstagon.service;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.letstagon.LetsTagOnServiceApplication;
import com.letstagon.service.file.LtoFileService;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUploadServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnServiceApplication.class)
@WebAppConfiguration
public class FileUploadServiceTest {

	/** The upload service. */
	@Autowired
	private LtoFileService uploadService;

	/**
	 * Upload file.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@Test
	public void uploadFile() throws InterruptedException {

		uploadService.uploadFile(uploadService.getProfileImagesS3BucketName(), "pics", "test.jpg",
				new File("/Users/Bala/Downloads/10557456_10152624543724282_1638054964128020654_n.jpg"));

	}

}
