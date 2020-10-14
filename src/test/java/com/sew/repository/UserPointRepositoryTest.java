package com.sew.repository;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.util.Assert.notNull;

import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sew.domain.User;
import com.sew.domain.UserPoint;

/**
 * Test class for {@link UserPointRepository}
 * 
 * @author sarwo.wibowo
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserPointRepositoryTest {

	@Autowired
	UserPointRepository userPointRepository;
	
	@Autowired
	MongoOperations mongos;

	@BeforeAll
	public void setUp() {
		
		User user = User.builder().email("simple@test.com").firstName("sarwo").lastName("wibowo").id("1234").build();
		
		UserPoint userPoint = UserPoint.builder().pointClockedTime(Instant.now()).user(user).build();
		userPointRepository.save(userPoint);
		notNull(userPoint, "object is null");
		notNull(userPoint.getId(), "object is not created");
	}
	
	@AfterAll
	public void tearDown() {
		mongos.dropCollection(UserPoint.class);
	}

	@Test
	public void shouldFindByUserId() {
		//given
		String userId = "1234";
		
		//when
		UserPoint userPoint = userPointRepository.findByUserId(userId);
	
		//then
		notNull(userPoint, "object is null");
		
	}
	
	@Test
	public void shouldNotFindByUserId() {
		//given
		String userId = "3431";
		
		//when
		UserPoint userPoint = userPointRepository.findByUserId(userId);
	
		//then
		assertNull(userPoint, "object is found");
		
	}

}
