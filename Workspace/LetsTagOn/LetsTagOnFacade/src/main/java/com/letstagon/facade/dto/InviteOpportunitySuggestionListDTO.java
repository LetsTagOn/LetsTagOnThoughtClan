package com.letstagon.facade.dto;

import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class InviteOpportunitySuggestionListDTO.
 */
public class InviteOpportunitySuggestionListDTO extends PaginatedResponseDTO {
	
	/**
	 * Instantiates a new invite opportunity suggestion list DTO.
	 */
	public InviteOpportunitySuggestionListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new invite opportunity suggestion list DTO.
	 *
	 * @param page the page
	 * @param size the size
	 * @param totalCount the total count
	 * @param searchResult the search result
	 * @param invitedVolunteerList the invited volunteer list
	 */
	public InviteOpportunitySuggestionListDTO(int page, int size,
			long totalCount, List<? extends Object> searchResult, List<? extends Object> invitedVolunteerList) {
		super(page, size, totalCount, searchResult);
		this.invitedVolunteerList = invitedVolunteerList;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new invite opportunity suggestion list DTO.
	 *
	 * @param invitedVolunteerList the invited volunteer list
	 */
	public InviteOpportunitySuggestionListDTO(
			List<? extends Object> invitedVolunteerList) {
		super();
		this.invitedVolunteerList = invitedVolunteerList;
	}

	/** The invited volunteer list. */
	private List<? extends Object> invitedVolunteerList;

	/**
	 * Gets the invited volunteer list.
	 *
	 * @return the invited volunteer list
	 */
	public List<? extends Object> getInvitedVolunteerList() {
		return invitedVolunteerList;
	}

	/**
	 * Sets the invited volunteer list.
	 *
	 * @param invitedVolunteerList the new invited volunteer list
	 */
	public void setInvitedVolunteerList(List<? extends Object> invitedVolunteerList) {
		this.invitedVolunteerList = invitedVolunteerList;
	}

	
	
}
