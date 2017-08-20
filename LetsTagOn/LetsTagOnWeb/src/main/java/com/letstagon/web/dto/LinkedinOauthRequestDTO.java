package com.letstagon.web.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedinOauthRequestDTO.
 */
public class LinkedinOauthRequestDTO {

	/** The grant type. */
	private String grant_type;
	
	/** The code. */
	private String code;
	
	/** The redirect uri. */
	private String redirect_uri;
	
	/** The client id. */
	private String client_id;
	
	/** The client secret. */
	private String client_secret;
	
	/**
	 * Gets the grant type.
	 *
	 * @return the grant type
	 */
	public String getGrant_type() {
		return grant_type;
	}
	
	/**
	 * Sets the grant type.
	 *
	 * @param grant_type the new grant type
	 */
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Gets the redirect uri.
	 *
	 * @return the redirect uri
	 */
	public String getRedirect_uri() {
		return redirect_uri;
	}
	
	/**
	 * Sets the redirect uri.
	 *
	 * @param redirect_uri the new redirect uri
	 */
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	
	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public String getClient_id() {
		return client_id;
	}
	
	/**
	 * Sets the client id.
	 *
	 * @param client_id the new client id
	 */
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
	/**
	 * Gets the client secret.
	 *
	 * @return the client secret
	 */
	public String getClient_secret() {
		return client_secret;
	}
	
	/**
	 * Sets the client secret.
	 *
	 * @param client_secret the new client secret
	 */
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedinOauthRequestDTO [grant_type=" + grant_type + ", code=" + code + ", redirect_uri=" + redirect_uri
				+ ", client_id=" + client_id + ", client_secret=" + client_secret + "]";
	}
	
	
	
}
