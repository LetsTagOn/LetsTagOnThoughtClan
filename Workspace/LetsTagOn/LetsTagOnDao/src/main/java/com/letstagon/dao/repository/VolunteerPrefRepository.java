package com.letstagon.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.letstagon.dao.model.VolunteerPref;
import com.letstagon.dao.model.User;

import java.sql.Time;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface VolunteerPrefRepository.
 */
public interface VolunteerPrefRepository extends PagingAndSortingRepository<VolunteerPref, Long> {

	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<VolunteerPref> findByUser(User user);
	
	/**
	 * Find by user and day and start time and end time.
	 *
	 * @param user the user
	 * @param day the day
	 * @param startTime the start time
	 * @param endTime the end time
	 * @return the volunteer pref
	 */
	public VolunteerPref findByUserAndDayAndStartTimeAndEndTime(@Param("user")User user,@Param("day")String day,@Param("startTime")Time startTime,@Param("endTime")Time endTime);
	
}
