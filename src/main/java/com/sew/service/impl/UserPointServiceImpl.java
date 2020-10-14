package com.sew.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.sew.domain.User;
import com.sew.domain.UserPoint;
import com.sew.repository.UserPointRepository;
import com.sew.service.UserPointService;
import com.sew.service.UserService;
import com.sew.service.request.CreateOrUpdatePointRequest;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sarwo.wibowo
 *
 */
@Slf4j
public class UserPointServiceImpl implements UserPointService {

	@Autowired
	private UserPointRepository userPointRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional
	public UserPoint createOrUpdatePoint(@NonNull CreateOrUpdatePointRequest request) {

		Preconditions.checkArgument(request.getPoint() > 0, "Point is must be more than 0");
		
		//retrieve user
		User user = userService.getUserByEmail(request.getUserEmail());
		if(user == null) {
			throw new IllegalStateException(request.getUserEmail() + " is not registered in the system!");
		}
		
		//update point
		UserPoint userPoint = userPointRepository.findByUserId(user.getId());
		if(userPoint == null) {
			userPoint = UserPoint.builder().user(user).totalPoint(request.getPoint()).build();
		} else {
			userPoint.setTotalPoint(userPoint.getTotalPoint() + request.getPoint());
		}
		
		Instant now = Instant.now();
		userPoint.setPointClockedTime(now);
		userPointRepository.save(userPoint);
		
		log.info("Point is updated {} ", userPoint);
		return userPoint;
	}

}
