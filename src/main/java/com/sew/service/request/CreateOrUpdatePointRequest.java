package com.sew.service.request;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple object to make request to create / update user point
 * @author sarwo.wibowo
 *
 */
@Getter
@Setter
public class CreateOrUpdatePointRequest {
	
	@NonNull
	private String userEmail;
	
	@NonNull
	private Long point;
}
