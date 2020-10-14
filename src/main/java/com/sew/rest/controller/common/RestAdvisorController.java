package com.sew.rest.controller.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller to handle if there is exception
 * @author sarwo.wibowo
 *
 */
@RestControllerAdvice
@Slf4j
public class RestAdvisorController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> unhandledError(Exception e) {
		log.error("There is unhandled error", e);
		return toGenericResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class})
	public ResponseEntity<Error> badRequest(Exception e) {
		log.error("Bad request made", e);
		return toGenericResponse(e, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Error> toGenericResponse(Exception e, HttpStatus status) {
		Error error = Error.builder().code(status.name()).description(e.getLocalizedMessage()).version(ApiVersion.V1).build();
		ResponseEntity<Error> response = new ResponseEntity<Error>(error, status);
		return response;
	}
}
