package com.sew.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sew.MockHelper;
import com.sew.domain.User;
import com.sew.repository.UserRepository;
import com.sew.service.impl.UserServiceImpl;

/**
 * Test class for service {@link UserService}
 * @author sarwo.wibowo
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCreateUser() {
		//given
		User requestUser = MockHelper.mockUser("test@something.com");
		when(userRepository.save(requestUser)).thenReturn(requestUser);
		
		//when
		User createdUser = userService.createUser(requestUser);
		
		//then
		assertNotNull(createdUser, "User is not created!");
		assertNotNull(createdUser.getId(), "User id is not generated!");
		assertEquals(requestUser.getEmail(), createdUser.getEmail());
		
		verify(userRepository, times(1)).save(any(User.class));
		
	}
	
	@Test
	public void shouldNotCreateUser() {
		//given
		User requestUser = MockHelper.mockUser("bad email");
		when(userRepository.save(requestUser)).thenReturn(requestUser);
		

		//when
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(requestUser);
		});
		
		
		//then
		verify(userRepository, times(0)).save(any(User.class));
		assertTrue(exception instanceof IllegalArgumentException, "Not valid exception");
		assertTrue(exception.getMessage().contains("Valid email"));
		
	}
	
	@Test
	public void shouldFindByEmail() {
		
		//given
		String email = "test@simple.com";
		User mockUser = MockHelper.mockUser(email);
		when(userRepository.findByEmail(email)).thenReturn(mockUser);
		
		//when
		User user = userService.getUserByEmail(email);
		
		//then
		assertNotNull(user);
		assertEquals(email, user.getEmail());
		verify(userRepository, times(1)).findByEmail(email);
		
	}
	
	@Test
	public void shouldNotFindByEmail() {
		
		//given
		String email = "notfound@simple.com";
		when(userRepository.findByEmail(email)).thenReturn(null);
		
		//when
		User user = userService.getUserByEmail(email);
		
		//then
		assertNull(user);
		verify(userRepository, times(1)).findByEmail(email);
		
	}
	
}
