package com.sew.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sew.domain.UserPoint;

/**
 * Data access layer for user point data
 * @author sarwo.wibowo
 *
 */
public interface UserPointRepository extends MongoRepository<UserPoint, String> {
	UserPoint findByUserId(String userId);
}
