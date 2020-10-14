package com.sew.service;

import java.util.List;

import com.sew.service.dto.LeaderBoard;

/**
 * Service to display the leader-board of user point rank
 * @author sarwo.wibowo
 *
 */
public interface UserPointSearchService {
	
	/**
	 * Retrieve the leader board of users ordered by point and clock point time
	 * @return List of {@link LeaderBoard} if exist, else will return empty
	 */
	List<LeaderBoard> getAll();
}
