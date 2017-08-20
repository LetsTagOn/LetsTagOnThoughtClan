package com.letstagon.facade.dto.linkedin;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedInProfessionalExperienceDTO.
 */
public class LinkedInProfessionalExperienceDTO {
	
	/** The positions. */
	private LinkedinPositionDTO positions;

	/**
	 * Gets the positions.
	 *
	 * @return the positions
	 */
	public LinkedinPositionDTO getPositions() {
		return positions;
	}

	/**
	 * Sets the positions.
	 *
	 * @param positions the new positions
	 */
	public void setPositions(LinkedinPositionDTO positions) {
		this.positions = positions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedInProfessionalExperienceDTO [positions=" + positions
				+ "]";
	}
}
