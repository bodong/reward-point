package com.sew.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.sew.domain.UserPoint;
import com.sew.repository.UserPointRepository;
import com.sew.service.UserPointSearchService;
import com.sew.service.dto.LeaderBoard;

/**
 * @author sarwo.wibowo
 *
 */
public class UserPointSearchServiceImpl implements UserPointSearchService {

	@Autowired
	private UserPointRepository userPointRepository;

	@Override
	public List<LeaderBoard> getAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Sort.Direction.DESC, "totalPoint"));
		orders.add(new Order(Sort.Direction.ASC, "pointClockedTime"));
				
		List<UserPoint> userPoints = userPointRepository.findAll(Sort.by(orders));
		return toLeaderBoards(userPoints);
	}

	private List<LeaderBoard> toLeaderBoards(List<UserPoint> userPoints) {
		return userPoints.stream().map(userPoint -> toLeaderBoard(userPoint)).collect(Collectors.toList());
	}

	private LeaderBoard toLeaderBoard(UserPoint userPoint) {
		LeaderBoard leaderBoard = LeaderBoard.builder()
				.email(userPoint.getUser().getEmail())
				.firstName(userPoint.getUser().getFirstName())
				.lastName(userPoint.getUser().getLastName())
				.pointClockedTime(userPoint.getPointClockedTime())
				.totalPoint(userPoint.getTotalPoint())
				.build();
		return leaderBoard;
	}

}
