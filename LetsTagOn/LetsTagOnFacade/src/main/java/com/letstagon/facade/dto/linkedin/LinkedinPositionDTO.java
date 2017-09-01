package com.letstagon.facade.dto.linkedin;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedinPositionDTO.
 */
public class LinkedinPositionDTO {
	
	/** The total. */
	private int _total;
	
	/** The values. */
	private List<LinkedinUserExperienceDTO> values;
	
	/**
	 * Gets the total.
	 *
	 * @return the linkedinUserExperienceDTOList
	 */
	public int get_total() {
		return _total;
	}
	
	/**
	 * Sets the total.
	 *
	 * @param _total the new total
	 */
	public void set_total(int _total) {
		this._total = _total;
	}
	
	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<LinkedinUserExperienceDTO> getValues() {
		return values;
	}
	
	/**
	 * Sets the values.
	 *
	 * @param values the new values
	 */
	public void setValues(List<LinkedinUserExperienceDTO> values) {
		this.values = values;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedinPositionDTO [_total=" + _total + ", values=" + values + "]";
	}


}
