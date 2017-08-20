package com.letstagon.facade.dto;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileCompletionStatusDTO.
 */
public class ProfileCompletionStatusDTO {

	/** The completion. */
	private float completion;
	
	/** The advice. */
	private List<String> advice;
	
	/** The pending items. */
	private List<String> pendingItems;

	/**
	 * Gets the completion.
	 *
	 * @return the completion
	 */
	public float getCompletion() {
		return completion;
	}

	/**
	 * Sets the completion.
	 *
	 * @param completion the new completion
	 */
	public void setCompletion(float completion) {
		this.completion = completion;
	}

	/**
	 * Gets the advice.
	 *
	 * @return the advice
	 */
	public List<String> getAdvice() {
		return advice;
	}

	/**
	 * Sets the advice.
	 *
	 * @param advice the new advice
	 */
	public void setAdvice(List<String> advice) {
		this.advice = advice;
	}

	/**
	 * Gets the pending items.
	 *
	 * @return the pending items
	 */
	public List<String> getPendingItems() {
		return pendingItems;
	}

	/**
	 * Sets the pending items.
	 *
	 * @param pendingItems the new pending items
	 */
	public void setPendingItems(List<String> pendingItems) {
		this.pendingItems = pendingItems;
	}

}
