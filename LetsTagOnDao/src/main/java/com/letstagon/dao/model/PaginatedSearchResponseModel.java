package com.letstagon.dao.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PaginatedSearchResponseModel.
 */
public class PaginatedSearchResponseModel {

	/** The search result. */
	private List<? extends Object> searchResult;

	/** The page. */
	private int page;

	/** The size. */
	private int size;

	/** The total count. */
	private long totalCount;

	/**
	 * Instantiates a new paginated search response model.
	 */
	public PaginatedSearchResponseModel() {
		super();
	}

	/**
	 * Instantiates a new paginated search response model.
	 *
	 * @param searchResult the search result
	 * @param page the page
	 * @param size the size
	 * @param totalCount the total count
	 */
	public PaginatedSearchResponseModel(List<? extends Object> searchResult, int page, int size, long totalCount) {
		super();
		this.searchResult = searchResult;
		this.page = page;
		this.size = size;
		this.totalCount = totalCount;
	}

	/**
	 * Gets the search result.
	 *
	 * @return the search result
	 */
	public List<? extends Object> getSearchResult() {
		return searchResult;
	}

	/**
	 * Sets the search result.
	 *
	 * @param searchResult the new search result
	 */
	public void setSearchResult(List<? extends Object> searchResult) {
		this.searchResult = searchResult;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the total count.
	 *
	 * @return the total count
	 */
	public long getTotalCount() {
		return totalCount;
	}
	

	/**
	 * Sets the total count.
	 *
	 * @param totalCount the new total count
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

}
