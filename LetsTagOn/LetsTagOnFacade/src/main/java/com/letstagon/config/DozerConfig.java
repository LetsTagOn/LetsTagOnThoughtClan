package com.letstagon.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * Dozer spring bean config. Dozer is used for conversion of entity to VO and
 * vice versa.
 * 
 * @author Thoughtclan
 *
 */
@Configuration
public class DozerConfig {

	/**
	 * Gets the dozer bean mapper.
	 *
	 * @return the dozer bean mapper
	 */
	@Bean
	public DozerBeanMapper getDozerBeanMapper() {
		return new DozerBeanMapper();
	}
}
