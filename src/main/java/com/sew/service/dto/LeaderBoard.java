package com.sew.service.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

/**
 * Simple object to transfer detail of user with their point
 * 
 * @author sarwo.wibowo
 *
 */
@Getter
@Builder
public class LeaderBoard {

	private String firstName;
	private String lastName;
	private String email;
	private Instant pointClockedTime;
	private Long totalPoint;

}
