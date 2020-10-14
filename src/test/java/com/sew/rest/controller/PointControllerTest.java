package com.sew.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.sew.MockHelper;
import com.sew.domain.UserPoint;
import com.sew.service.UserPointService;
import com.sew.service.request.CreateOrUpdatePointRequest;

/**
 * Test class for rest api {@code PointController}
 * 
 * @author sarwo.wibowo
 *
 */
@WebMvcTest(controllers = PointController.class)
@AutoConfigureJsonTesters
public class PointControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<UserPoint> jsonResponse;
	
	@Autowired
	private JacksonTester<CreateOrUpdatePointRequest> jsonRequest;

	@MockBean
	private UserPointService userPointService;

	@Test
	public void shouldGetAll() throws Exception {
		
		//given
		String email = "test@test.com";
		Long point = 10L;
		
		CreateOrUpdatePointRequest request = createRequest(email, point);
		UserPoint mockUserPoint = MockHelper.mockUserPoint(email, point);
		
		given(userPointService.createOrUpdatePoint(any(CreateOrUpdatePointRequest.class))).willReturn(mockUserPoint);
		
		
		//when
		MockHttpServletResponse response = this.mockMvc.perform(post("/api/v1/points")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest.write(request).getJson()))
				.andExpect(status().isOk())
				.andReturn().getResponse();
		
		
		//then
		assertEquals(response.getContentAsString(), jsonResponse.write(mockUserPoint).getJson());
		verify(userPointService, times(1)).createOrUpdatePoint(any(CreateOrUpdatePointRequest.class));
	}

	private CreateOrUpdatePointRequest createRequest(String email, Long point) {
		CreateOrUpdatePointRequest request = new CreateOrUpdatePointRequest();
		request.setPoint(point);
		request.setUserEmail(email);
		return request;
	}

}
