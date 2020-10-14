package com.sew.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sew.domain.User;

/**
 * Data access layer for user data
 * @author sarwo.wibowo
 *
 */
public interface UserRepository extends MongoRepository<User, String> {
	User findByEmail(String email);
}
