package com.letstagon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

// TODO: Auto-generated Javadoc
/**
 * The Class LTORepositoryRestMvcConfiguration.
 */
@Configuration
@Import(RepositoryRestMvcConfiguration.class)
public class LTORepositoryRestMvcConfiguration extends RepositoryRestMvcConfiguration {
	
	/** The Constant REPOSIOTYBASEPATH. */
	private static final String REPOSIOTYBASEPATH = "/rest";

	/* (non-Javadoc)
	 * @see org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration#configureRepositoryRestConfiguration(org.springframework.data.rest.core.config.RepositoryRestConfiguration)
	 */
	@Override
	protected void configureRepositoryRestConfiguration(
	        RepositoryRestConfiguration config) {
		config.setBasePath(REPOSIOTYBASEPATH);

	}
}