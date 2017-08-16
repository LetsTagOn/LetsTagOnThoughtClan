package com.letstagon.dao.specification;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.letstagon.dao.model.JobType;
import com.letstagon.dao.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserSpecification.
 */
public class UserSpecification {

	/**
	 * Instantiates a new user specification.
	 */
	private UserSpecification() {

	}

	/**
	 * Checks for name.
	 *
	 * @param name the name
	 * @return the specification
	 */
	public static Specification<User> hasName(String name) {
		return (root, query, cb) -> {
			// Create query here
			if (StringUtils.isBlank(name)) {
				// any
				return cb.like(root.<String> get("firstName"), "%");
			}

			return cb.like(cb.concat(cb.lower(root.<String> get("firstName")), cb.lower(root.<String> get("lastName"))),
					"%" + name + "%");

		};
	}

	/**
	 * Checks for skills.
	 *
	 * @param jobTypes the job types
	 * @return the specification
	 */
	public static Specification<User> hasSkills(List<JobType> jobTypes) {
		return (root, query, cb) -> {
			// Create query here
			if (jobTypes == null || jobTypes.isEmpty()) {
				// any
				return cb.like(root.<String> get("firstName"), "%");
			}
			// TODO
			return cb.like(root.<String> get("firstName"), "%");

		};
	}

}
