package com.letstagon.web.controller.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.letstagon.service.file.LtoFileService;

// TODO: Auto-generated Javadoc
/**
 * The Class LTOFileController.
 *
 * @author Thoughtclan
 */

public class LTOFileController {

	/** The file service. */
	@Resource(name="ltoFileService")
	protected LtoFileService fileService;

	/** The tika. */
	@Autowired
	private Tika tika;

	/** The temp folder. */
	@Value("${lto.tmpFolder}")
	private String tempFolder;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LTOFileController.class);

	/**
	 * Redirect to file url.
	 *
	 * @param response the response
	 * @param key the key
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws InterruptedException the interrupted exception
	 */
	@RequestMapping(value = "/fileRd", method = RequestMethod.GET)
	public String redirectToFileUrl(HttpServletResponse response, @RequestParam("key") String key)
			throws IOException, AmazonServiceException, AmazonClientException, InterruptedException {

		return "redirect:" + fileService.generatePreSignedUrl(fileService.getGenericBucket(), key);

	}

	/**
	 * Download file.
	 *
	 * @param response the response
	 * @param key the key
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws InterruptedException the interrupted exception
	 */
	//@RequestMapping(value = "/fileDl", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @RequestParam("key") String key)
			throws IOException, AmazonServiceException, AmazonClientException, InterruptedException {

		S3Object s3Object = fileService.downloadFileViaS3Client(fileService.getGenericBucket(), key);

		File file = getFileFromS3Object(s3Object);

		if (s3Object.getObjectContent() == null) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			LOG.trace(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		// mime type
		String mimeType = s3Object.getObjectMetadata().getContentType();
		if (StringUtils.isBlank(mimeType)) {
			this.tika.detect(file);
		}

		response.setContentType(mimeType);
		LOG.trace("mimetype : " + mimeType);

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as
		 * popup, based on your browser setting.]
		 */
		String fileName = s3Object.getObjectMetadata().getUserMetaDataOf("fileName");
		if (StringUtils.isBlank(fileName)) {
			fileName = "file.jpg";
		}
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));

		/*
		 * "Content-Disposition : attachment" will be directly download, may
		 * provide save as popup, based on your browser setting
		 */
		// response.setHeader("Content-Disposition", String.format("attachment;
		// filename=\"%s\"", file.getName()));

		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		// Copy bytes from source to destination(outputstream in this example),
		// closes both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	/**
	 * Gets the file from S 3 object.
	 *
	 * @param s3Object the s 3 object
	 * @return the file from S 3 object
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private File getFileFromS3Object(S3Object s3Object) throws FileNotFoundException, IOException {
		InputStream reader = new BufferedInputStream(s3Object.getObjectContent());

		File file = new File(tempFolder + UUID.randomUUID().toString());
		OutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		int read = -1;

		while ((read = reader.read()) != -1) {
			writer.write(read);
		}

		writer.flush();
		writer.close();
		reader.close();
		return file;
	}

	/**
	 * Upload single file using Spring Controller.
	 *
	 * @param file the file
	 * @param key the key
	 * @return the string
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	//@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file,
			@RequestParam("key") String key)
					throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		if (!file.isEmpty()) {
			fileService.uploadFile(fileService.getProfileImagesS3BucketName(), "pics",
					(key == null) ? file.getOriginalFilename() : key, file);

			return "You successfully uploaded file=" + file.getOriginalFilename();
		} else {
			return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller.
	 *
	 * @param files the files
	 * @return the string
	 * @throws AmazonServiceException the amazon service exception
	 * @throws AmazonClientException the amazon client exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	//@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files)
			throws AmazonServiceException, AmazonClientException, IOException, InterruptedException {

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];

			if (!file.isEmpty()) {
				fileService.uploadFile(fileService.getProfileImagesS3BucketName(), "pics", file.getOriginalFilename(),
						file);

				LOG.trace("You successfully uploaded file=" + file.getOriginalFilename());
			} else {
				LOG.trace("You failed to upload " + file.getOriginalFilename() + " because the file was empty.");
			}

		}
		return message;
	}

}
