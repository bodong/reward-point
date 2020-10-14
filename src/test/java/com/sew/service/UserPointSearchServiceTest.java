package com.sew.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.sew.MockHelper;
import com.sew.domain.UserPoint;
import com.sew.repository.UserPointRepository;
import com.sew.service.dto.LeaderBoard;
import com.sew.service.impl.UserPointSearchServiceImpl;

/**
 * Test class for {@link UserPointSearchService}
 * @author sarwo.wibowo
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class UserPointSearchServiceTest {

	@InjectMocks
	private UserPointSearchServiceImpl userPointSearchService;
	
	@Mock
	private UserPointRepository userPointRepository;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetAll() {
		//given
		List<UserPoint> mockPoints = MockHelper.mockUserPoint();
		when(userPointRepository.findAll(any(Sort.class))).thenReturn(mockPoints);
		
		//when
		List<LeaderBoard> boards = userPointSearchService.getAll();
		
		//then
		assertNotNull(boards);
		assertEquals(mockPoints.size(), boards.size());
		
		verify(userPointRepository, times(1)).findAll(any(Sort.class));
	}
}
