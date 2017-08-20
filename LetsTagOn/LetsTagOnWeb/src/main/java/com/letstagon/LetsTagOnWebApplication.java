package com.letstagon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Auto-generated Javadoc
/**
 * The Class LetsTagOnWebApplication.
 */
@SpringBootApplication
@EnableScheduling
public class LetsTagOnWebApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(LetsTagOnWebApplication.class, args);
	}
}
