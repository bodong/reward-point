package com.sew.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sew.domain.UserPoint;
import com.sew.service.UserPointService;
import com.sew.service.request.CreateOrUpdatePointRequest;

import lombok.NonNull;

/**
 * Controller to handle REST layer for user-point manipulation
 * @author sarwo.wibowo
 *
 */
@RestController
@RequestMapping("/api/v1/points")
public class PointController {

	@Autowired
	private UserPointService userPointService;
	
	@PostMapping
	public ResponseEntity<UserPoint> clockPoint(@RequestBody @NonNull CreateOrUpdatePointRequest request) {
		UserPoint userPoint = userPointService.createOrUpdatePoint(request);
		ResponseEntity<UserPoint> responseEntity = new ResponseEntity<UserPoint>(userPoint, HttpStatus.OK);
		return responseEntity;
	} 
	
}
