package com.sew.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.sew.MockHelper;
import com.sew.service.UserPointSearchService;
import com.sew.service.dto.LeaderBoard;

/**
 * Test class for rest api {@code LeaderBoardController}
 * 
 * @author sarwo.wibowo
 *
 */
@WebMvcTest(controllers = LeaderBoardController.class)
@AutoConfigureJsonTesters
public class LeaderBoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<List<LeaderBoard>> json;

	@MockBean
	private UserPointSearchService userPointSearchService;

	@Test
	public void shouldGetAll() throws Exception {
		List<LeaderBoard> mockLeaderBoards = MockHelper.mockLeaderBoard();
		given(userPointSearchService.getAll()).willReturn(mockLeaderBoards);

		MockHttpServletResponse response = this.mockMvc.perform(get("/api/v1/leader-boards")).andExpect(status().isOk())
				.andReturn().getResponse();

		assertEquals(response.getContentAsString(), json.write(mockLeaderBoards).getJson());
		verify(userPointSearchService, times(1)).getAll();
		
	}

}
