package com.letstagon.web.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedInOauthResponseDTO.
 */
public class LinkedInOauthResponseDTO {

	/** The access token. */
	private String access_token;

	/** The expires in. */
	private Long expires_in;

	/**
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * Sets the access token.
	 *
	 * @param access_token the new access token
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * Gets the expires in.
	 *
	 * @return the expires in
	 */
	public Long getExpires_in() {
		return expires_in;
	}

	/**
	 * Sets the expires in.
	 *
	 * @param expires_in the new expires in
	 */
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedInOauthResponseDTO [access_token=" + access_token + ", expires_in=" + expires_in + "]";
	}

	
	
}
