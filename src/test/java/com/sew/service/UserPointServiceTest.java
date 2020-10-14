package com.sew.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.sew.domain.UserPoint;
import com.sew.repository.UserPointRepository;
import com.sew.service.impl.UserPointServiceImpl;
import com.sew.service.request.CreateOrUpdatePointRequest;

/**
 * Test class for {@link UserPointService}
 * @author sarwo.wibowo
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class UserPointServiceTest {

	@InjectMocks
	private UserPointServiceImpl userPointService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserPointRepository userPointRepository;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCreateOrUpdatePoint() {
		//given
		String email = "test@test.com";
		CreateOrUpdatePointRequest request = new CreateOrUpdatePointRequest();
		request.setUserEmail(email);
		request.setPoint(12l);

		User mockUser = MockHelper.mockUser(email);
		when(userService.getUserByEmail(email)).thenReturn(mockUser);
		when(userPointRepository.findByUserId(mockUser.getId())).thenReturn(null);
		
		//when
		UserPoint userPoint = userPointService.createOrUpdatePoint(request);
		
		//then
		assertNotNull(userPoint);
		verify(userService, times(1)).getUserByEmail(userPoint.getUser().getEmail());
		verify(userPointRepository, times(1)).findByUserId(userPoint.getUser().getId());
		
		assertNotNull(userPoint.getPointClockedTime());
		assertTrue(userPoint.getTotalPoint().compareTo(request.getPoint()) == 0);
	}
	
	
	@Test
	public void shouldNotCreateOrUpdatePoint() {
		//given
		String email = "test@test.com";
		CreateOrUpdatePointRequest request = new CreateOrUpdatePointRequest();
		request.setUserEmail(email);
		request.setPoint(-1l);

		
		//when
		UserPoint userPoint = null;
		Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
			userPointService.createOrUpdatePoint(request);
		});
		
		//then
		assertNull(userPoint);
		assertTrue(throwable instanceof IllegalArgumentException);
		assertTrue(throwable.getMessage().contains("Point is must be more than 0"));
		
	}
	
}
