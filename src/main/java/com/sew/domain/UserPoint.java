package com.sew.domain;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Document to store user point
 * @author sarwo.wibowo
 *
 */
@Builder
@ToString
@Getter
@Document
public class UserPoint {
	
	@Id
	@Setter
	private String id;
	
	private User user;
	
	@Setter
	private Instant pointClockedTime;
	
	@Setter
	private Long totalPoint;
}
