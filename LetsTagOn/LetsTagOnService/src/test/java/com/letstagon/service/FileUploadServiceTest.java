package com.letstagon.service;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("ltoFileService")	
	private LtoFileService uploadService;

	/**
	 * Upload file.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@Test
	public void uploadFile() throws InterruptedException {

		uploadService.uploadFile(uploadService.getProfileImagesS3BucketName(), "pic", "test1.jpg",
				new File("D:/Workspace/LetsTagOnThoughtClan/LetsTagOn/LetsTagOnWeb/src/main/resources/static/images/profile.jpg"));

	}

}
