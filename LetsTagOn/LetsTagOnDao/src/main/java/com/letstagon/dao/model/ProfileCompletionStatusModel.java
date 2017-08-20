package com.letstagon.dao.model;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileCompletionStatusModel.
 */
public class ProfileCompletionStatusModel {

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

		if (this.advice == null) {
			this.advice = new ArrayList<String>();
		}
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
