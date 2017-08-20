package com.letstagon.facade.dto.linkedin;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedinLocationDTO.
 */
public class LinkedinLocationDTO {

/** The name. */
private String name;

/** The country. */
private LinkedInCountryDTO country;

/**
 * Gets the name.
 *
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * Sets the name.
 *
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "LinkedinLocationDTO [name=" + name + ", country=" + country + "]";
}

/**
 * Gets the country.
 *
 * @return the country
 */
public LinkedInCountryDTO getCountry() {
	return country;
}

/**
 * Sets the country.
 *
 * @param country the country to set
 */
public void setCountry(LinkedInCountryDTO country) {
	this.country = country;
}

}