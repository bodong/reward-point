package com.sew.rest.controller.common;

import lombok.Builder;
import lombok.Getter;

/**
 * Object to return when there is an error / exception
 * @author sarwo.wibowo
 *
 */
@Getter
@Builder
public class Error {

	private ApiVersion version;
	private String code;
	private String description;
	
}
