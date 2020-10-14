package com.sew.repository;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

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

/**
 * Test class for {@link UserRepository}
 * 
 * @author sarwo.wibowo
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MongoOperations mongos;

	@BeforeAll
	public void setUp() {
		User user = User.builder().email("simple@test.com").firstName("sarwo").lastName("wibowo").build();
		userRepository.save(user);
		notNull(user, "user object is null");
		notNull(user.getId(), "user id is null");
	}
	
	@AfterAll
	public void tearDown() {
		mongos.dropCollection(User.class);
	}

	@Test
	public void shouldFindByEmail() {
		// given
		String email = "simple@test.com";

		// when
		User user = userRepository.findByEmail(email);

		// then
		notNull(user, "user object is null");
		isTrue(email.equals(user.getEmail()), "not valid email");
	}
	
	@Test
	public void shouldNotFindByEmail() {
		// given
		String email = "notfound@test.com";

		// when
		User user = userRepository.findByEmail(email);

		// then
		assertNull(user, "user object is not null");
	}

}
