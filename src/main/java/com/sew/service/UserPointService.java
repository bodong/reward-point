package com.sew.service;

import com.sew.domain.UserPoint;
import com.sew.service.request.CreateOrUpdatePointRequest;

/**
 * Service for user point data manipulation
 * @author sarwo.wibowo
 *
 */
public interface UserPointService {
	
	/**
	 * To create / update point of particular user. If user is not found, then will throw 
	 * @param request
	 * @return {@link UserPoint}
	 */
	UserPoint createOrUpdatePoint(CreateOrUpdatePointRequest request);
}
