package com.sew.rest.controller.common;

import lombok.Getter;

/**
 * Constant for API version
 * @author sarwo.wibowo
 *
 */
public enum ApiVersion {
	V1("v1"), V2("v2"), V3("v3");
	
	@Getter
	private String version;

	private ApiVersion(String apiVersion) {
		this.version = apiVersion;
	}
	
}
