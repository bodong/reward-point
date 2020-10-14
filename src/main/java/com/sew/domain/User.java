package com.sew.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Document to store detail info of the user
 * @author sarwo.wibowo
 *
 */
@Builder
@ToString
@Getter
@Document
public class User {
	
	@Id
	@Setter
	private String id;
	
	@NonNull
	private String firstName;
	private String lastName;
	
	@NonNull
	private String email;
	
}
