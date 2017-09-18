package com.letstagon.service;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.letstagon.LetsTagOnServiceApplication;
import com.letstagon.service.file.LtoFileService;

// TODO: Auto-generated Javadoc
/**
 * The Class FileDownloadServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LetsTagOnServiceApplication.class)
@WebAppConfiguration
public class FileDownloadServiceTest {

	/** The download service. */
	@Autowired()
	@Qualifier("ltoFileService")
	private LtoFileService downloadService;

	/** The tika. */
	@Autowired
	private Tika tika;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FileDownloadServiceTest.class);

	/**
	 * Download file.
	 *
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void downloadFile() throws InterruptedException, IOException {

		File file = downloadService.downloadFile(downloadService.getProfileImagesS3BucketName(), "pic/test.png");

		LOG.info("File name : " + file.getName());
		LOG.info("File content type : " + tika.detect(file));

		Assert.isTrue(file.length() > 0);

	}

}
