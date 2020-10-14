package com.sew.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sew.service.UserPointSearchService;
import com.sew.service.dto.LeaderBoard;

/**
 * Controller to handle the leader board information
 * @author sarwo.wibowo
 *
 */
@RestController
@RequestMapping("/api/v1/leader-boards")
public class LeaderBoardController {

	@Autowired
	private UserPointSearchService userPointSearchService;
	
	@GetMapping
	public ResponseEntity<List<LeaderBoard>> getAll() {
		List<LeaderBoard> boards = userPointSearchService.getAll();
		ResponseEntity<List<LeaderBoard>> responseEntity = new ResponseEntity<List<LeaderBoard>>(boards, HttpStatus.OK);
		return responseEntity;
	}
	
}
