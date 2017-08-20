package com.letstagon.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUtilsConfig.
 */
@Configuration
public class FileUtilsConfig {

	/**
	 * Gets the tika.
	 *
	 * @return the tika
	 */
	@Bean
	public Tika getTika() {
		return new Tika();
	}

}
