package com.sew;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.sew.domain.User;
import com.sew.domain.UserPoint;
import com.sew.service.dto.LeaderBoard;

/**
 * Simple helper class to make the mock data in one place
 * 
 * @author sarwo.wibowo
 *
 */
public class MockHelper {

	public static User mockUser(String email) {
		return User.builder().email(email).firstName("First").id(UUID.randomUUID().toString()).build();
	}

	public static List<UserPoint> mockUserPoint() {
		List<UserPoint> points = new ArrayList<>();
		Instant today = Instant.now();
		points.add(userPoint(12L, "one@test.com", today));
		points.add(userPoint(10L, "two@test.com", today));
		return points;
	}

	public static List<LeaderBoard> mockLeaderBoard() {
		List<UserPoint> points = mockUserPoint();
		return points.stream().map(point -> toLeaderBoard(point)).collect(Collectors.toList());
	}
	
	public static UserPoint mockUserPoint(String email, Long point) {
		UserPoint userPoint = userPoint(point, email, Instant.now());
		return userPoint;
	}
	
	private static UserPoint userPoint(long point, String email, Instant clockPointTime) {
		User user = mockUser(email);
		UserPoint userPoint = UserPoint.builder().id(UUID.randomUUID().toString()).pointClockedTime(clockPointTime)
				.user(user).totalPoint(point).build();
		return userPoint;
	}
	
	private static LeaderBoard toLeaderBoard(UserPoint userPoint) {
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
